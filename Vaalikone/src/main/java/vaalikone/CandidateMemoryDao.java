/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaalikone;

import java.util.*;

/**
 *
 * @author mlkul
 */
public class CandidateMemoryDao implements CandidateDao {
    
    @Override
     public List<Candidate> getCandidates() {
        ArrayList<Candidate> candidates = new ArrayList<>();
        
        //tähän voi toteuttaa tietokannasta haun
        
        Candidate aku = new Candidate(331, "Uusimaa", "Aku", 34, "KOK");
        Candidate lasse = new Candidate(124, "Uusimaa", "Lasse", 49, "VAS");
        Candidate heli = new Candidate(127, "Uusimaa", "Heli", 25, "VIHR");
        aku.addAllAnswers(2,1,2,3,1);
        lasse.addAllAnswers(3,5,4,5,5);
        heli.addAllAnswers(1,1,1,1,1);
        
        candidates.add(aku);
        candidates.add(lasse);
        candidates.add(heli);
        
        return candidates;
    }
    
}
