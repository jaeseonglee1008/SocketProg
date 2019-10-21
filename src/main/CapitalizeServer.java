package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
//import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author jason
 *
 */
public class CapitalizeServer {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
//		localport open. port might be various
		try (var listener = new ServerSocket(59898)) {

			System.out.println("The capitalization server is running...");
//			limited number of threads that acceptable
			var pool = Executors.newFixedThreadPool(3);
			while (true) {
				pool.execute(new Capitalizer(listener.accept()));
			}

		}

	}

	/**
	 * @author jason
	 *
	 */
	private static class Capitalizer implements Runnable {

		private Socket socket;

		/**
		 * @param socket
		 * 
		 */
		Capitalizer(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Connected: " + socket);
			try {
				var in = new Scanner(socket.getInputStream());
				var out = new PrintWriter(socket.getOutputStream(), true);
				while (in.hasNextLine()) {
					out.println(in.nextLine().toUpperCase());
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error: " + socket);

			} finally {
				try {
					socket.close();
				} catch (Exception e2) {
					System.out.println("Closed:" + socket);
					// TODO: handle exception
				}
			}

		}

	}

}
