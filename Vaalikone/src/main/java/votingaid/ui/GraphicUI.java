/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import votingaid.dao.CandidateMemoryDao;
import votingaid.dao.QuestionMemoryDao;
import votingaid.domain.Candidate;
import votingaid.domain.CandidateLogic;

/**
 *
 * @author mlkul
 */
public class GraphicUI extends Application {
    
    private Properties properties;
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
        
//        this.properties = new Properties();
//        FileInputStream f = new FileInputStream("config.properties");
//        this.properties.load(new InputStreamReader(f, Charset.forName("UTF-8")));
        
    } 
    
    @Override
    public void start(Stage stage) throws Exception {
        Label lbWelcome = new Label("Tervetuloa vaalikoneeseen!");
        lbWelcome.setTextAlignment(TextAlignment.CENTER);
        lbWelcome.setAlignment(Pos.CENTER);
        lbWelcome.setWrapText(true);
        lbWelcome.setMaxWidth(400);
        lbWelcome.setFont(new Font("Arial", 20));
        
        Label lbIntro = new Label("Vaalikone auttaa sinua tutustumaan "
                + "eduskuntavaalien ehdokkaisiin. Vastaa väitteisiin ja "
                + "vaalikone kertoo, keiden näkemykset ovat lähellä omiasi.\n\n"
                + "Aloita kertomalla kotikuntasi.");
        lbIntro.setTextAlignment(TextAlignment.CENTER);
        lbIntro.setWrapText(true);
        lbIntro.setMaxWidth(400);
        
        TextField tfArea = new TextField();
        
        Button startButton = new Button("Aloita");
        GridPane.setHalignment(startButton, HPos.CENTER);
        
        GridPane welcomeView = new GridPane();
        welcomeView.add(lbWelcome, 0, 0);
        welcomeView.add(lbIntro, 0, 1);
        welcomeView.add(tfArea, 0, 2);
        welcomeView.add(startButton, 0, 3);
        welcomeView.setColumnSpan(lbWelcome, 5);
        welcomeView.setColumnSpan(lbIntro, 5);
        welcomeView.setColumnSpan(tfArea, 5);
        welcomeView.setColumnSpan(startButton, 5);
        
        welcomeView.setPrefSize(700, 400);
        welcomeView.setAlignment(Pos.CENTER);
        welcomeView.setVgap(10);
        welcomeView.setHgap(20);
        welcomeView.setPadding(new Insets(30, 20, 20, 20));

        
        //kysymyksen numero
        Label qNumber = new Label("1/25");
        qNumber.setTextAlignment(TextAlignment.CENTER);
        //qNumber.setAlignment(Pos.CENTER);
        qNumber.setPrefHeight(15);
        qNumber.setMaxHeight(15);
        qNumber.setTextFill(Color.WHITE);
        //qNumber.setShape(arg0);
        Color cYellow = Color.rgb(200,80,10);
        CornerRadii corner10 = new CornerRadii(10);
        qNumber.setBackground(new Background(new BackgroundFill(cYellow, corner10, Insets.EMPTY)));
        qNumber.setPadding(new Insets(10, 10, 10, 10));
        
        //kysymyksen asettelu
        Label question = new Label(this.questions.get(0));  //luetaan kysymys listasta
        question.setTextAlignment(TextAlignment.CENTER);
        question.setWrapText(true);
        question.setMaxWidth(500);
        question.setPadding(new Insets(10, 10, 10, 10));
        //question.setAlignment(Pos.CENTER);
        question.setFont(new Font("Arial", 20));
        
        
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
            compareAndListAnswers(1, lbResults);
        });
        rb2.setOnAction((event) -> {
            compareAndListAnswers(2, lbResults);
        });
        rb3.setOnAction((event) -> {
            compareAndListAnswers(3, lbResults);
        });
        rb4.setOnAction((event) -> {
            compareAndListAnswers(4, lbResults);
        });
        rb5.setOnAction((event) -> {
            compareAndListAnswers(5, lbResults);
        });
        
        
        //edellinen/seuraava -napit
        Button previous = new Button("< Edellinen");
        Button next = new Button("Seuraava >");
        previous.setPrefWidth(80);
        next.setPrefWidth(80);
//        HBox buttons = new HBox();
//        buttons.getChildren().addAll(previous, next);
//        buttons.setSpacing(10);
//        buttons.setAlignment(Pos.CENTER);
//        buttons.setPadding(new Insets(30, 10, 10, 10));
        
        
        
        //asettelu
        GridPane questionView = new GridPane();
        for (int i=1; i <= 5; i++) {
            questionView.getColumnConstraints().add(new ColumnConstraints(80));
        } 
        
        
        questionView.add(qNumber, 0, 0);
        questionView.add(question, 0, 1);

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
        GridPane.setColumnSpan(question, 5);
        GridPane.setColumnSpan(lbResults, 3);
        //GridPane.setColumnSpan(buttons, 5);
        
        questionView.setPrefSize(700, 400);
        questionView.setAlignment(Pos.CENTER);
        questionView.setVgap(10);
        questionView.setHgap(10);
        questionView.setPadding(new Insets(50, 20, 20, 20));
        
        Scene questionScene = new Scene(questionView);
        Scene welcomeScene = new Scene(welcomeView);
        
        startButton.setOnAction(action -> {
            CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
            CandidateLogic candidatelogic = new CandidateLogic(candMemoryDao);
            this.candidatelogic.createCandidateList(); //tähän tulisi tfArea.getText() parametriksi?
            stage.setScene(questionScene);
        });
        
        stage.setScene(welcomeScene);
        //stage.setScene(questionScene);
        stage.show();
    }
    
    public void compareAndListAnswers(int number, Label label) {
        this.candidates = this.candidatelogic.compareToCandidates(c, number);
        String candList = "";
        for (Candidate x : this.candidates) {
            candList = candList + x.toString() + "\n";
            //System.out.println(x.toString());
        }
        label.setText(candList);
        c++;
    }
    
//    public static void main(String[] args) {
//        launch(GraphicUI.class);
//    }
}
