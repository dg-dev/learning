import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleChatClient {
	JFrame frame;
	
	public void gui() {
		JTextArea chatDisplay;
		JScrollPane chatDisplayScroll;
		JPanel sendPanel;
		JTextField sendText;
		JButton sendButton;
		frame = new JFrame("SimpleChatClient");
		chatDisplay = new JTextArea();
		chatDisplay.setPreferredSize(new Dimension(400, 300));
		chatDisplay.setEditable(false);
		chatDisplay.setLineWrap(true);
		chatDisplay.setWrapStyleWord(true);
		chatDisplayScroll = new JScrollPane(chatDisplay);
		chatDisplayScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatDisplayScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frame.add(chatDisplayScroll, BorderLayout.CENTER);
		sendPanel = new JPanel();
		sendPanel.setLayout(new BorderLayout());
		sendText = new JTextField(30);
		sendPanel.add(sendText, BorderLayout.CENTER);
		sendButton = new JButton("SEND");
		sendPanel.add(sendButton, BorderLayout.EAST);
		frame.add(sendPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SimpleChatClient client = new SimpleChatClient();
		client.gui();
	}
}
