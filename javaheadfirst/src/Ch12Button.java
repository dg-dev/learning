import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class Ch12Button {
	private JFrame frame;
	private JButton button1;
	private JButton button2;
	private JPanel panel;
	private JLabel label;
	private Color color;
	private int x, y;
	
	public void go() {
		frame = new JFrame("Booyah");
		button1 = new JButton("CLICK");
		button2 = new JButton("WEE");
		panel = new MyDrawingPanel();
		label = new JLabel("testing 123");
		color = new Color(0, 0, 0);
		button1.addActionListener(new MyBottomButtonListener());
		button2.addActionListener(new MyLeftButtonListener());
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.SOUTH, button1);
		frame.getContentPane().add(BorderLayout.EAST, button2);
		frame.getContentPane().add(BorderLayout.WEST, label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
		for (int i = 0; i < 100; i++) {
			frame.repaint();
			try {
				Thread.sleep(50);
			} catch (Exception exc) { }
			x++;
			y++;
		}
	}
	
	class MyBottomButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
		}	
	}
	
	class MyLeftButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			label.setText("RANDOM NUMBER " + (int) (Math.random() * 256));
		}	
	}
	
	class MyDrawingPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(color);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(new Color(123, 213, 231));
			g.fillOval(x, y, 25, 25);
		}
	}
	
	public static void main(String[] args) {
		Ch12Button bro = new Ch12Button();
		bro.go();
	}
}