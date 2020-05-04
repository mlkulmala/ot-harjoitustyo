/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import votingaid.domain.AnswerList;
import votingaid.domain.Candidate;
import votingaid.domain.CandidateLogic;
import votingaid.ui.UI;

/**
 *
 * @author mlkul
 */
public class ResultsView {
    UI ui;
    CandidateLogic candidateLogic;
    
    public ResultsView(UI ui, CandidateLogic candidateLogic) {
        this.ui = ui;
        this.candidateLogic = candidateLogic;
    }
    
    public Scene getScene() {
        
        Label lbTitle = createRoundedLabel("ENITEN SAMAA MIELTÄ KANSSASI");
        
        GridPane resultsLayout = new GridPane();
        resultsLayout.getColumnConstraints().add(new ColumnConstraints(250));
        resultsLayout.getColumnConstraints().add(new ColumnConstraints(150));
        resultsLayout.setVgap(15);
        
        
        AnswerList a1 = this.candidateLogic.getAnswerList(0);
        Candidate c1 = a1.getCandidate();
        VBox candidate1 = createLayoutForCandidate(c1.getNumber(), c1.getName(), c1.getParty());
        Label lbMatch1 = createLabelForMatchPercentage(a1.getMatchPercentage());
        
        AnswerList a2 = this.candidateLogic.getAnswerList(1);
        Candidate c2 = a2.getCandidate();
        VBox candidate2 = createLayoutForCandidate(c2.getNumber(), c2.getName(), c2.getParty());
        Label lbMatch2 = createLabelForMatchPercentage(a2.getMatchPercentage());
        
        AnswerList a3 = this.candidateLogic.getAnswerList(2);
        Candidate c3 = a3.getCandidate();
        VBox candidate3 = createLayoutForCandidate(c3.getNumber(), c3.getName(), c3.getParty());
        Label lbMatch3 = createLabelForMatchPercentage(a3.getMatchPercentage());
        
        
        resultsLayout.add(lbTitle, 0, 0);
        resultsLayout.add(candidate1, 0, 1);
        resultsLayout.add(lbMatch1, 1, 1);
        resultsLayout.add(candidate2, 0, 2);
        resultsLayout.add(lbMatch2, 1, 2);
        resultsLayout.add(candidate3, 0, 3);
        resultsLayout.add(lbMatch3, 1, 3);

        
        GridPane.setHalignment(lbTitle, HPos.CENTER);
        GridPane.setValignment(lbTitle, VPos.CENTER);
        GridPane.setColumnSpan(lbTitle, 3);
        GridPane.setValignment(candidate1, VPos.TOP);
        GridPane.setColumnSpan(candidate1, 2);
        GridPane.setValignment(candidate2, VPos.TOP);
        GridPane.setColumnSpan(candidate2, 2);
        GridPane.setValignment(candidate3, VPos.TOP);
        GridPane.setColumnSpan(candidate3, 2);
        
        GridPane.setValignment(lbMatch1, VPos.CENTER);
        GridPane.setValignment(lbMatch2, VPos.CENTER);
        GridPane.setValignment(lbMatch3, VPos.CENTER);
        
        resultsLayout.setPrefSize(700, 500);
        resultsLayout.setAlignment(Pos.CENTER);
        
        
        return new Scene(resultsLayout);
    }
    public VBox createLayoutForCandidate(int number, String name, String party) {
        VBox candidateBox = new VBox(10);
        
        Label lbCandidateName = new Label(number + " " + name);
        lbCandidateName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        
        Label lbParty = createRoundedLabel(party);
        
        Button infoButton = new Button("Tutustu ehdokkaaseen");
        infoButton.setPrefWidth(150);
        
        candidateBox.getChildren().addAll(lbCandidateName, lbParty, infoButton);
        
        return candidateBox;
    }
    
    public Label createLabelForMatchPercentage(int percent) {
        Label lbMatchPercentage = new Label(percent + "%");
        lbMatchPercentage.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        lbMatchPercentage.setPadding(new Insets(10, 0, 10, 0));
        lbMatchPercentage.setTextAlignment(TextAlignment.LEFT);
        
        return lbMatchPercentage;
    }
    
//    public GridPane createLayoutForCandidate(int number, String name, String party, int percent) {
//        GridPane candidateLayout = new GridPane();
//        candidateLayout.getColumnConstraints().add(new ColumnConstraints(250));
//        candidateLayout.getColumnConstraints().add(new ColumnConstraints(150));
//        
//        Label lbCandidateName = new Label(number + " " + name);
//        lbCandidateName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
//        
//        Label lbParty = createRoundedLabel(party);
//        
//        Button infoButton = new Button("Tutustu ehdokkaaseen");
//        infoButton.setPrefWidth(150);
//        //tiedot asetettu VBoxiin
//        VBox candidateBox = new VBox(10);
//        candidateBox.getChildren().addAll(lbCandidateName, lbParty, infoButton);
//        //label prosentille
//        Label lbMatchPercentage = new Label(percent + "%");
//        lbMatchPercentage.setFont(Font.font("Arial", FontWeight.BOLD, 60));
//        lbMatchPercentage.setPadding(new Insets(10, 0, 10, 0));
//        lbMatchPercentage.setTextAlignment(TextAlignment.LEFT);
//        //asetetaan Gridpaneen
//        candidateLayout.add(candidateBox, 0, 0);
//        candidateLayout.add(lbMatchPercentage, 0, 1);
//        GridPane.setValignment(candidateLayout, VPos.TOP);
//        GridPane.setValignment(lbMatchPercentage, VPos.CENTER);
//        
//        
//        return candidateLayout;
//    }
    
    public Label createRoundedLabel(String text) {
        Label label = new Label(text); //questionList.getSize() ???
        label.setTextAlignment(TextAlignment.CENTER); //tekstin keskitys
//        label.setPrefHeight(15);
//        label.setMaxHeight(15);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        label.setTextFill(Color.WHITE);
        CornerRadii corner10 = new CornerRadii(15);
        Color color = chooseColorByParty(text);
        label.setBackground(new Background(new BackgroundFill(color, corner10, Insets.EMPTY)));
        label.setPadding(new Insets(5, 8, 5, 8));  //laatikon marginaalit
        
        return label;
    }
    
    public Color chooseColorByParty(String party) {
        Color color = Color.DODGERBLUE;
        switch (party) {
            case "KOK":
                color = Color.ROYALBLUE;
                break;
            case "VIHR":
                color = Color.LIMEGREEN;
                break;
            case "SDP":
                color = Color.RED;
                break;
            case "KESK":
                color = Color.GREEN;
                break;
            case "KD":
                color = Color.CORNFLOWERBLUE;
                break;
            case "RKP":
                color = Color.GOLD;
                break;
            case "PS":
                color = Color.DEEPSKYBLUE;
                break;
            case "VAS":
                color = Color.FIREBRICK;
                break;
            case "SKP":
                color = Color.SLATEGRAY;
                break;
            case "FP":
                color = Color.HOTPINK;
                break;
        }
        return color;
    }
}
