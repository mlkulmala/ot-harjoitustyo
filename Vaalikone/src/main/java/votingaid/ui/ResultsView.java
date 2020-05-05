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
import javafx.scene.layout.HBox;
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
    int next;
    GridPane resultsLayout;
    
    public ResultsView(UI ui, CandidateLogic candidateLogic, int next) {
        this.ui = ui;
        this.candidateLogic = candidateLogic;
        this.next = next;
    }
    
    public Scene getScene() {
        resultsLayout = createGridPaneForResults();
        
        Label lbTitle = createRoundedLabel("ENITEN SAMAA MIELTÄ KANSSASI");
        resultsLayout.add(lbTitle, 0, 0);
        GridPane.setHalignment(lbTitle, HPos.CENTER);
        GridPane.setValignment(lbTitle, VPos.BOTTOM);
        GridPane.setColumnSpan(lbTitle, 3);
        
        int i = 1;
        while (next < this.candidateLogic.getCountOfAllAnswerLists()) {
            addCandidateToGrid(next, i);
            next++;
            i++;
            if (i > 3) {
                break;
            }
        }

        Button nextButton = createButtonForNextPage(next);
        Button startButton = createButtonForNewStart();
        GridPane buttons = new GridPane();
        buttons.getColumnConstraints().add(new ColumnConstraints(100));
        buttons.getColumnConstraints().add(new ColumnConstraints(100));
        
        if (next < this.candidateLogic.getCountOfAllAnswerLists()) {
            buttons.add(nextButton, 0, 0);
        }  
        buttons.add(startButton, 1, 0);
        buttons.setVgap(10);
        //buttons.getChildren().addAll(nextButton, startButton);
        resultsLayout.add(buttons, 0, 4);
        
        return new Scene(resultsLayout);
    }
    
    public GridPane createGridPaneForResults() {
        resultsLayout = new GridPane();
        resultsLayout.getColumnConstraints().add(new ColumnConstraints(200));
        resultsLayout.getColumnConstraints().add(new ColumnConstraints(180));
        resultsLayout.getRowConstraints().add(new RowConstraints(80));
        resultsLayout.getRowConstraints().add(new RowConstraints(100));
        resultsLayout.getRowConstraints().add(new RowConstraints(100));
        resultsLayout.getRowConstraints().add(new RowConstraints(100));
        resultsLayout.getRowConstraints().add(new RowConstraints(60));
        resultsLayout.setVgap(15);
        resultsLayout.setPrefSize(700, 500);
        resultsLayout.setAlignment(Pos.CENTER);
        
        return resultsLayout;
    }
    
    public void addCandidateToGrid(int nextResult, int i) {
        AnswerList a = this.candidateLogic.getAnswerList(nextResult);
        Candidate c = a.getCandidate();
        VBox candidate1 = createLayoutForCandidate(c.getNumber(), c.getName(), c.getParty());
        resultsLayout.add(candidate1, 0, i);
        GridPane.setValignment(candidate1, VPos.TOP);
        GridPane.setColumnSpan(candidate1, 2);
        Label lbMatch1 = createLabelForMatchPercentage(a.getMatchPercentage());
        resultsLayout.add(lbMatch1, 1, i);
        GridPane.setValignment(lbMatch1, VPos.CENTER);
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
    
    public Button createButtonForNextPage(int next) {
        Button nextButton = new Button("Seuraava >");
        nextButton.setOnAction((event) -> {
            ui.showFinalResults(next);
        });
        return nextButton;
    }
    
    public Button createButtonForNewStart() {
        Button startButton = new Button("Aloita alusta");
        startButton.setOnAction((event) -> {
            ui.showWelcomeView();
        });
        return startButton;
    }

    
    public Label createRoundedLabel(String text) {
        Label label = new Label(text); //questionList.getSize() ???
        label.setTextAlignment(TextAlignment.CENTER); //tekstin keskitys
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
