/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import votingaid.dao.CandidateMemoryDao;
import votingaid.dao.QuestionMemoryDao;
import votingaid.domain.Candidate;
import votingaid.domain.CandidateLogic;

/**
 *
 * @author mlkul
 */
public class GraphicUI extends Application {
    
    private Scanner scanner;
    private List<String> questions;
    private CandidateLogic candidatelogic;
    private List<Candidate> candidates;
    private int c;
    
    @Override
    public void init() throws Exception {
        this.scanner = new Scanner(System.in);
        QuestionMemoryDao questionDao = new QuestionMemoryDao();
        this.questions = questionDao.getQuestions();
        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
        this.candidatelogic = new CandidateLogic(candMemoryDao);
        this.candidates = new ArrayList<>();
        this.c = 1;
        
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Label questionLabel = new Label(this.questions.get(0));  //luetaan kysymys listasta
        //TextFlow! 
        //textflow.setMaxWidth(500);
        HBox cbLabels = new HBox();
        cbLabels.setSpacing(50);
        cbLabels.getChildren().add(new Label("Täysin\neri mieltä"));
        cbLabels.getChildren().add(new Label("Osittain\neri mieltä"));
        cbLabels.getChildren().add(new Label("En osaa\nsanoa"));
        cbLabels.getChildren().add(new Label("Osittain\nsamaa\nmieltä"));
        cbLabels.getChildren().add(new Label("Täysin\nsamaa\nmieltä"));
        
        CheckBox cb1 = new CheckBox();
        CheckBox cb2 = new CheckBox();
        CheckBox cb3 = new CheckBox();
        CheckBox cb4 = new CheckBox();
        CheckBox cb5 = new CheckBox();
        
        HBox checkBoxes = new HBox();
        checkBoxes.setSpacing(70);
        checkBoxes.getChildren().addAll(cb1, cb2, cb3, cb4, cb5);
        
        Button previous = new Button("Previous");
        Button next = new Button("Next");
        
        
        GridPane grid = new GridPane();
        grid.add(questionLabel, 0, 0);
        grid.add(cbLabels, 0, 1);
        grid.add(checkBoxes, 0, 2);
        
        grid.setPrefSize(600, 400);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));
        
        Scene questionScene = new Scene(grid);
        
        //BorderPane borderPane = new BorderPane();
        
        stage.setScene(questionScene);
        stage.show();
    }
    
//    public static void main(String[] args) {
//        launch(GraphicUI.class);
//    }
}
