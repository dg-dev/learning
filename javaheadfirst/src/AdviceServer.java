import java.net.*;
import java.io.*;

public class AdviceServer {

	public static void main(String[] args) {
		try {
			ServerSocket sSock = new ServerSocket(8888);
			System.out.println("listening on port 8888...");
			Socket sock = null;
			while ((sock = sSock.accept()) != null) {
				System.out.println("client connected, " + sock.toString());
				PrintWriter writer = new PrintWriter(sock.getOutputStream());
				writer.println("wot up wot up");
				writer.close();
				sock.close();
				System.out.println("client disconnected, " + sock.toString());
			}
			sSock.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
