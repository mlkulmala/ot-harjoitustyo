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
 *
 * @author mlkul
 */
public class CandidateLogic {
    
    //List<Candidate> candidates;
    List<AnswerList> allAnswers;
    CandidateDao candidateDao;
    String area; //tämä ei vielä käytössä
    
    
    public CandidateLogic(CandidateDao candidatedao, String area) {
        //this.candidates = new ArrayList<>();
        this.allAnswers = new ArrayList<>();
        this.candidateDao = candidatedao;
        this.area = area;
    }
    
//    public void createCandidateList() {
//        this.candidates = candidateDao.getCandidatesByArea();
//    }
    
    public void createAnswerList() {
        this.allAnswers = candidateDao.getAllAnswers();
    }
                                                                
//    public List<Candidate> compareToCandidates(int questionNumber, int userAnswer) {
//        for (Candidate candidate : this.candidates) {
//            int candAnswer = candidate.getAnswer(questionNumber);
//            int diff = Math.abs(userAnswer - candAnswer);
//            int percentage = 100 - diff * 25;
//            candidate.setSingleMatch(questionNumber, percentage);//tämä answerList-luokkaan
//        } 
//        Collections.sort(this.candidates);
//        return this.candidates;
//    }
    
    
    
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
