/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.ui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

/**
 *
 * @author mlkul
 */
public class CandidateView {
    
    UI ui;
    
    public CandidateView(UI ui) {
        this.ui = ui;
    }
    
    public Scene getScene() {
        GridPane candidateView = new GridPane();
        
        return new Scene(candidateView);
    }
}
