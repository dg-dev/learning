import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;

public class BeatBox {
	JFrame frame = new JFrame("BeatBox");
	JPanel labelPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel checkBoxPanel = new JPanel();
	JButton startButton = new JButton("Start");
	JButton stopButton = new JButton("Stop");
	JButton upTempoButton = new JButton("UP Tempo");
	JButton downTempoButton = new JButton("DOWN Tempo");
	
	public void open() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// labels
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		for (int i = 0; i < 16; i++)
			labelPanel.add(new Label("Drum #" + i));
		frame.getContentPane().add(labelPanel, BorderLayout.WEST);
		// checkboxes
		checkBoxPanel.setLayout(new GridLayout(16, 16));
		for (int i = 0; i < 256; i++) {
			checkBoxPanel.add(new JCheckBox());
		}
		frame.getContentPane().add(checkBoxPanel, BorderLayout.CENTER);
		// buttons
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(upTempoButton);
		buttonPanel.add(downTempoButton);
		frame.getContentPane().add(buttonPanel, BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		BeatBox box = new BeatBox();
		box.open();
	}
}
