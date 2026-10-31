
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

					BufferedWriter output = new BufferedWriter(
							new OutputStreamWriter(client.getOutputStream())
					);

					String line = input.readLine();

					if (line == null) {
						continue;
					}

					String[] parts = line.split(" ");

					String method = parts[0];
					String path = parts[1];
					String version = parts[2];

					int contentLength = 0;

					while ((line = input.readLine()) != null && !line.isEmpty()) {
						System.out.println(line);

						// 3. Extract Content-Length
						if (line.startsWith("Content-Length:")) {
							String[] headerParts = line.split(":");
							contentLength = Integer.parseInt(headerParts[1].trim());
						}
					}

					// 4. Read POST body (your next task)
					if (method.equals("POST")) {
						String message = "";

						// Read contentLength bytes from the request body here.
					}

					// 5. Determine response
					String body;
					String status;

					if (method.equals("GET") && path.equals("/about")) {
						body = "This is the about page!";
						status = "200 OK";
					} else if (method.equals("GET") && path.equals("/home")) {
						body = "This is the home page!";
						status = "200 OK";
					} else {
						body = "Not Found";
						status = "404 Not Found";
					}

					// 6. Send response
					output.write("HTTP/1.1 " + status + "\r\n");
					output.write("Content-Type: text/plain\r\n");
					output.write("Content-Length: " + body.length() + "\r\n");
					output.write("\r\n");
					output.write(body);

					output.flush();
				}
			}
		}
	}
}
