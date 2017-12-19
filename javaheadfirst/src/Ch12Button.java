import javax.swing.*;
import java.awt.event.*;

public class Ch12Button {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Booyah");
		JButton button = new JButton("CLICK");
		frame.getContentPane().add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}
}