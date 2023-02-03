package assignment3;
import java.net.*;
import java.io.*;
import java.util.*;
import java.io.*;
 public class server {
	 
    
   static ArrayList token0=new ArrayList(10);

	public static void main(String[] args) {
		ServerSocket server;
		try {
			//create a serverSocket"9998"
			 server = new ServerSocket(9998);
			 Socket socket=null; 
			 System.out.println("Server: waiting for a client to connect");
			 //
			 while (true) {
				socket= server.accept();
				System.out.println("Server: client connected");
				new Thread(new serverThread(socket)).start();//create a new anonymous thread
				//serverThread serverThread1 =new serverThread(socket );
				//serverThread1.start(); 
				
			 }
		} catch (Exception e) {
			//  handle exception
		}
		
	}
}
 