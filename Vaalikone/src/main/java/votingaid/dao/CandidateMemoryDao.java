/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import votingaid.dao.CandidateDao;
import java.util.*;
import votingaid.domain.Candidate;

/**
 *
 * @author mlkul
 */
public class CandidateMemoryDao implements CandidateDao {
    
    @Override
     public List<Candidate> getCandidatesByArea() {
        ArrayList<Candidate> candidates = new ArrayList<>();
        
        //tietokannasta hakua ei vielä luotu, joten luodaan tässä
        //ehdokkaat ja vastaukset testaamista varten
 
        
        Candidate aku = new Candidate(331, "Uusimaa", "Aku", 34, "KOK");
        Candidate lasse = new Candidate(124, "Uusimaa", "Lasse", 49, "VAS");
        Candidate heli = new Candidate(127, "Uusimaa", "Heli", 25, "VIHR");
        
        for (int i = 1; i <= 25; i++) {
            aku.setAnswer(i, 2);
            lasse.setAnswer(i, 5);
            heli.setAnswer(i, 1);
        }
//        aku.setAnswer(1, 2);
//        aku.setAnswer(2, 1);
//        aku.setAnswer(3, 2);
//        aku.setAnswer(4, 3);
//        aku.setAnswer(5, 1);
//        lasse.setAnswer(1, 3);
//        lasse.setAnswer(2, 5);
//        lasse.setAnswer(3, 4);
//        lasse.setAnswer(4, 5);
//        lasse.setAnswer(5, 5);
//        heli.setAnswer(1, 1);
//        heli.setAnswer(2, 1);
//        heli.setAnswer(3, 1);
//        heli.setAnswer(4, 1);
//        heli.setAnswer(5, 1);
        
        candidates.add(aku);
        candidates.add(lasse);
        candidates.add(heli);
        
        return candidates;
    }
    
}
