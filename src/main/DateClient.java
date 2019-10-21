package main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class DateClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		if (args.length != 1) {
			System.err.println("Pass the server IP as the sole command line argument");
			return;
		}
		var socket = new Socket(args[0], 59091);
		var in = new Scanner(socket.getInputStream());
		System.out.println("Server response: " + in.nextLine());

	}
}
