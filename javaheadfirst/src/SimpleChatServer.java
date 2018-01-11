import java.net.*;
import java.io.*;

public class SimpleChatServer {
	public void go() {
		try {
			Socket cSock = null;
			ServerSocket sSock = null;
			sSock = new ServerSocket(8888);
			System.out.println("listening " + sSock.toString());
			while ((cSock = sSock.accept()) != null) {
				Thread clientReader = new Thread(new ChatReaderRunnable(cSock));
				clientReader.start();	
			}
			sSock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SimpleChatServer server = new SimpleChatServer();
		server.go();
	}
	
	private class ChatReaderRunnable implements Runnable {
		private BufferedReader reader;
		private Socket sock;
		
		public ChatReaderRunnable(Socket s) {
			sock = s;
			try {
				reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				System.out.println("connected " + sock.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					System.out.println("> " + line);
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("disconnected " + sock.toString());
		}
	}
}
