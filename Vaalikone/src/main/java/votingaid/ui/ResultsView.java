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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import votingaid.domain.AnswerList;
import votingaid.domain.Candidate;
import votingaid.domain.CandidateLogic;

/**
 * View for showing an overview of the voting aid results.
 * Three candidates are shown simultaneously.
 * @author mlkul
 */
public class ResultsView {
    UI ui;
    CandidateLogic candidateLogic;
    int currentOnList;
    GridPane resultsLayout;
    
    /**
     * Construct a view for voting aid results.
     * @param ui parent ui.
     * @param candidateLogic class that contains all information of the candidates 
     * and their answers.
     * @param currentOnList an index that keeps count of the candidate 
     * on top of the list in this view.
     */
    public ResultsView(UI ui, CandidateLogic candidateLogic, int currentOnList) {
        this.ui = ui;
        this.candidateLogic = candidateLogic;
        this.currentOnList = currentOnList;
    }
    
    /**
     * Scene implementing this view.
     * @return Scene
     */
    public Scene getScene() {
        resultsLayout = createGridPaneForResults();
        
        addTitleLabelToGrid(0, 0);
        
        for (int i = 1; i <= 3; i++) {
            if (currentOnList < candidateLogic.getCountOfAllAnswerLists()) {
                addCandidateToGrid(currentOnList, i);
                currentOnList++;
            }
        }
        int previous = countCurrentOnListOnPreviousPage();

        Button nextButton = createButtonForNavigation("Seuraava >", currentOnList);
        Button prevButton = createButtonForNavigation("< Edellinen", previous); 
        
        Button startButton = createButtonForNewStart();
        GridPane buttons = createGridPaneForButtons();
       
        if (currentOnList > 3) {
            buttons.add(prevButton, 0, 0);
        }
        if (currentOnList < this.candidateLogic.getCountOfAllAnswerLists()) {
            buttons.add(nextButton, 1, 0);
        } 
        buttons.add(startButton, 2, 0);
        buttons.setVgap(10);
        resultsLayout.add(buttons, 0, 4);
        
        return new Scene(resultsLayout);
    }
    
    public GridPane createGridPaneForResults() {
        resultsLayout = new GridPane();
        resultsLayout.getColumnConstraints().add(new ColumnConstraints(200));
        resultsLayout.getColumnConstraints().add(new ColumnConstraints(230));
        resultsLayout.getRowConstraints().add(new RowConstraints(60));
        resultsLayout.getRowConstraints().add(new RowConstraints(100));
        resultsLayout.getRowConstraints().add(new RowConstraints(100));
        resultsLayout.getRowConstraints().add(new RowConstraints(100));
        resultsLayout.getRowConstraints().add(new RowConstraints(60));
        resultsLayout.setVgap(15);
        resultsLayout.setPrefSize(700, 500);
        resultsLayout.setAlignment(Pos.CENTER);
        
        return resultsLayout;
    }
    
    public void addTitleLabelToGrid(int x, int y) {
        Label lbTitle = createRoundedLabel("ENITEN SAMAA MIELTÄ KANSSASI");
        resultsLayout.add(lbTitle, x, y);
        GridPane.setHalignment(lbTitle, HPos.LEFT);
        GridPane.setValignment(lbTitle, VPos.BOTTOM);
        GridPane.setColumnSpan(lbTitle, 2);
    }
    
    public void addCandidateToGrid(int current, int i) {
        AnswerList a = this.candidateLogic.getAnswerList(current);
        Candidate c = a.getCandidate();
        VBox candidate1 = createLayoutForCandidate(c);
        resultsLayout.add(candidate1, 0, i);
        GridPane.setValignment(candidate1, VPos.TOP);
        GridPane.setColumnSpan(candidate1, 2);
        Label lbMatch1 = createLabelForMatchPercentage(a.getMatchPercentage());
        resultsLayout.add(lbMatch1, 1, i);
        GridPane.setValignment(lbMatch1, VPos.CENTER);
    }
    
    
    public VBox createLayoutForCandidate(Candidate candidate) {
        VBox candidateBox = new VBox(10);
        
        Label lbCandidateName = new Label(candidate.getNumber() + " " + candidate.getName());
        lbCandidateName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        
        Label lbParty = createRoundedLabel(candidate.getParty());
        
        Button infoButton = new Button("Tutustu ehdokkaaseen");
        infoButton.setPrefWidth(150);
        infoButton.setOnAction((event) -> {
            ui.showCandidateView(candidate, currentOnList);
        }); 
        
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
    
    public Button createButtonForNavigation(String buttonText, int current) {
        Button nextButton = new Button(buttonText);
        nextButton.setOnAction((event) -> {
            ui.showFinalResults(current);
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
    
    public GridPane createGridPaneForButtons() {
        GridPane buttons = new GridPane();
        buttons.getColumnConstraints().add(new ColumnConstraints(100));
        buttons.getColumnConstraints().add(new ColumnConstraints(100));
        buttons.getColumnConstraints().add(new ColumnConstraints(100));
        return buttons;
    }
    
    public Label createRoundedLabel(String text) {
        Label label = new Label(text); 
        label.setTextAlignment(TextAlignment.CENTER); 
        label.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        label.setTextFill(Color.WHITE);
        CornerRadii corner10 = new CornerRadii(15);
        Color color = chooseColorByParty(text);
        label.setBackground(new Background(new BackgroundFill(color, corner10, Insets.EMPTY)));
        label.setPadding(new Insets(5, 8, 5, 8)); 
        
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
    
    public int countCurrentOnListOnPreviousPage() {
        switch (currentOnList % 3) {
            case 1:
                return currentOnList - 4;
            case 2:
                return currentOnList - 5;
            default:
                return currentOnList - 6;
        }
    }
}
