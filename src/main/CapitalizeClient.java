package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CapitalizeClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		if (args.length != 1) {
			System.out.println("Pass the server IP as the cmd line argument");
			return;
		}
		try(var socket = new Socket(args[0],59898)){
			System.out.println("Enter lines of text then Ctrl C to quit");
			var scanner = new Scanner(System.in);
			var in = new Scanner(socket.getInputStream());
			var out = new PrintWriter(socket.getOutputStream(), true);
			while(scanner.hasNextLine()) {
				out.println(scanner.nextLine());
				System.out.println(in.nextLine());
			}
			
		}
	}
}
