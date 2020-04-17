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

/**
 *
 * @author mlkul
 */
public class GraphicUI extends Application {
    
    private Stage stage;
    private List<Question> questions;
    private CandidateLogic candidatelogic;
    private List<Candidate> candidates;
    
    @Override
    public void init() throws Exception {
        QuestionMemoryDao questionDao = new QuestionMemoryDao();
        this.questions = questionDao.getQuestions();
        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
        this.candidatelogic = new CandidateLogic(candMemoryDao);
        this.candidates = new ArrayList<>();
        
    } 
    
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
    
    public void showQuestion() {   //(Question question)
        QuestionView questionView = new QuestionView(this);
        setScene(questionView.getScene());
        
    }
    
    public void showWelcomeView() {
        WelcomeView welcomeView = new WelcomeView(this);
        setScene(welcomeView.getScene());
    }
    
   
}
