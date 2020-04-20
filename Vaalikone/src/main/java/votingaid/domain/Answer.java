/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.domain;

/**
 *
 * @author mlkul
 */
public class Answer {
    
    private Question question;
    private Candidate candidate;
    private int answer;
    
    public Answer(Question question, Candidate candidate, int answer) {
        this.question = question;
        this.candidate = candidate;
        this.answer = answer;
    }
    
    public int getAnswer() {
        return this.answer;
    }
    
}
