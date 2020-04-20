/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.main;

//import votingaid.ui.UI;
import java.util.*;
import static javafx.application.Application.launch;
import votingaid.ui.GraphicUI;

/**
 *
 * @author mlkul
 */
public class VotingAidApplication {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GraphicUI gui = new GraphicUI();
        launch(GraphicUI.class);
//        UI ui = new UI(scanner);
//        ui.start();
    }
    
}
