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
    private String district;
    private String name;
    private int age;
    private String party;
//    private HashMap<Question, Integer> answers;
//    private HashMap<Question, Integer> singleMatches;
//    private int matchPercentage;
	
	
    public Candidate(int number, String area, String name, int age, String party) {
        this.number = number;
        this.district = district;
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
	
    public String getDistrict() {
        return this.district;
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
    
  
    
}
