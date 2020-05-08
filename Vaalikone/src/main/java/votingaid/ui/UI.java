/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.ui;


import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import votingaid.dao.CandidateMemoryDao;
import votingaid.dao.QuestionMemoryDao;
import votingaid.domain.Candidate;
import votingaid.domain.CandidateInfo;
import votingaid.domain.CandidateLogic;
import votingaid.domain.Question;
import votingaid.domain.QuestionList;

/**
 *
 * @author mlkul
 */
public class UI extends Application {
    
    private Stage stage;
    private CandidateLogic candidateLogic;
    private QuestionList questionList;
    private CandidateInfo candidateInfo;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Voting Aid");
        this.stage = stage;
        showWelcomeView();
        stage.show();
    }
    
    /**
     * Change active scene of this UI.
     * @param scene Scene to be shown.
     */
    public void setScene(Scene scene) {
        stage.setScene(scene);
    }
    
    
    public void initializeLists(String district) throws IOException, SQLException {
        QuestionMemoryDao questionMemoryDao = new QuestionMemoryDao(); 
        this.questionList = new QuestionList(questionMemoryDao);
        this.questionList.getQuestions();
        
        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
        this.candidateLogic = new CandidateLogic(candMemoryDao);
        
        this.candidateLogic.createAnswerLists(district);
    }
    
    /**
     * Show question view with given question.
     * @param question Question to be shown
     * @param label Label where three best candidate matches are shown. 
     * The text is updated on every answer selection the user makes.
     */
    public void showQuestion(Question question, Label label, CandidateLogic candidateLogic) { 
        int size = this.questionList.getSize();
        QuestionView questionView = new QuestionView(this, question, size, label, candidateLogic);
        setScene(questionView.getScene());
    }
    
    /**
     * Show the first question.
     */
    public void showFirstQuestion() {
        Question firstQuestion = this.questionList.getCurrent();
        Label label = new Label();
        showQuestion(firstQuestion, label, this.candidateLogic);
    }
    
    /**
     * Show next question in turn.
     * @param label Label with updated top results.
     */
    
    public void showNextQuestion(Label label) {
        Question next = questionList.getNext();
        showQuestion(next, label, this.candidateLogic);
    }
    
    /**
     * Show the previous question
     * @param label Label with updated top results.
     */
    public void showPreviousQuestion(Label label) {
        Question previous = questionList.getPrevious();
        showQuestion(previous, label, candidateLogic);
    }
    
    /**
     * Show the first view with welcoming text and choice of the district.
     */
    public void showWelcomeView() {
        WelcomeView welcomeView = new WelcomeView(this);
        setScene(welcomeView.getScene());
    }
    
    /**
     * Show the view with results.
     * @param current An index pointing to the first result on the list 
     * in the active scene.
     */
    public void showFinalResults(int current) {
        ResultsView resultsView = new ResultsView(this, this.candidateLogic, current);
        setScene(resultsView.getScene());
    }
    
    /**
     * Show rhe view with additional information about the candidate and his opinions.
     * @param candidate Election candidate whose information is shown.
     * @param current An index pointing to the first result on the list in the previous scene.
     */
    public void showCandidateView(Candidate candidate, int current) {
        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
        
        CandidateInfo info;
        try {
            candMemoryDao.getConnection();
            info = candMemoryDao.getCandidateInfo(candidate);
            candMemoryDao.close();
        } catch (Exception e) {
            info = new CandidateInfo(candidate.getId());
        }
        
        CandidateView candidateView = new CandidateView(this, candidate, current, info);
        
        setScene(candidateView.getScene());
    }
    
    /**
     * Launch this application.
     * @param args CLI arguments.
     */ 
    public static void main(String[] args) {
        launch(args);
    }
}
