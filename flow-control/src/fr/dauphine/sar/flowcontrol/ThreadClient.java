package fr.dauphine.sar.flowcontrol;
import java.io.*;
import java.net.*;

public class ThreadClient extends Thread {
	private Socket so=null;
	private BufferedReader in;
	private PrintWriter out;
	private Buffer buffer;

	public ThreadClient(Socket so,Buffer buffer)
	{
		this.so=so;
		this.buffer=buffer;
	}
	
	/**
	 *
	 */
	@Override 
	public void run() {
		try
		{
			in = new BufferedReader(new InputStreamReader(so.getInputStream()));
			out = new PrintWriter(so.getOutputStream());
			String req=null;
			String rep=null;
			req="Bienvenue sur le service production-consommation"; //Message d'acceuil
			out.println(req);
			System.out.println(req);
			out.flush();

			rep=in.readLine();
			if (rep.equals("Consume"))
			{
				if(!buffer.isEmpty())
				{
					req="Ack"; 
					out.println(req);
					out.flush();
					System.out.println("Un consommateur veut consommer ! ");
					letConsume(rep,req);
					System.out.println(buffer);
				}
				else
				{
					req="Rej"; 
					out.println(req);
					out.flush();
					System.out.println("Un consommateur veut consommer mais le tampon est vide ! ");
					System.out.println(buffer);
				}
			}
			else
			{
				if(!buffer.isFull())
				{
					req="Ack"; 
					out.println(req);
					out.flush();
					System.out.println("Un producteur veut produire ! ");
					letProduce(rep);
					System.out.println(buffer);
				}
				else 
				{
					req="Rej"; 
					out.println(req);
					out.flush();
					System.out.println("Un producteur veut produire mais le tampon est plein ! ");
					System.out.println(buffer);
				}
			}
		}
		catch(IOException e)
		{ 
			System.err.println("Erreur : "+e);
		}
		finally{
			try {
				so.close();
			}
			catch (IOException e){}
		}
	}


	private void letProduce(String rep) throws IOException {
		// TODO Auto-generated method stub
		rep=in.readLine();		//The message to put
		synchronized(buffer) {
			buffer.add(rep);//Acces au tampon pour mettre le message rep
		}
	}


	private void letConsume(String rep, String req) throws IOException {
		// TODO Auto-generated method stub
		String consumedMessage = null;
		rep=in.readLine();    //ask to consume 
		synchronized(buffer) {
			consumedMessage=buffer.remove();//Acces au tampon pour enlever le message 
		}
		req=consumedMessage;
		out.println(req);
	}
}