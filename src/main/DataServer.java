package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author jason
 *
 *lsof -wni tcp:59091  <---this checks whether the port is open or not.
 *then check the PID and type this to kill ->> kill -9 PID

 */
public class DataServer {
	public static void main(String[] args) throws IOException {
		try (var listener = new ServerSocket(59091)) {
			System.out.println("The date server is running...");
			while (true) {
				try (var socket = listener.accept()) {
					var out = new PrintWriter(socket.getOutputStream(), true);
					out.println(new Date().toString());
				}
			}

		}

	}

}
