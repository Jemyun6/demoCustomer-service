package assignment3;

import java.io.*;
import java.net.Socket;

public class client2 extends client {
	public client2(String name) {
		super(name);
		// generated constructor
	}

	public static void main(String[] args) {
		client2 c2 = new client2("Client 2");
		// create client 2
		try {
			socket = new Socket("localhost", 9998);

			c2.connect();
		} catch (Exception e) {

		}

	}
}
