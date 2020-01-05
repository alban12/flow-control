package fr.dauphine.sar.flowcontrol;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Thread {
	protected static String repi=null;
	protected static ArrayList<String> flow = new ArrayList<String>();
}
