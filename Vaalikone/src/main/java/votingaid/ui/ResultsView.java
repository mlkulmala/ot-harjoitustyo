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
        
        VBox resultsLayout = new VBox(10);
        GridPane candidateLayout = createLayoutForCandidate();
        
        //ehdokkaan tiedot
        Label lbCandidateName = new Label("123 Heli Vihervaara");
        lbCandidateName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        Label lbParty = createRoundedLabel("VIHR");
        Button infoButton = new Button("Tutustu ehdokkaaseen");
        infoButton.setPrefWidth(150);
        //tiedot asetettu VBoxiin
        VBox candidateBox = new VBox(10);
        candidateBox.getChildren().addAll(lbCandidateName, lbParty, infoButton);
        //label prosentille
        Label lbMatchPercentage = new Label("89%");
        lbMatchPercentage.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        lbMatchPercentage.setPadding(new Insets(10, 0, 10, 0));
        lbMatchPercentage.setTextAlignment(TextAlignment.LEFT);
        //asetetaan Gridpaneen
        candidateLayout.add(candidateBox, 0, 0);
        candidateLayout.add(lbMatchPercentage, 0, 1);
        
        //2.ehdokas
        VBox candidateLayout2 = new VBox(10);
        Label lbCandidate2 = new Label("246 Lasse Lähtökuoppa");
        lbCandidate2.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        //lbCandidate2.setPadding(new Insets(20, 0, 20, 0));
        
        Label lbParty2 = createRoundedLabel("VAS");
        Label lbMatchPercentage2 = new Label("72%");
        lbMatchPercentage2.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        lbMatchPercentage2.setPadding(new Insets(10, 0, 10, 0));
        lbMatchPercentage2.setTextAlignment(TextAlignment.RIGHT);
        
        Button infoButton2 = new Button("Tutustu ehdokkaaseen");
        infoButton.setPrefWidth(150);
        
        candidateLayout2.getChildren().addAll(lbCandidate2, lbParty2, infoButton2);
        
        //3.ehdokas
        VBox candidateLayout3 = new VBox(10);
        Label lbCandidate3 = new Label("355 Jorma Johtaja");
        lbCandidate3.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        //lbCandidate2.setPadding(new Insets(20, 0, 20, 0));
        
        Label lbParty3 = createRoundedLabel("KOK");
        Label lbMatchPercentage3 = new Label("65%");
        lbMatchPercentage3.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        lbMatchPercentage3.setPadding(new Insets(10, 0, 10, 0));
        lbMatchPercentage3.setTextAlignment(TextAlignment.RIGHT);
        
        Button infoButton3 = new Button("Tutustu ehdokkaaseen");
        infoButton.setPrefWidth(150);
        
        candidateLayout2.getChildren().addAll(lbCandidate3, lbParty3, infoButton3);
        
       
        
        
//        resultsLayout.add(lbTitle, 0, 0);
//        
//        resultsLayout.add(candidateLayout, 0, 1);
//        resultsLayout.add(lbMatchPercentage, 1, 1);
//        
//        resultsLayout.add(candidateLayout2, 0, 2);
//        resultsLayout.add(lbMatchPercentage2, 1, 2);
//        
//        resultsLayout.add(candidateLayout3, 0, 3);
//        resultsLayout.add(lbMatchPercentage3, 1, 3);
        
        //resultsLayout.add(line, 0, 2);
        GridPane.setHalignment(lbTitle, HPos.CENTER);
        GridPane.setValignment(lbTitle, VPos.CENTER);
        GridPane.setColumnSpan(lbTitle, 3);
        GridPane.setValignment(candidateLayout, VPos.TOP);
        GridPane.setColumnSpan(candidateLayout, 2);
        GridPane.setValignment(candidateLayout2, VPos.TOP);
        GridPane.setColumnSpan(candidateLayout2, 2);
        GridPane.setValignment(candidateLayout3, VPos.TOP);
        GridPane.setColumnSpan(candidateLayout3, 2);
        
        GridPane.setValignment(lbMatchPercentage, VPos.CENTER);
        GridPane.setValignment(lbMatchPercentage2, VPos.CENTER);
        GridPane.setValignment(lbMatchPercentage3, VPos.CENTER);
        
        
        
        //GridPane.setColumnSpan(line, 3);
        
        return new Scene(resultsLayout);
    }
    
    public GridPane createLayoutForCandidate() {
        GridPane candidateLayout = new GridPane();
        
        candidateLayout.getColumnConstraints().add(new ColumnConstraints(250));
        candidateLayout.getColumnConstraints().add(new ColumnConstraints(150));
        
        return candidateLayout;
    }
    
    public Label createRoundedLabel(String text) {
        Label label = new Label(text); //questionList.getSize() ???
        label.setTextAlignment(TextAlignment.CENTER); //tekstin keskitys
        label.setPrefHeight(15);
        label.setMaxHeight(15);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        label.setTextFill(Color.WHITE);
        CornerRadii corner10 = new CornerRadii(15);
        Color color = chooseColorByParty(text);
        label.setBackground(new Background(new BackgroundFill(color, corner10, Insets.EMPTY)));
        label.setPadding(new Insets(10, 0, 0, 10));  
        
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
