package assignment3;

import java.net.*;
import java.io.*;
import java.util.*;

public class serverThread extends Thread {
	static Socket socket;
	static ArrayList token = new ArrayList(10);// maximum capacity of ten different tokens

	public serverThread(Socket socket) {
		super();
		this.socket = socket;

	}

	public boolean check(Object o) {// check if the token is in the list
		if (token.contains(o)) {// make sure that there are no duplicates in the list
			return false;
		} else
			return true;

	}

	@Override
	public synchronized void run() {
		try { //

			while (true) {
				int countClient = 0;// to count the number of clients

				//
				System.out.println("Server: client connected");
				InputStream ins = socket.getInputStream();
				OutputStream os = socket.getOutputStream();// gets outputStream in response to client's request
				PrintWriter pw = new PrintWriter(os);
				Scanner in = new Scanner(ins);
				String tokens = "";
				String info = "";
				// loop for getting client's information
				while (in.hasNextLine()) {
					countClient++;
					info = in.nextLine();
					if (!info.equals("QUIT")) {
						if (info.startsWith("SUBMIT")) {

							tokens = info.substring(6);// add the words after "SUBMIT " into the tokenList
							if (token.size() < 10) {

								if (check(tokens) == true) {// call the check() method,make sure that there are no
															// duplicates in the list
									token.add(tokens);
									Collections.sort(token);// sort the list in order of uppercase(A-Z) to
															// lowercase(a-z)
									System.out.println("Server: received message from  client " + " : " + info + "'");
									String ok = " ok ";
									pw.println(ok); // response from the server
									pw.flush();
									System.out.println("Add new token: " + tokens);
								} else {// (list is full and token is not yet in the list,send "ERROR" to client
									String error = " ERROR ";
									pw.println(error);
									pw.flush();
									System.out.println("ERROR : the token is in the list");
								}
							}
							if (token.size() >= 10) {
								String error = " ERROR ";// send error to client if submitting more than 10 tokens
								pw.println(error);
								pw.flush();
								System.out.println("ERROR :List is full  ");
							}
						}

						if (info.equals("RETRIEVE")) {
							if (!token.isEmpty()) {// Reply with the sorted global list of tokens currently on the
													// server
								Collections.sort(token);
								pw.println("Server: recieved RETRIEVE from CLIENT" + countClient);
								pw.println("token: ");
								System.out.print("tokens: ");
								for (Object object1 : token) {
									System.out.print(" " + object1);

								}
								System.out.println(" ");
								pw.println(token.toString());
								pw.flush();
								// response from the server
							} else {// . If the global tokens list is currently empty, reply with ERROR.
								pw.println("ERROR");
								pw.flush();
								System.out.println("ERROR: the tokensList is empty");

							}
						}
					} else {
						// closing the connection
						pw.close();
						in.close();
						ins.close();
						os.close();
						socket.close();
						System.out.println("connecting " + countClient + " clients");
						System.out.println("Server: connection to  client closed");
					}
				}
			}
		} catch (Exception e) {
			// handle exception
		}
	}

}
