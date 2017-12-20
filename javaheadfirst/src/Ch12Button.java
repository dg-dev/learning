import javax.swing.*;
import java.awt.event.*;

public class Ch12Button implements ActionListener {
	private JFrame frame;
	private JButton button;
	
	public void go() {
		frame = new JFrame("Booyah");
		button = new JButton("CLICK");
		button.addActionListener(this);
		frame.getContentPane().add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);	
	}
	
	public void actionPerformed(ActionEvent e) {
		button.setText("CLICKED");
		frame.setSize(320, 240);
	}
	
	public static void main(String[] args) {
		Ch12Button bro = new Ch12Button();
		bro.go();
	}
}