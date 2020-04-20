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
    String area; //tämä ei ehkä tule käyttöön
    
    
    public CandidateLogic(CandidateDao candidatedao, String area) {
        this.candidates = new ArrayList<>();
        this.candidateDao = candidatedao;
        this.area = area;
    }
    
    public void createCandidateList() {
        this.candidates = candidateDao.getCandidatesByArea();
    }
                                                                
    public List<Candidate> compareToCandidates(int userAnswer, int questionNumber) {
        for (Candidate candidate : this.candidates) {
            int candAnswer = candidate.getAnswer(questionNumber);
            int diff = Math.abs(userAnswer - candAnswer);
            int percentage = 100 - diff * 25;
            candidate.setSingleMatch(questionNumber, percentage);
//            int total = candidate.getMatchPercentage();
//            candidate.setMatchPercentage(total);
        } 
        Collections.sort(this.candidates);
        return this.candidates;
       
    }
    
}
