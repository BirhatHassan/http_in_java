package http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	public static void main(String[] args) throws IOException {
		try (ServerSocket serverSocket = new ServerSocket(8080)) {
			while (true) {
				try (Socket client = serverSocket.accept()) {
					BufferedReader input = new BufferedReader(
							new InputStreamReader(client.getInputStream())
					);
//					BufferedWriter output = new BufferedWriter(
//							new OutputStreamWriter(new OutputStreamWriter(client.getOutputStream()))
//					);

					String line = input.readLine();

					while (line != null && !line.isEmpty()) {
						System.out.println(line);
						line = input.readLine();
					}

				}
			}
		}
	}
}