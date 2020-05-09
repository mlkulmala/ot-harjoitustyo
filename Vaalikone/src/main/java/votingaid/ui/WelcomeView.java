package votingaid.ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Starting point of the application. Introduces the application to the user. 
 * Selection of the district whose candidates are shown.
 * @author mlkul
 */
public class WelcomeView {
    
    UI ui;
    
    public WelcomeView(UI ui) {
        this.ui = ui;
    }
    
    /**
     * Scene implementing this view.
     * @return Scene
     */
    public Scene getScene() {
        GridPane welcomeLayout = createGridPaneForWelcomeView();
        
        Label lbWelcome = createLabelForTitle("Tervetuloa vaalikoneeseen!");
        welcomeLayout.add(lbWelcome, 0, 0);
        
        addIntroductionTextToGrid(welcomeLayout, 0, 1);

        ChoiceBox cbDistricts = new ChoiceBox();
        cbDistricts.getItems().add("Helsinki");
        cbDistricts.setPrefWidth(100);
        GridPane.setHalignment(cbDistricts, HPos.CENTER);
        welcomeLayout.add(cbDistricts, 0, 2);
        
        Label lbMessage = createLabelForText("");
        welcomeLayout.add(lbMessage, 0, 3);

        Button startButton = new Button("Aloita vaalikone");
        GridPane.setHalignment(startButton, HPos.CENTER);
        welcomeLayout.add(startButton, 0, 4);
           
        startButton.setOnAction(action -> {
            if (cbDistricts.getValue() != null) {
                String district = (String) cbDistricts.getValue();
                try {
                    ui.initializeLists(district);
                    ui.showFirstQuestion();
                } catch (NullPointerException  e) {
                    lbMessage.setText("Valitsemassasi vaalipiirissä ei ole vielä ehdokkaita.");
                } catch (Exception e) {
                    lbMessage.setText("Yhteys tietokantaan epäonnistui.");
                }
            } else {
                lbMessage.setText("Vaalipiiriä ei ole valittu.\n");
            }
        });
        
        return new Scene(welcomeLayout);
    }
    
    public GridPane createGridPaneForWelcomeView() {
        GridPane grid = new GridPane();
        grid.setPrefSize(700, 500);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setPadding(new Insets(30, 20, 20, 20));
        return grid;
    }
    
    public void addIntroductionTextToGrid(GridPane grid, int x, int y) {
        Label lbIntro = createLabelForText("Vaalikone auttaa sinua tutustumaan "
                + "eduskuntavaalien ehdokkaisiin. Vastaa väitteisiin ja "
                + "vaalikone kertoo, keiden näkemykset ovat lähellä omiasi.\n\n"
                + "Aloita kertomalla kotikuntasi.");
        grid.add(lbIntro, x, y);
    }
    
    public void addMessageLabelToGrid(GridPane grid, int x, int y) {
        Label lbMessage = createLabelForText("");
        grid.add(lbMessage, x, y);
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
    
    public Label createLabelForText(String string) {
        Label lbText = new Label(string);
        lbText.setTextAlignment(TextAlignment.CENTER);
        lbText.setAlignment(Pos.CENTER);
        lbText.setWrapText(true);
        lbText.setMaxWidth(400);
        return lbText;
    }
}
