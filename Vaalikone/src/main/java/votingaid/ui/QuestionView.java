/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.ui;

import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import votingaid.domain.AnswerList;
import votingaid.domain.CandidateLogic;
import votingaid.domain.Question;

/**
 * QuestionView shows each question and the answer options.
 * @author mlkul
 */
public class QuestionView {
    UI ui;
    Question question;
    int listSize;
    CandidateLogic candidateLogic;
    List<AnswerList> results;
    
    /**
     * Construct
     * @param ui parent ui.
     * @param question the question in turn to be answered.
     * @param listSize amount of questions.
     */
    public QuestionView(UI ui, Question question, int listSize, CandidateLogic candidateLogic) {
        this.ui = ui;
        this.question = question;
        this.listSize = listSize;
        this.candidateLogic = candidateLogic;
    }
    
    public Scene getScene() {
        //5x1 gridpane kysymyksenasettelulle
        GridPane questionView = createLayoutForQuestions();
        
        Label qNumber = createLabelForQNumber();
        Label lbQuestion = createLabelForQuestion(); 
        
        Label lb1 = new Label("Täysin\neri mieltä");
        Label lb2 = new Label("Osittain\neri mieltä");
        Label lb3 = new Label("En osaa\nsanoa");
        Label lb4 = new Label("Osittain\nsamaa\nmieltä");
        Label lb5 = new Label("Täysin\nsamaa\nmieltä");
        lb1.setTextAlignment(TextAlignment.CENTER); 
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
        Button prevButton = createButtonForPreviousQuestion();
        Button nextButton = createButtonForNextQuestion();
        Button resultsButton = createButtonForShowResults();

        
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
        
        
        //luodaan toinen 3x1 gridpane napeille
        GridPane buttonView = createLayoutForButtons();

        buttonView.add(lbResults, 1, 0);  
        if (this.question.getId() != 1) {
            buttonView.add(prevButton, 0, 0);
        }
        if (this.question.getId() != listSize) {
            buttonView.add(nextButton, 2, 0);
        } else {
            buttonView.add(resultsButton, 2, 0);
        }
        
        
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
        GridPane.setValignment(lbResults, VPos.TOP);
        GridPane.setHalignment(prevButton, HPos.LEFT);
        GridPane.setValignment(prevButton, VPos.TOP);
        GridPane.setHalignment(nextButton, HPos.RIGHT);
        GridPane.setValignment(nextButton, VPos.TOP);
        GridPane.setHalignment(resultsButton, HPos.RIGHT);
        GridPane.setColumnSpan(qNumber, 5);
        GridPane.setColumnSpan(lbQuestion, 5);
        
        //questionView.setPrefSize(700, 400);
        questionView.setAlignment(Pos.CENTER);
        questionView.setVgap(10);
        questionView.setHgap(10);
        questionView.setPadding(new Insets(10, 20, 20, 10));
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(questionView, buttonView);
        vbox.setPrefSize(700, 500);
        vbox.setAlignment(Pos.CENTER);
        
        return new Scene(vbox );
    }
    
    public void listAnswers(int answer, Label label) {
        //System.out.println(this.question.getId());
        this.results = this.candidateLogic.compareToCandidateAnswers(this.question.getId(), answer);
        String candList = "";
        for (AnswerList x : this.results) {
            candList = candList + x.toString() + "\n";
        }
        label.setText(candList);
    }
    
    public GridPane createLayoutForQuestions() {
        GridPane questionLayout = new GridPane();
        for (int i = 1; i <= 5; i++) {
            questionLayout.getColumnConstraints().add(new ColumnConstraints(80));
        } 
        questionLayout.getRowConstraints().add(new RowConstraints(80));
        questionLayout.getRowConstraints().add(new RowConstraints(100));
        questionLayout.getRowConstraints().add(new RowConstraints(50));
        questionLayout.getRowConstraints().add(new RowConstraints(30));
        
        return questionLayout;
    }
    
    public GridPane createLayoutForButtons() {
        GridPane buttonLayout = new GridPane();
        buttonLayout.getColumnConstraints().add(new ColumnConstraints(120));
        buttonLayout.getColumnConstraints().add(new ColumnConstraints(120));
        buttonLayout.getColumnConstraints().add(new ColumnConstraints(120));
        buttonLayout.getRowConstraints().add(new RowConstraints(100));
        buttonLayout.setAlignment(Pos.TOP_CENTER);
        return buttonLayout;
    }
    
    public Label createLabelForQNumber() {
        Label qNumber = new Label(this.question.getId() + "/" +  listSize); //questionList.getSize() ???
        qNumber.setTextAlignment(TextAlignment.CENTER); //tekstin keskitys
        qNumber.setPrefHeight(15);
        qNumber.setMaxHeight(15);
        qNumber.setTextFill(Color.WHITE);
        Color colorA = Color.rgb(200, 80, 5);
        CornerRadii corner10 = new CornerRadii(10);
        qNumber.setBackground(new Background(new BackgroundFill(colorA, corner10, Insets.EMPTY)));
        qNumber.setPadding(new Insets(10, 10, 10, 10));  //laatikon marginaalit
        
        return qNumber;
    }
    
    public Label createLabelForQuestion() {
        Label lbQuestion = new Label(this.question.getQuestionText());
        lbQuestion.setTextAlignment(TextAlignment.CENTER);
        lbQuestion.setWrapText(true);
        lbQuestion.setMaxWidth(500);
        lbQuestion.setPadding(new Insets(10, 10, 10, 10));
        lbQuestion.setFont(new Font("Arial", 20));
        
        return lbQuestion;
    }
    
    public Button createButtonForPreviousQuestion() {
        Button prevButton = new Button("< Edellinen");
        prevButton.setPrefWidth(80);
        prevButton.setOnAction((event) -> {
            ui.showPreviousQuestion();
        });
        return prevButton;
    }
    
    public Button createButtonForNextQuestion() {
        Button nextButton = new Button("Seuraava >");
        nextButton.setPrefWidth(80);
        nextButton.setOnAction((event) -> {
            ui.showNextQuestion();
        });
        return nextButton;
    }
    
    public Button createButtonForShowResults() {
        Button resultsButton = new Button("Siirry tuloksiin");
        resultsButton.setPrefWidth(100);
        
        resultsButton.setOnAction((event) -> {
            ui.showFinalResults();
        });
        return resultsButton;
    }
    
    
    
}
