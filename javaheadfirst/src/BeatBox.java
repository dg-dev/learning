import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;

public class BeatBox {
	JFrame frame = new JFrame("BeatBox");
	JPanel buttonPanel = new JPanel();
	JButton startButton = new JButton("Start");
	JButton stopButton = new JButton("Stop");
	JButton upTempoButton = new JButton("UP Tempo");
	JButton downTempoButton = new JButton("DOWN Tempo");
	
	public void open() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		// labels & checkboxes
		JPanel beatsPanel = new JPanel();
		beatsPanel.setLayout(new BoxLayout(beatsPanel, BoxLayout.Y_AXIS));
		for (int i = 0; i < 16; i++) {
			JPanel labelAndCheckBoxPanel = new JPanel();
			labelAndCheckBoxPanel.setLayout(new BorderLayout());
			labelAndCheckBoxPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			labelAndCheckBoxPanel.add(new JLabel("Drum #" + i), BorderLayout.WEST);
			for (int j = 0; j < 16; j++) {
				JPanel checkBoxPanel = new JPanel();
				checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.X_AXIS));
				checkBoxPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
				for (int k = 0; k < 16; k++)
					checkBoxPanel.add(new JCheckBox());
				labelAndCheckBoxPanel.add(checkBoxPanel, BorderLayout.EAST);
			}
			beatsPanel.add(labelAndCheckBoxPanel);
		}
		mainPanel.add(beatsPanel, BorderLayout.WEST);
		// texteditarea
		JTextArea debugArea = new JTextArea("testing, testing... 1 2 3 testing, testing... 1 2 3 testing, testing... 1 2 3");
		debugArea.setLineWrap(true);
		debugArea.setWrapStyleWord(true);
		debugArea.setPreferredSize(new Dimension(350,300));
		JScrollPane myScrollPane = new JScrollPane(debugArea);
		myScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		myScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		mainPanel.add(myScrollPane, BorderLayout.CENTER);
		// buttons
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(upTempoButton);
		buttonPanel.add(downTempoButton);
		mainPanel.add(buttonPanel, BorderLayout.EAST);
		// display
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		BeatBox box = new BeatBox();
		box.open();
	}
}
