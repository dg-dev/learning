import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleChatClient {
	JFrame frame;
	
	public void gui() {
		frame = new JFrame("SimpleChatClient");
		JTextArea chatDisplay = new JTextArea();
		chatDisplay.setPreferredSize(new Dimension(455, 300));
		chatDisplay.setEditable(false);
		chatDisplay.setLineWrap(true);
		chatDisplay.setWrapStyleWord(true);
		JScrollPane chatDisplayScroll = new JScrollPane(chatDisplay);
		chatDisplayScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatDisplayScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frame.add(chatDisplayScroll, BorderLayout.CENTER);
		JPanel sendPanel = new JPanel();
		sendPanel.setLayout(new BorderLayout());
		JTextField sendText = new JTextField(30);
		sendPanel.add(sendText, BorderLayout.CENTER);
		JButton sendButton = new JButton("SEND");
		sendPanel.add(sendButton, BorderLayout.EAST);
		frame.add(sendPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SimpleChatClient client = new SimpleChatClient();
		client.gui();
	}
}
