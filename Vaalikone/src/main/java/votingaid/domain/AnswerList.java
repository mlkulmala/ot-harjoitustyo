/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.domain;

import java.util.*;

/**
 *
 * @author mlkul
 */
public class AnswerList implements Comparable<AnswerList>{
    
    Candidate candidate;
    private HashMap<Integer, Integer> answers;
    private HashMap<Integer, Integer> singleMatches;
    private int matchPercentage;
    
    public AnswerList(Candidate candidate) {
        this.candidate = candidate;
        this.answers = new HashMap<>();
        this.singleMatches = new HashMap<>();
        this.matchPercentage = 0;
    }
    
    public Map<Integer, Integer> getAnswers() {
        return this.answers;
    }
    
    public void setAnswer(int question, int answer) {
        this.answers.put(question, answer);
    }
    
    public int getAnswer(int question) {
        return this.answers.get(question);
    }
    
    public int getSingleMatch(int question) {
        return this.singleMatches.get(question);
    }
    
    public void setSingleMatch(int question, int percentage) {
        this.singleMatches.put(question, percentage);
        updateMatchPercentage();
    }
    
    public void updateMatchPercentage() {
        int sum = 0;
        if (!this.singleMatches.values().isEmpty()) {
            for (int percentage : this.singleMatches.values()) {
                sum += percentage;
            }
            sum = sum / this.singleMatches.size();
        }
        this.matchPercentage = sum;
    }
    
    public int getMatchPercentage() {
        return this.matchPercentage;
    }
    
    @Override
    public int compareTo(AnswerList answers) {
        return answers.matchPercentage - this.matchPercentage;
    }
	
    @Override
    public String toString() {
        return matchPercentage + "% " + this.candidate.getName() + ", " + this.candidate.getParty();
    }
}
