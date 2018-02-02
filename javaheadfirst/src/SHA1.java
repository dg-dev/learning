import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class SHA1 {
	public static void main(String[] args) {
		String msg = "Have a nice fucking life.";
		Formatter sha1 = new Formatter();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA1");
			digest.update(msg.getBytes("UTF8"));
			for (byte c : digest.digest())
				sha1.format("%02x", c);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(sha1);
	}
}
