package assignment3;

import java.net.*;
import java.io.*;
import java.util.*;

public class client {
	String name;
	// create clientSocket and assign the hostAddress and portNumber
	static Socket socket;

	public client(String name) {
		super();
		this.name = name;
	}

	public static void main(String[] args) {
		client c1 = new client("client 1");
		// create a client instance
		try {
			socket = new Socket("localhost", 9998);
			c1.connect();// connect the socket
		} catch (Exception e) {

		}

	}

	public void connect() {

		try {

			System.out.println(this.name + "  connected to server");
			//// Gets the inputStream and reads the server-side response information
			// acquire inputStream .and send messages to the server
			InputStream is = socket.getInputStream();
			Scanner in = new Scanner(is);
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);// Wrap the outputStream as a printStream

			while (true) {

				Scanner s = new Scanner(System.in);
				while (s.hasNextLine()) {
					String m1 = s.nextLine();
					pw.println(m1);
					pw.flush();
					System.out.println(this.name + " : sent message to server");
					if ("QUIT".equals(m1)) {// Ends the connection to that client.
						pw.flush();
						s.close();
						in.close();
						is.close();
						pw.close();
						os.close();
						socket.close();
						// closes resources
						System.out.println(this.name + " : closed connection to server");
						break;
					}

					String info = "";
					if (in.hasNextLine()) {
						// Loop for the client's information
						info = in.nextLine();
						System.out.println(this.name + " : received message from server  " + info);
					}
				}

			}
		} catch (Exception e) {
			// handle exception
		}
	}

}
