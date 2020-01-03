package fr.dauphine.sar.flowcontrol;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Communication extends Thread{
	
	private Socket socket;
	public Communication(Socket s) {
		this.socket = s;
	}
	@Override
	public void run() {
		try {
    		InputStream is = socket.getInputStream();
    		InputStreamReader isr = new InputStreamReader(is);
    		BufferedReader br = new BufferedReader(isr);
    		
    		
    		OutputStream os = socket.getOutputStream();
    		PrintWriter pw = new PrintWriter(os);
    		String IP = socket.getRemoteSocketAddress().toString();
    		System.out.println("Client connexion: "+IP);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		
	}

}
