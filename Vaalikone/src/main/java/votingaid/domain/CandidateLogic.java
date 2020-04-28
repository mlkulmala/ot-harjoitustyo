/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.domain;

import votingaid.domain.AnswerList;
import votingaid.domain.Candidate;
import votingaid.dao.CandidateDao;
import java.util.*;

/**
 * CandidateLogic 
 * @author mlkul
 */
public class CandidateLogic {
    
    List<AnswerList> allAnswers;
    CandidateDao candidateDao;
    String district; //tämä ei vielä käytössä
    
    /**
     * Construct a new CandidateLogic using given DAO. 
     * @param candidatedao dao that is used for loading candidates
     * @param district The district whose candidates are being listed.
     * 
     */
    public CandidateLogic(CandidateDao candidatedao, String district) {
        this.allAnswers = new ArrayList<>();
        this.candidateDao = candidatedao;
        this.district = district;
    }
    
    /**
     * Loads all candidates and their answers from database.
     */
    public void createAnswerList() {
        this.allAnswers = candidateDao.getAllAnswers();
    }
                                                                
    /**
     * Compare the answer given by the user with the answers of all candidates. 
     * Count the percentage unit that indicates how well the opinions match. 
     * Sort the list of AnswerLists according to the matchPercentage.
     * @param questionNumber the number of the question whose answers are being compared.
     * @param userAnswer the answer of the user on scale 1 to 5.
     * @return the list of AnswerLists sorted by the matchPercentage of each 
     * candidate
     */
    public List<AnswerList> compareToCandidateAnswers(int questionNumber, int userAnswer) {
        for (AnswerList answerList : this.allAnswers) {
            int candAnswer = answerList.getAnswer(questionNumber);
            int diff = Math.abs(userAnswer - candAnswer);
            int percentage = 100 - diff * 25;
            answerList.setSingleMatch(questionNumber, percentage);
        } 
        Collections.sort(this.allAnswers);
        return this.allAnswers;
    }
    
}
