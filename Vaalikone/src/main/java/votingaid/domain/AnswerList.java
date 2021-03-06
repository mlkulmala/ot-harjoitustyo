package votingaid.domain;

import java.util.*;

/**
 * An AnswerList is a collection of all answers of a single candidate.
 * It has a list of opinion matches on single questions 
 * between the user and a candidate.
 * @author mlkul
 */
public class AnswerList implements Comparable<AnswerList> {
    
    Candidate candidate;
    private HashMap<Integer, Integer> answers;
    private HashMap<Integer, Integer> singleMatches;
    private int matchPercentage;
    
    /**
     * Construct a new AnswerList. 
     * @param candidate Election candidate whose answers are listed.
     */
    public AnswerList(Candidate candidate) {
        this.candidate = candidate;
        this.answers = new HashMap<>();
        this.singleMatches = new HashMap<>();
        this.matchPercentage = 0;
    }
    
    public Candidate getCandidate() {
        return this.candidate;
    }
    
    public Map<Integer, Integer> getAnswers() {
        return this.answers;
    }
    
    public void setAnswer(int question, int answer) {
        this.answers.put(question, answer);
    }
    
    public int getAnswer(int question) {
        if (!this.answers.containsKey(question)) {
            return 0;
        }
        return this.answers.get(question);
    }
    
    public int getSingleMatch(int question) {
        return this.singleMatches.get(question);
    }
    /**
     * Add new percentage when a question is answered.
     * @param question the number of the question in line
     * @param percentage percentage units that indicate how well the user's 
     * opinion matches this candidate's opinion on this question
     */
    public void setSingleMatch(int question, int percentage) {
        this.singleMatches.put(question, percentage);
        updateMatchPercentage();
    }
    /**
     * Count the averige of opinion matches on single questions
     * between the user and a candidate. 
     */
    public void updateMatchPercentage() {
        int sum = 0;
        if (!this.singleMatches.values().isEmpty()) {
            for (int percentage : this.singleMatches.values()) {
                sum += percentage;
            }
            sum = (int) Math.round(sum * 1.0 / this.singleMatches.size());
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
