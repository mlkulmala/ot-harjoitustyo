/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.domain;

import votingaid.domain.Candidate;
import votingaid.dao.CandidateDao;
import java.util.*;

/**
 *
 * @author mlkul
 */
public class CandidateLogic {
    
    List<Candidate> candidates;
    CandidateDao candidateDao;
    
    public CandidateLogic(CandidateDao candidatedao) {
        this.candidateDao = candidatedao;
    }
    
//    public void createCandidateList() {
//        this.candidates = candidateDao.getCandidatesByArea();
//    }
    
    public List<Candidate> createCandidateList() {
        return candidateDao.getCandidatesByArea();
    }
    
//    public void compareAndListAnswers(int number) {
//        this.candidates = compareToCandidates(c, number);
//        System.out.println("\n* * * * * * * * * *");
//        for (Candidate x : this.candidates) {
//            System.out.println(x.toString());
//        }
//        System.out.println();
//        c++;
//    }
    
    public List<Candidate> compareToCandidates(int qNumber, int userAnswer) {
        for (Candidate x : this.candidates) {
            int candAnswer = x.getAnswers().get(qNumber - 1);
            int diff = Math.abs(userAnswer - candAnswer);
            int newPercentage = 100 - diff * 25;
            x.addToSum(newPercentage);
            int total = x.getSum() / qNumber;
            x.setMatchPercentage(total);
        } 
        Collections.sort(this.candidates);
        return this.candidates;
       
    }
    
}
