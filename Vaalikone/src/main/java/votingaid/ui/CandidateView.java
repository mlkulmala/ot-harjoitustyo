/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.ui;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import votingaid.domain.Candidate;

/**
 *
 * @author mlkul
 */
public class CandidateView {
    
    UI ui;
    Candidate candidate;
    int currentOnList;
    
    public CandidateView(UI ui, Candidate candidate, int currentOnList) {
        this.ui = ui;
        this.candidate = candidate;
        this.currentOnList = currentOnList;
    }
    
    public Scene getScene() {
        GridPane candidateLayout = createLayoutForCandidateView();
        
        Button resultsButton = new Button("Takaisin tuloksiin");
        candidateLayout.add(resultsButton, 0, 0);
        GridPane.setValignment(resultsButton, VPos.TOP);
        int firstOnList = getCurrentOnList();
        resultsButton.setOnAction((event) -> {
            ui.showFinalResults(firstOnList);
        });
        
        Label lbCandidateName = new Label(candidate.getNumber() + " " + candidate.getName());
        lbCandidateName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        candidateLayout.add(lbCandidateName, 0, 1);
        
        Label lbParty = createRoundedLabel(candidate.getParty());
        candidateLayout.add(lbParty, 0, 2);
        
        
        
        
        
        return new Scene(candidateLayout);
    }
    
    public GridPane createLayoutForCandidateView() {
        GridPane candidateLayout = new GridPane();
        candidateLayout.getColumnConstraints().add(new ColumnConstraints(200));
        candidateLayout.getColumnConstraints().add(new ColumnConstraints(200));
        
        candidateLayout.getRowConstraints().add(new RowConstraints(80));
        candidateLayout.getRowConstraints().add(new RowConstraints(50));
        candidateLayout.getRowConstraints().add(new RowConstraints(50));
        candidateLayout.getRowConstraints().add(new RowConstraints(50));
        candidateLayout.setVgap(15);
        candidateLayout.setPrefSize(700, 500);
        candidateLayout.setAlignment(Pos.CENTER);

        return candidateLayout;
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
    
    public Label createLabelForTitle(String title) {
        Label lbWelcome = new Label(title);
        lbWelcome.setTextAlignment(TextAlignment.CENTER);
        lbWelcome.setAlignment(Pos.CENTER);
        lbWelcome.setWrapText(true);
        lbWelcome.setMaxWidth(400);
        lbWelcome.setFont(new Font("Arial", 25));
        
        return lbWelcome;
    }
    
    public int getCurrentOnList() {
        switch (currentOnList % 3) {
            case 1:
                return currentOnList - 1;
            case 2:
                return currentOnList - 2;
            default:
                return currentOnList - 3;
        }
    }
}
