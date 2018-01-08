import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class QuizPlayer {
	private JFrame frame;
	private JButton button;
	private JTextArea display;
	private ArrayList<QuizQuestion> questionSet;
	private boolean isQuestion;
	private int questionIndex;
	
	public void start() {
		frame = new JFrame("QuizPlayer");
		JPanel mainPanel = new JPanel();
		display = new JTextArea();
		display.setLineWrap(true);
		display.setWrapStyleWord(true);
		display.setEditable(false);
		JScrollPane scrollDisplay = new JScrollPane(display);
		scrollDisplay.setPreferredSize(new Dimension(300,175));
		scrollDisplay.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollDisplay.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(scrollDisplay);
		button = new JButton("...");
		button.setEnabled(false);
		button.addActionListener(new ButtonListener());
		mainPanel.add(button);
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem loadMenuItem = new JMenuItem("Load");
		loadMenuItem.addActionListener(new LoadListener());
		fileMenu.add(loadMenuItem);
		fileMenu.addSeparator();
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ExitListener());
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 325);
		frame.setVisible(true);
		display.requestFocus();
	}
	
	public QuizPlayer() {
		questionSet = new ArrayList<QuizQuestion>();
		isQuestion = false;
		questionIndex = 0;
	}
	
	private void loadQuestions(File file) {
		if (!file.exists())
			return;
		display.setText("");
		questionSet.clear();
		questionIndex = 0;
		isQuestion = false;
		try {
			String line = null;
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				QuizQuestion qq = new QuizQuestion();
				String[] questionAnswer = line.split("\\|");
				qq.setQuestion(questionAnswer[0]);
				qq.setAnswer(questionAnswer[1]);
				questionSet.add(qq);
			}
			br.close();
		} catch (Exception e) { }
		if (questionSet.size() > 0) {
			isQuestion = false;
			button.setText("Next");
			button.setEnabled(true);
		}
	}
	
	public static void main(String[] args) {
		QuizPlayer player = new QuizPlayer();
		player.start();
	}
	
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if (isQuestion) {
				isQuestion = false;
				display.setText(questionSet.get(questionIndex).getAnswer());
				questionIndex++;
				if (questionIndex >= questionSet.size()) {
					button.setText("...");
					button.setEnabled(false);
				} else {
					button.setText("Next");
				}
			} else {
				isQuestion = true;
				display.setText(questionSet.get(questionIndex).getQuestion());
				button.setText("Answer");
			}
		}
	}
	
	class LoadListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			JFileChooser fs = new JFileChooser();
			if (fs.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
				loadQuestions(fs.getSelectedFile());
		}
	}
	
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			System.exit(0);
		}
	}
}
