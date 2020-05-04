/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.domain;

import java.io.IOException;
import java.sql.SQLException;
import votingaid.domain.AnswerList;
import votingaid.domain.Candidate;
import votingaid.dao.CandidateDao;
import java.util.*;

/**
 * CandidateLogic 
 * @author mlkul
 */
public class CandidateLogic {
    
    List<Candidate> allCandidates;
    List<AnswerList> allAnswers;
    CandidateDao candidateDao;
    
    /**
     * Construct a new CandidateLogic using given DAO. 
     * @param candidatedao dao that is used for loading candidates
     * 
     */
    public CandidateLogic(CandidateDao candidatedao) {
        allCandidates = new ArrayList<>();
        allAnswers = new ArrayList<>();
        candidateDao = candidatedao;
    }
    
    /**
     * Loads all candidates and their answers from database.
     */
    public void createAnswerList(String district) throws SQLException, IOException {
        
        candidateDao.getConnection();
        allCandidates = candidateDao.getCandidatesByDistrict(district);
        for (Candidate candidate : allCandidates) {
            AnswerList answerList = candidateDao.getCandidateAnswers(candidate);
            allAnswers.add(answerList);
        }
        candidateDao.close();
        
        //allAnswers = getAllAnswers();
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
        for (AnswerList answerList : allAnswers) {
            System.out.println(answerList.getCandidate().getName());
            System.out.println(answerList.getAnswer(questionNumber));
            int candAnswer = answerList.getAnswer(questionNumber);
            if (candAnswer != 0) {
                int diff = Math.abs(userAnswer - candAnswer);
                int percentage = 100 - diff * 25;
                answerList.setSingleMatch(questionNumber, percentage);
            }
        } 
        Collections.sort(allAnswers);
        return allAnswers;
    }
    
    public List<AnswerList> getAllAnswers() {
        ArrayList<AnswerList> allAnswers = new ArrayList<>();
        
        //tietokantaa ei vielä luotu, joten luodaan tässä
        //ehdokkaat ja vastaukset testaamista varten
        
        Candidate aku = new Candidate(1, 331, "Uusimaa", "Aku", 34, "KOK");
        Candidate lasse = new Candidate(2, 124, "Uusimaa", "Lasse", 49, "VAS");
        Candidate heli = new Candidate(3, 127, "Uusimaa", "Heli", 25, "VIHR");
        
        AnswerList listAku = new AnswerList(aku);
        AnswerList listLasse = new AnswerList(lasse);
        AnswerList listHeli = new AnswerList(heli);
        
        for (int i = 1; i <= 25; i++) {
            listAku.setAnswer(i, 2);
            listLasse.setAnswer(i, 5);
            listHeli.setAnswer(i, 3);
        }
        
        allAnswers.add(listAku); 
        allAnswers.add(listLasse);
        allAnswers.add(listHeli);
        
        return allAnswers;
    }
    
    public AnswerList getAnswerList(int number) {
        return this.allAnswers.get(number);
    }
}
