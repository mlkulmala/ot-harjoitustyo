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
    
    public void createCandidateList() {
        this.candidates = candidateDao.getCandidates();
    }
    
    public List<Candidate> compareToCandidates(int c, int userAnswer) {
        for (Candidate x : this.candidates) {
            int candAnswer = x.getAnswers().get(c - 1);
            int diff = Math.abs(userAnswer - candAnswer);
            int newPercentage = 100 - diff * 25;
            x.addToSum(newPercentage);
            int total = x.getSum() / c;
            x.setMatchPercentage(total);
        } 
        Collections.sort(this.candidates);
        return this.candidates;
       
    }
    
}
