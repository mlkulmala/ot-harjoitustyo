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
public class Candidate implements Comparable<Candidate> {
    private int number;
    private String area;
    private String name;
    private int age;
    private String party;
	
    private ArrayList<Integer> answers;
    private int sum;
    private int matchPercentage;
	
	
    public Candidate(int number, String area, String name, int age, String party) {
        this.number = number;
        this.area = area;
        this.name = name;
        this.age = age;
        this.party = party;
        this.answers = new ArrayList<>();
        this.sum = 0;
        this.matchPercentage = 0;
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
	
    public ArrayList<Integer> getAnswers() {
        return this.answers;
    }
    
    public void setAnswers(ArrayList<Integer> answers) {
        this.answers = answers;
    }
    
    public void addAllAnswers(int a, int b, int c, int d, int e) {
        this.answers.add(a);
        this.answers.add(b);
        this.answers.add(c);
        this.answers.add(d);
        this.answers.add(e);
    }
	
    public void addToSum(int c) {
        this.sum += c;
    }
	
    public int getSum() {
        return this.sum;
    }
    
    public void setMatchPercentage(int percent) {
        this.matchPercentage = percent;
    }
    
    public int getMatchPercentage() {
        return this.matchPercentage;
    }
	
   
    @Override
    public int compareTo(Candidate a) {
        return a.sum - this.sum;
    }
	
    @Override
    public String toString() {
        return matchPercentage + "% " + name + ", " + party;
    }
    
}
