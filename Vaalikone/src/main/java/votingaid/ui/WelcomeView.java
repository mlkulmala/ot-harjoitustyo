package votingaid.ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
        Label lbWelcome = createLabelForTitle("Tervetuloa vaalikoneeseen!");

        Label lbIntro = createLabelForText("Vaalikone auttaa sinua tutustumaan "
                + "eduskuntavaalien ehdokkaisiin. Vastaa väitteisiin ja "
                + "vaalikone kertoo, keiden näkemykset ovat lähellä omiasi.\n\n"
                + "Aloita kertomalla kotikuntasi.");

        Label lbMessage = createLabelForText("");
        
        ChoiceBox cbDistricts = new ChoiceBox();
        cbDistricts.getItems().addAll("Helsinki", "Tampere", "Turku");
        cbDistricts.setPrefWidth(100);
        GridPane.setHalignment(cbDistricts, HPos.CENTER);
        

        Button startButton = new Button("Aloita");
        GridPane.setHalignment(startButton, HPos.CENTER);

        GridPane welcomeView = new GridPane();
        welcomeView.add(lbWelcome, 0, 0);
        welcomeView.add(lbIntro, 0, 1);
        welcomeView.add(cbDistricts, 0, 2);
        welcomeView.add(startButton, 0, 4);
        welcomeView.add(lbMessage, 0, 3);
        welcomeView.setColumnSpan(lbWelcome, 5);
        welcomeView.setColumnSpan(lbIntro, 5);
        welcomeView.setColumnSpan(cbDistricts, 5);
        welcomeView.setColumnSpan(lbMessage, 5);
        welcomeView.setColumnSpan(startButton, 5);

        welcomeView.setPrefSize(700, 500);
        welcomeView.setAlignment(Pos.CENTER);
        welcomeView.setVgap(20);
        welcomeView.setHgap(20);
        welcomeView.setPadding(new Insets(30, 20, 20, 20));
        
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
        
        return new Scene(welcomeView);
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
