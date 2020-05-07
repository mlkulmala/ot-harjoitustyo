package votingaid.domain;

import java.util.*;

/**
 * A class representing an election candidate.
 * @author mlkul
 */
public class Candidate {
    final int id;
    final int number;
    final String district;
    final String name;
    final int age;
    final String party;

	
    
    public Candidate(int id, int number, String district, String name, int age, String party) {
        this.id = id;
        this.number = number;
        this.district = district;
        this.name = name;
        this.age = age;
        this.party = party;
    }
	
    public int getId() {
        return this.id;
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
