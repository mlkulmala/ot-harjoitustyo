/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.ui;


import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import votingaid.dao.CandidateMemoryDao;
import votingaid.dao.QuestionMemoryDao;
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
    
    
    public void initializeLists(String district) throws IOException {
        QuestionMemoryDao questionMemoryDao = new QuestionMemoryDao(); 
        this.questionList = new QuestionList(questionMemoryDao);
        
        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
        this.candidateLogic = new CandidateLogic(candMemoryDao);
        
        try {
            this.candidateLogic.createAnswerList(district);
        } catch (SQLException ex) {
            //mitä tässä pitäisi olla????
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void showFinalResults(int next) {
        ResultsView resultsView = new ResultsView(this, this.candidateLogic, next);
        setScene(resultsView.getScene());
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
