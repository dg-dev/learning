import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleChatClient {
	private JFrame frame;
	private JTextArea chatDisplay;
	private JTextField sendText;
	
	public void go() {
		this.gui();
		this.network();
	}
	
	private void gui() {
		JScrollPane chatDisplayScroll;
		JPanel sendPanel;
		JButton sendButton;
		SendButtonListener sendButtonListener = new SendButtonListener();
		frame = new JFrame("SimpleChatClient");
		chatDisplay = new JTextArea();
		chatDisplay.setEditable(false);
		chatDisplay.setLineWrap(true);
		chatDisplay.setWrapStyleWord(true);
		chatDisplayScroll = new JScrollPane(chatDisplay);
		chatDisplayScroll.setPreferredSize(new Dimension(400, 300));
		chatDisplayScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatDisplayScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frame.add(chatDisplayScroll, BorderLayout.CENTER);
		sendPanel = new JPanel();
		sendPanel.setLayout(new BorderLayout());
		sendText = new JTextField();
		sendText.addActionListener(sendButtonListener);
		sendPanel.add(sendText, BorderLayout.CENTER);
		sendButton = new JButton("SEND");
		sendButton.addActionListener(sendButtonListener);
		sendPanel.add(sendButton, BorderLayout.EAST);
		frame.add(sendPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		while (!sendText.hasFocus())
			sendText.requestFocus();
	}
	
	private void network() {
		
	}
	
	public static void main(String[] args) {
		SimpleChatClient client = new SimpleChatClient();
		client.go();
	}
	
	private class SendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			sendText.requestFocus();
			if (sendText.getText().equals(""))
				return;
			chatDisplay.append("> " + sendText.getText() + "\n");
			sendText.setText("");
		}
	}
}
