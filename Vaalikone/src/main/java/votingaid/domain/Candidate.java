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
public class Candidate {
    private int number;
    private String area;
    private String name;
    private int age;
    private String party;
//    private HashMap<Question, Integer> answers;
//    private HashMap<Question, Integer> singleMatches;
//    private int matchPercentage;
	
	
    public Candidate(int number, String area, String name, int age, String party) {
        this.number = number;
        this.area = area;
        this.name = name;
        this.age = age;
        this.party = party;
//        this.answers = new HashMap<>();
//        this.singleMatches = new HashMap<>();
//        this.matchPercentage = 0;
    }
	
    public int getNumber() {
        return this.number;
    }		
	
    public String getArea() {
        return this.area;
    }
	
    public String getName() {
        return this.name;
    }
	
    public int getAge() {
        return this.age;
    }
	
    public String getParty() {
        return this.party;
    }
    
    @Override
    public String toString() {
        return  name + ", " + age + ", " + party;
    }
    
//    public int getMatchPercentage() {
//        return this.matchPercentage;
//    }
//    
//    public void setMatchPercentage(int percentage) {
//        this.matchPercentage = percentage;
//    }

//    public void setAnswer(Question question, int answer) {
//        this.answers.put(question, answer);
//    }
    
//    public int getAnswer(int question) {
//        return this.answers.get(question);
//    }
    
//    public void setSingleMatch(Question question, int percentage) {
//        this.singleMatches.put(question, percentage);
//        //updateMatchPercentage();
//    }
    
//    public int getSingleMatch(Question question) {
//        return this.singleMatches.get(question);
//    }
    
//    public void updateMatchPercentage() {
//        int sum = 0;
//        if (!this.singleMatches.values().isEmpty()) {
//            for (int percentage : this.singleMatches.values()) {
//                sum += percentage;
//            }
//            sum = sum / this.singleMatches.size();
//        }
//        this.matchPercentage = sum;
//    }
    
//    public int getMatchPercentage() {
//        int sum = 0;
//        if (!this.singleMatches.values().isEmpty()) {
//            for (int percentage : this.singleMatches.values()) {
//                sum += percentage;
//            }
//            sum = sum / this.singleMatches.size();
//        }
//        return sum;
//    }
    
      
    
	
    
    
}
