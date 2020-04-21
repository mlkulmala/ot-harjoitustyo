/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.main;

import static javafx.application.Application.launch;
import votingaid.ui.VotingAidUi;

/**
 *
 * @author mlkul
 */
public class VotingAidApplication {
    
    public static void main(String[] args) {
        
        VotingAidUi gui = new VotingAidUi();
        launch(VotingAidUi.class);
    }
}
