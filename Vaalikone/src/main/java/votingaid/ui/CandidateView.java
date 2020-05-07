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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import votingaid.domain.Candidate;
import votingaid.domain.CandidateInfo;

/**
 *
 * @author mlkul
 */
public class CandidateView {
    
    UI ui;
    Candidate candidate;
    int currentOnList;
    CandidateInfo info;
    
    public CandidateView(UI ui, Candidate candidate, int currentOnList, CandidateInfo candidateInfo) {
        this.ui = ui;
        this.candidate = candidate;
        this.currentOnList = currentOnList;
        this.info = candidateInfo;
    }
    
    public Scene getScene() {
        GridPane candidateLayout = createLayoutForCandidateView();
        
        Button resultsButton = new Button("Takaisin tuloksiin");
        candidateLayout.add(resultsButton, 0, 1);
        GridPane.setValignment(resultsButton, VPos.TOP);
        int firstOnList = getCurrentOnList();
        resultsButton.setOnAction((event) -> {
            ui.showFinalResults(firstOnList);
        });
        
        Label lbCandidateName = new Label(candidate.getNumber() + " " + candidate.getName());
        lbCandidateName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        candidateLayout.add(lbCandidateName, 0, 2);
        
        Label lbParty = createRoundedLabel(candidate.getParty());
        candidateLayout.add(lbParty, 0, 3);
        
        TextFlow eDistrict = createTextElementWithBoldText("Vaalipiiri:\n", info.getElectoralDistrict() + "\n");
        TextFlow age = createTextElementWithBoldText("Ikä:\n", String.valueOf(candidate.getAge()) + "\n");
        TextFlow profession = createTextElementWithBoldText("Ammatti:\n", info.getProfession()+ "\n");
        VBox left = new VBox();
        left.getChildren().addAll(eDistrict, age, profession);
        candidateLayout.add(left, 0, 4);
        
        TextFlow candidateNumber = createTextElementWithBoldText("Ehdokasnumero:\n", String.valueOf(candidate.getNumber()) + "\n");
        TextFlow language = createTextElementWithBoldText("Äidinkieli:\n", info.getLanguage() + "\n");
        TextFlow education = createTextElementWithBoldText("Koulutus:\n", info.getEducation() + "\n");
        VBox right = new VBox();
        right.getChildren().addAll(candidateNumber, language, education);
        candidateLayout.add(right, 1, 4);
        
        TextFlow reasoning = createTextElementWithBoldText("Miksi minut pitäisi valita eduskuntaan:\n", info.getReasoning());
        candidateLayout.add(reasoning, 0, 5);
        GridPane.setColumnSpan(reasoning, 2);
        
        return new Scene(candidateLayout);
    }
    
    public GridPane createLayoutForCandidateView() {
        GridPane candidateLayout = new GridPane();
        candidateLayout.getColumnConstraints().add(new ColumnConstraints(200));
        candidateLayout.getColumnConstraints().add(new ColumnConstraints(230));
        
        candidateLayout.getRowConstraints().add(new RowConstraints(30));
        candidateLayout.getRowConstraints().add(new RowConstraints(35));
//        candidateLayout.getRowConstraints().add(new RowConstraints(20));
//        candidateLayout.getRowConstraints().add(new RowConstraints(25));
//        candidateLayout.getRowConstraints().add(new RowConstraints(80));

        candidateLayout.setVgap(10);
        candidateLayout.setPrefSize(700, 500);
        candidateLayout.setAlignment(Pos.TOP_CENTER);

        return candidateLayout;
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
    
    public TextFlow createTextElementWithBoldText(String bold, String normal) {
        TextFlow flow = new TextFlow();
        Text text1 = new Text(bold);
        text1.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        Text text2 = new Text(normal);
        text2.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        flow.getChildren().addAll(text1, text2);
        return flow;
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
