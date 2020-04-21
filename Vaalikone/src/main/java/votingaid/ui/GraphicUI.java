/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.ui;


import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import votingaid.dao.CandidateMemoryDao;
import votingaid.dao.QuestionMemoryDao;
import votingaid.domain.Candidate;
import votingaid.domain.CandidateLogic;
import votingaid.domain.Question;
import votingaid.domain.QuestionList;

/**
 *
 * @author mlkul
 */
public class GraphicUI extends Application {
    
    private Stage stage;
    private CandidateLogic candidateLogic;
    private QuestionList questionList;
    
//    @Override
//    public void init() throws Exception {
//        QuestionMemoryDao questionDao = new QuestionMemoryDao();
//        this.questionList = new QuestionList(questionDao);
//        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
//        this.candidatelogic = new CandidateLogic(candMemoryDao);
//        
//    } 
    
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
    
    
    public void initializeLists(String area) {
        QuestionMemoryDao questionMemoryDao = new QuestionMemoryDao(); 
        this.questionList = new QuestionList(questionMemoryDao);
        
        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
        this.candidateLogic = new CandidateLogic(candMemoryDao, area);
        candidateLogic.createCandidateList();
    }
    
    public void showFirstQuestion() {
        Question firstQuestion = this.questionList.getCurrent();
        QuestionView questionView = new QuestionView(this, firstQuestion, this.candidateLogic);
        setScene(questionView.getScene());
    }
    
    public void showQuestion(Question question, CandidateLogic candidateLogic) {   
        QuestionView questionView = new QuestionView(this, question, candidateLogic);
        setScene(questionView.getScene());
    }
    
    public void showNextQuestion() {
        Question next = questionList.getNext();
        showQuestion(next, this.candidateLogic);
    }
    
    public void showPreviousQuestion() {
        Question previous = questionList.getPrevious();
        showQuestion(previous, candidateLogic);
    }
    
    public void showWelcomeView() {
        WelcomeView welcomeView = new WelcomeView(this);
        setScene(welcomeView.getScene());
    }
    
    //setScene(questionList.getNext().getScene())
    
    
    
   
}
