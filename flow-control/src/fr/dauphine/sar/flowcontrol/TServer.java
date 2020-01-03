package fr.dauphine.sar.flowcontrol;

import java.io.*;
import java.net.*;

public class TServer  extends Thread{
	
	private Object[] tampon = new Object[N];
	private static final int N=0;
	public static final int port = 1234;
	
	public TServer()
	{
		
	}
	
	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Server start");
			
			while(true) {
				Socket s = ss.accept();
				new Communication(s).start();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
