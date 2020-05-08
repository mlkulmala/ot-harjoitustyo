package votingaid.domain;

/**
 * A class representing a question in this voting aid.
 * @author mlkul
 */
public class Question {
    
    private int id;
    private String questionText;
    private boolean answered;
    private int userAnswer;
    
    public Question(int id, String questionText) {
        this.id = id;
        this.questionText = questionText;
        this.answered = false;
        this.userAnswer = 0;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getQuestionText() {
        return this.questionText;
    }
    
    public boolean isAnswered() {
        return answered;
    }
    
    public void setAnswered() {
        this.answered = true;
    }
    
    public int getUserAnswer() {
        return this.userAnswer;
    }
    
    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
        this.answered = true;
    }
    
}
