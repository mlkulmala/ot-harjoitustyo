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
public class ListOfCandidates {
    private ArrayList<Candidate> candidates;
	
	
    public ListOfCandidates() {
        this.candidates = new ArrayList<>();
        candidates.add(new Candidate(3, "Uusimaa", "Aku", 34, "KOK"));
    }
	
    public void addToListOfCandidates(Candidate a) {
        this.candidates.add(a);
    }
    
}
