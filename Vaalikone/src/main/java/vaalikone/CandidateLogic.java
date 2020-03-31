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
public class CandidateLogic {
    
    List<Candidate> candidates;
    CandidateMemoryDao candMemoryDao;
    
    public CandidateLogic() {
        //this.candidates = new ArrayList<>();
        this.candMemoryDao = new CandidateMemoryDao();
    }
    
    public void createCandidateList() {
        this.candidates = candMemoryDao.getCandidates();
    }
    
    public void compareToCandidates(int c, int qNumber, int userAnswer) {
	// for(int i=1; i<=3; i++) {
	for(Candidate x : this.candidates) {
            int candAnswer = x.getAnswers().get(qNumber);
            int diff = Math.abs(userAnswer - candAnswer);
            int sum = x.getSum();  //aluksi 0
            sum += 100 - diff*25;
            sum = sum/c;
            x.setMatchPercentage(sum);
            x.addToSum(sum); 
	} 
	Collections.sort(this.candidates);
        System.out.println("\n* * * * * * * * * *");
        for(Candidate x: this.candidates) {
            System.out.println(x.toString());
        }
    }
    
}
