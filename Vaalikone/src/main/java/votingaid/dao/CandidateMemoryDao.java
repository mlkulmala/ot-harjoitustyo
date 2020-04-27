/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.dao;


import votingaid.dao.CandidateDao;
import java.util.*;
import votingaid.domain.AnswerList;
import votingaid.domain.Candidate;

/**
 *
 * @author mlkul
 */
public class CandidateMemoryDao implements CandidateDao {
    
   
    
    @Override
    public List<AnswerList> getAllAnswers() {
        ArrayList<AnswerList> allAnswers = new ArrayList<>();
        
        //tietokantaa ei vielä luotu, joten luodaan tässä
        //ehdokkaat ja vastaukset testaamista varten
        
        Candidate aku = new Candidate(331, "Uusimaa", "Aku", 34, "KOK");
        Candidate lasse = new Candidate(124, "Uusimaa", "Lasse", 49, "VAS");
        Candidate heli = new Candidate(127, "Uusimaa", "Heli", 25, "VIHR");
        
        AnswerList listAku = new AnswerList(aku);
        AnswerList listLasse = new AnswerList(lasse);
        AnswerList listHeli = new AnswerList(heli);
        
        for (int i = 1; i <= 20; i++) {
            listAku.setAnswer(i, 2);
            listLasse.setAnswer(i, 5);
            listHeli.setAnswer(i, 3);
        }
        
        allAnswers.add(listAku); 
        allAnswers.add(listLasse);
        allAnswers.add(listHeli);
        
        
        
        return allAnswers;
    }
     
     
    
}
