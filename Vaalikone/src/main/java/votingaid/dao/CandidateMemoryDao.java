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
        
        //tähän voi toteuttaa tietokannasta haun
        //lisätään malliehdokkaat nyt tässä:
//        try {
//            File fileDir = new File("questions_new.txt");
//   
//            BufferedReader in = new BufferedReader(
//                new InputStreamReader(
//                    new FileInputStream(fileDir), "UTF8"));
//            String line;
//            while ((line = in.readLine()) != null) {  //tähän se mitä tehdään riveille
//                String[] s = line.split(",");
//                if(s[1].equals(area)) {
//                    candidates.add(new Candidate(s[0], s[1], s[2], s[3], s[4], s[5], s[6], ...));
//                }
//            }
//            in.close();
//            
//        } catch (UnsupportedEncodingException e) {
//            System.out.println(e.getMessage());
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        
        
        Candidate aku = new Candidate(331, "Uusimaa", "Aku", 34, "KOK");
        Candidate lasse = new Candidate(124, "Uusimaa", "Lasse", 49, "VAS");
        Candidate heli = new Candidate(127, "Uusimaa", "Heli", 25, "VIHR");
        aku.addAllAnswers(2, 1, 2, 3, 1);
        lasse.addAllAnswers(3, 5, 4, 5, 5);
        heli.addAllAnswers(1, 1, 1, 1, 1);
        
        candidates.add(aku);
        candidates.add(lasse);
        candidates.add(heli);
        
        return candidates;
    }
    
}
