import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class SimpleChatClient {
	private JFrame frame;
	private JTextArea chatDisplay;
	private JTextField sendText;
	private Socket sock;
	private PrintWriter writer;
	private BufferedReader reader;
	
	public void go() {
		this.gui();
		this.network();
	}
	
	private void gui() {
		JScrollPane chatDisplayScroll;
		JPanel sendPanel;
		JButton sendButton;
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
		sendText.addActionListener(new SendButtonListener());
		sendPanel.add(sendText, BorderLayout.CENTER);
		sendButton = new JButton("SEND");
		sendButton.addActionListener(new SendButtonListener());
		sendPanel.add(sendButton, BorderLayout.EAST);
		frame.add(sendPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		while (!sendText.hasFocus())
			sendText.requestFocus();
	}
	
	private void network() {
		try {
			sock = new Socket("127.0.0.1", 8888);
			writer = new PrintWriter(sock.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			Thread chatReader = new Thread(new ChatReaderRunnable());
			chatReader.start();
			chatDisplay.append("STATUS: connected to " + sock.toString() + "\n");
		} catch (Exception e) {
			chatDisplay.append("ERROR: " + e.getMessage() + "\n");
		}
	}
	
	public static void main(String[] args) {
		SimpleChatClient client = new SimpleChatClient();
		client.go();
	}
	
	private class SendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			sendText.requestFocus();
			if ((sock == null) || !(sock.isConnected()) || (sock.isClosed())) {
				chatDisplay.append("ERROR: Not connected\n");
				sendText.setText("");
				return;
			}
			if (sendText.getText().equals(""))
				return;
			writer.println(sendText.getText());
			writer.flush();
			chatDisplay.append("< " + sendText.getText() + "\n");
			sendText.setText("");
		}
	}
	
	private class ChatReaderRunnable implements Runnable {
		public void run() {
			String line = null;
			if ((sock == null) || !(sock.isConnected()) || (sock.isClosed()))
				return;
			try {
				while ((line = reader.readLine()) != null)
					chatDisplay.append("> " + line + "\n");
				reader.close();
				chatDisplay.append("STATUS: disconnected from " + sock.toString() + "\n");
			} catch (IOException e) {
				chatDisplay.append("ERROR: " + e.getMessage());
			}
		}
	}
}
