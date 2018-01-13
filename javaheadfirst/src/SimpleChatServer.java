import java.net.*;
import java.io.*;
import java.util.HashSet;

public class SimpleChatServer {
	private HashSet<Socket> connectedClients;
	
	public SimpleChatServer() {
		connectedClients = new HashSet<Socket>();
	}
	
	private void addClient(Socket sock) {
		connectedClients.add(sock);
		System.out.println("connected clients: " + connectedClients);
	}
	
	private void removeClient(Socket sock) {
		connectedClients.remove(sock);
		System.out.println("connected clients: " + connectedClients);
	}
	
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
			this.sock = s;
			try {
				reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				addClient(sock);
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
					broadcast(line);
				}
				reader.close();
			} catch (SocketException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
			removeClient(sock);
			System.out.println("disconnected " + sock.toString());
		}
		
		public void broadcast(String message) {
			for (Socket cSock : connectedClients) {
				if (this.sock == cSock)
					continue;
				try {
					PrintWriter writer = new PrintWriter(cSock.getOutputStream());
					writer.println(message);
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}
