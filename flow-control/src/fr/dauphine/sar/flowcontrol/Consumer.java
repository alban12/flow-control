package fr.dauphine.sar.flowcontrol;

import java.io.*;
import java.net.*;

public class Consumer extends Client {

	public static void main(String[] args) throws InterruptedException
	{
		int port=4020;
		InetAddress hote=null;
		Socket sc=null;
		BufferedReader in;
		PrintWriter out;

		try{
			if (args.length>=2){
						hote= InetAddress.getByName(args[0]); 
						port= Integer.parseInt(args[1]); 
			}
			else {
				hote = hote.getLocalHost();
			}
		} catch(UnknownHostException e){
			System.err.println("Machine Inconnue :"+e);
		}
		try {
				sc = new Socket(hote,port);
				in=new BufferedReader(new InputStreamReader(sc.getInputStream()));
				out=new PrintWriter(sc.getOutputStream(),true);
				String rep=null;
				String req=null;
				//Identification
				req="Consume";
				out.println(req);
				//Attente de reponse
				while(repi==null)
				{
					repi=in.readLine();
					if (repi.equals("Ack"))
					{
						consume(sc,in,out,req,rep);
						break;
					}
					else if (repi.equals("Rej")) //Is rejected
					{
						break;
					}
				}

				
				sleep(2000);
				req = "Bye";
				out.println(req);
				System.out.println("End the connection with the server");

		}catch(IOException e){ System.err.println("Impossible de creer la socket du client : " +e);}
		finally{
			try{
					sc.close();
			}
			catch(IOException e) {}
		}
	}
		
	public static void consume(Socket sc, BufferedReader in, PrintWriter out, String req,String rep) throws IOException
	{
		req="Let me consume the message produced ";
		out.println(req);
		rep=in.readLine();
		flow.add(rep);
	}
	
}
