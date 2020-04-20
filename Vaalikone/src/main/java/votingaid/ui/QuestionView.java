/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.ui;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import votingaid.domain.Candidate;
import votingaid.domain.CandidateLogic;
import votingaid.domain.Question;
import votingaid.domain.QuestionList;

/**
 *
 * @author mlkul
 */
public class QuestionView {
    GraphicUI gui;
    Question question;
    CandidateLogic candidateLogic;
    List<Candidate> results;
    
    
    public QuestionView(GraphicUI gui, Question question, CandidateLogic candidateLogic) {
        this.gui = gui;
        this.question = question;
        this.candidateLogic = candidateLogic;
    }
    
    public Scene getScene() {
        Label qNumber = new Label(this.question.getId() + "/25"); //luetaan  numero question-listasta
        qNumber.setTextAlignment(TextAlignment.CENTER);
        qNumber.setPrefHeight(15);
        qNumber.setMaxHeight(15);
        qNumber.setTextFill(Color.WHITE);
        Color cYellow = Color.rgb(200,80,10);
        CornerRadii corner10 = new CornerRadii(10);
        qNumber.setBackground(new Background(new BackgroundFill(cYellow, corner10, Insets.EMPTY)));
        qNumber.setPadding(new Insets(10, 10, 10, 10));
        
        //kysymyksen asettelu
        Label lbQuestion = new Label(this.question.getQuestionText());  //luetaan kysymys questions-listasta
        lbQuestion.setTextAlignment(TextAlignment.CENTER);
        lbQuestion.setWrapText(true);
        lbQuestion.setMaxWidth(500);
        lbQuestion.setPadding(new Insets(10, 10, 10, 10));
        //lbQuestion.setAlignment(Pos.CENTER);
        lbQuestion.setFont(new Font("Arial", 20));
        
        //valintojen selitykset
        Label lb1 = new Label("Täysin\neri mieltä");
        Label lb2 = new Label("Osittain\neri mieltä");
        Label lb3 = new Label("En osaa\nsanoa");
        Label lb4 = new Label("Osittain\nsamaa\nmieltä");
        Label lb5 = new Label("Täysin\nsamaa\nmieltä");
        lb1.setTextAlignment(TextAlignment.CENTER); //tekstin keskitys
        lb2.setTextAlignment(TextAlignment.CENTER);
        lb3.setTextAlignment(TextAlignment.CENTER);
        lb4.setTextAlignment(TextAlignment.CENTER);
        lb5.setTextAlignment(TextAlignment.CENTER);
        
        //valintapainikkeet
        RadioButton rb1 = new RadioButton();
        RadioButton rb2 = new RadioButton();
        RadioButton rb3 = new RadioButton();
        RadioButton rb4 = new RadioButton();
        RadioButton rb5 = new RadioButton();
        ToggleGroup rButtons = new ToggleGroup();
        rb1.setToggleGroup(rButtons);
        rb2.setToggleGroup(rButtons);
        rb3.setToggleGroup(rButtons);
        rb4.setToggleGroup(rButtons);
        rb5.setToggleGroup(rButtons);
        
        //tulokset 
        Label lbResults = new Label();
        lbResults.setWrapText(true);

        //valintanappien toiminnot
        rb1.setOnAction((event) -> {
            listAnswers(1, lbResults);
//            if (!this.question.isAnswered()) {
//                this.question.setUserAnswer(1);
//                this.questionList.addAnsweredQuestion();
//                        
//                candidateLogic.compareAndListAnswers(1, this.question.getId());
//            }
//            question.setAnswered();
        });
        rb2.setOnAction((event) -> {
            listAnswers(2, lbResults);
        });
        rb3.setOnAction((event) -> {
            listAnswers(3, lbResults);
        });
        rb4.setOnAction((event) -> {
            listAnswers(4, lbResults);
        });
        rb5.setOnAction((event) -> {
            listAnswers(5, lbResults);
        });
        
        
        //edellinen/seuraava -napit
        Button previous = new Button("< Edellinen");
        Button next = new Button("Seuraava >");
        previous.setPrefWidth(80);
        next.setPrefWidth(80);
        
        previous.setOnAction((event) -> {
            //
        });
        
        next.setOnAction((event) -> {
            //
        });


        //asettelu
        GridPane questionView = new GridPane();
        for (int i=1; i <= 5; i++) {
            questionView.getColumnConstraints().add(new ColumnConstraints(80));
        } 
        
        
        questionView.add(qNumber, 0, 0);
        questionView.add(lbQuestion, 0, 1);

        questionView.add(lb1, 0, 2);
        questionView.add(lb2, 1, 2);
        questionView.add(lb3, 2, 2);
        questionView.add(lb4, 3, 2);
        questionView.add(lb5, 4, 2);
        
        questionView.add(rb1, 0, 3);
        questionView.add(rb2, 1, 3);
        questionView.add(rb3, 2, 3);
        questionView.add(rb4, 3, 3);
        questionView.add(rb5, 4, 3);
        questionView.add(lbResults, 1, 4);
        questionView.add(previous, 0, 4);
        questionView.add(next, 4, 4);
        
        GridPane.setHalignment(qNumber, HPos.CENTER);
        GridPane.setHalignment(lb1, HPos.CENTER);
        GridPane.setHalignment(lb2, HPos.CENTER);
        GridPane.setHalignment(lb3, HPos.CENTER);
        GridPane.setHalignment(lb4, HPos.CENTER);
        GridPane.setHalignment(lb5, HPos.CENTER);
        GridPane.setHalignment(rb1, HPos.CENTER);
        GridPane.setHalignment(rb2, HPos.CENTER);
        GridPane.setHalignment(rb3, HPos.CENTER);
        GridPane.setHalignment(rb4, HPos.CENTER);
        GridPane.setHalignment(rb5, HPos.CENTER);
        GridPane.setHalignment(lbResults, HPos.CENTER);
        GridPane.setColumnSpan(qNumber, 5);
        GridPane.setColumnSpan(lbQuestion, 5);
        GridPane.setColumnSpan(lbResults, 3);
        
        questionView.setPrefSize(700, 400);
        questionView.setAlignment(Pos.CENTER);
        questionView.setVgap(10);
        questionView.setHgap(10);
        questionView.setPadding(new Insets(50, 20, 20, 20));
        
        return new Scene(questionView);
    }
    
    public void listAnswers(int answer, Label label) {
        this.results = this.candidateLogic.compareToCandidates(answer, this.question.getId());
        String candList = "";
        for (Candidate x : this.results) {
            candList = candList + x.toString() + "\n";
        }
        label.setText(candList);
    }
}
