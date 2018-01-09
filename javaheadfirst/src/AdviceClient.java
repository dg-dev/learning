import java.io.*;
import java.net.*;

public class AdviceClient {
	public static void main(String[] args) {
		try {
			Socket sock = new Socket("127.0.0.1", 8888);
			System.out.println("connected to " + sock.toString());
			BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null)
				System.out.println("> " + line);
			reader.close();
			sock.close();
			System.out.println("disconnected from " + sock.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
