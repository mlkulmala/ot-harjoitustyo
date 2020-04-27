/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.domain;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
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
        
        GridPane resultsLayout = new GridPane();
        resultsLayout.setPrefSize(700, 500);
        
        return new Scene(resultsLayout);
    }
}
