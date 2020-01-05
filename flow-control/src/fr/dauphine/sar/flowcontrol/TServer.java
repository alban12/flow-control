package fr.dauphine.sar.flowcontrol;

import java.io.*;
import java.net.*;


public class TServer  extends Thread{
	
	private static Buffer tampon;
	private int nbmess;
	private int in;
	private int out;
	private static int n=5;
	
	public static void main(String[] args)
	{
		if (args.length>1)
		{
			n=Integer.parseInt(args[0]);
		}
		tampon = new Buffer(n);
		int port=4020;
		ServerSocket se;
		Socket ssv=null;
		PrintWriter out;
		BufferedReader in;

		try{
			se = new ServerSocket(port);
			System.out.println("Le serveur est à l'écoute");
			while(true) {
				ssv = se.accept(); //Création d'une nouvelle socket pour le nouveau client 
				System.out.println("Demande de connexion acceptée");
				ThreadClient th = new ThreadClient(ssv,tampon); //Création d'un nouveau thread pour le nouveau client 
				th.start();
			}
		}
		catch(IOException e) { System.err.println("Erreur : "+e);}
		finally{
			try{
				ssv.close();
			}
			catch(IOException e){}
		}
		System.out.println("Fin de communication");
	}
}
