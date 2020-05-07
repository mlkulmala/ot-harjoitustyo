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
import votingaid.dao.CandidateInfoDao;
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
    
    
    public void showFirstQuestion() {
        Question firstQuestion = this.questionList.getCurrent();
        int size = this.questionList.getSize();
        Label label = new Label();
        QuestionView questionView = new QuestionView(this, firstQuestion, size, label, this.candidateLogic);
        setScene(questionView.getScene());
    }
    
    public void showQuestion(Question question, Label label, CandidateLogic candidateLogic) { 
        int size = this.questionList.getSize();
        QuestionView questionView = new QuestionView(this, question, size, label, candidateLogic);
        setScene(questionView.getScene());
    }
    
    public void showNextQuestion(Label label) {
        Question next = questionList.getNext();
        showQuestion(next, label, this.candidateLogic);
    }
    
    public void showPreviousQuestion(Label label) {
        Question previous = questionList.getPrevious();
        showQuestion(previous, label, candidateLogic);
    }
    
    public void showWelcomeView() {
        WelcomeView welcomeView = new WelcomeView(this);
        setScene(welcomeView.getScene());
    }
    
    public void showFinalResults(int current) {
        ResultsView resultsView = new ResultsView(this, this.candidateLogic, current);
        setScene(resultsView.getScene());
    }
    
    public void showCandidateView(Candidate candidate, int next) {
        CandidateInfoDao candidateInfoDao = new CandidateInfoDao();
        
        CandidateInfo info;
        try {
            info = candidateInfoDao.getCandidateInfo(candidate);
        } catch (SQLException ex) {
            info = new CandidateInfo(candidate.getId());
        }
        
        CandidateView candidateView = new CandidateView(this, candidate, next, info);
        
        setScene(candidateView.getScene());
    }
    
     
    
    public static void main(String[] args) {
        launch(args);
    }
}
