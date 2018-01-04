public class QuizQuestion {
	private String question;
	private String answer;
	
	public QuizQuestion() {
		this.question = "";
		this.answer = "";
	}
	
	public QuizQuestion(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public String getAnswer() {
		return this.answer;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
