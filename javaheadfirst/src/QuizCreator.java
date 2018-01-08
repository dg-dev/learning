import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Dimension;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class QuizCreator {
	private JFrame frame;
	private JTextArea question;
	private JTextArea answer;
	private ArrayList<QuizQuestion> questionSet;
	
	public QuizCreator() {
		question = null;
		answer = null;
		questionSet = new ArrayList<QuizQuestion>();
	}
	
	public void start() {
		frame = new JFrame("QuizCreator");
		JPanel mainPanel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel questionLabel = new JLabel("Question:");
		mainPanel.add(questionLabel);

		question = new JTextArea();
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		JScrollPane questionScroll = new JScrollPane(question);
		questionScroll.setPreferredSize(new Dimension(350, 175));
		questionScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		questionScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainPanel.add(questionScroll);

		JLabel answerLabel = new JLabel("Answer:");
		mainPanel.add(answerLabel);
		
		answer = new JTextArea();
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		JScrollPane answerScroll = new JScrollPane(answer);
		answerScroll.setPreferredSize(new Dimension(350, 175));
		answerScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		answerScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainPanel.add(answerScroll);
		
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new NextButtonListener());
		mainPanel.add(nextButton);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem.addActionListener(new NewMenuListener());
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new SaveMenuListener());
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

		frame.setSize(400, 525);
		frame.setVisible(true);
		
		question.requestFocus();
	}
	
	private void clearText() {
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}
	
	private void saveQuestionSet(File file) {
		if (file.exists())
			return;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for (QuizQuestion questionItem : questionSet)
				writer.write(questionItem.getQuestion() + "|" + questionItem.getAnswer() + System.lineSeparator());
			writer.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR writing to " + file.getName());
		}
	}
	
	public static void main(String[] args) {
		QuizCreator qc = new QuizCreator();
		qc.start();
	}

	class NewMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			questionSet.clear();
			clearText();
		}
	}
	
	class SaveMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			JFileChooser fileChooser = new JFileChooser();
			if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(frame))
				saveQuestionSet(fileChooser.getSelectedFile());
		}
	}
	
	class NextButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if (!(question.getText().isEmpty()) && !(answer.getText().isEmpty())) {
				QuizQuestion newQuestion = new QuizQuestion(question.getText(), answer.getText());
				questionSet.add(newQuestion);
				clearText();
			}
		}
	}
}
