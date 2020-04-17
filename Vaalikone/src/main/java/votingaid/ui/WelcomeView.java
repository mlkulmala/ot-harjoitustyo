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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import votingaid.dao.CandidateMemoryDao;
import votingaid.dao.QuestionMemoryDao;
import votingaid.domain.Candidate;
import votingaid.domain.CandidateLogic;
import votingaid.domain.Question;

/**
 *
 * @author mlkul
 */
public class WelcomeView {
    
    GraphicUI gui;
    CandidateLogic candidatelogic;
    List<Candidate> candidates;
    
    public WelcomeView(GraphicUI gui) {
        this.gui = gui;
    }
    
    public Scene getScene() {
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
        
        startButton.setOnAction(action -> {
            CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
            CandidateLogic candidatelogic = new CandidateLogic(candMemoryDao);
            this.candidates = this.candidatelogic.createCandidateList(); //tähän tulisi tfArea.getText() parametriksi?
            
            gui.showQuestion();
        });
        
        return new Scene(welcomeView);
    }
    
    
}
