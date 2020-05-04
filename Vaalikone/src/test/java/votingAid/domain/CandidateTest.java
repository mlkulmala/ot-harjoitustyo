/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingAid.domain;

import votingaid.domain.Candidate;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mlkul
 */
public class CandidateTest {
    
    Candidate candidate;
    
    @Before
    public void setUp() {
        candidate = new Candidate(1, 123, "Uusimaa", "Maija", 30, "VIHR");
    }
    
    @Test
    public void numberSettingIsCorrect() {
        assertEquals(123, candidate.getNumber());
    }
    
    @Test
    public void nameSettingIsCorrect() {
        assertEquals("Maija", candidate.getName());
    }
    
    @Test
    public void ageSettingIsCorrect() {
        assertEquals(30, candidate.getAge());
    }
    
    @Test
    public void areaSettingIsCorrect() {
        assertEquals("Uusimaa", candidate.getDistrict());
    }
    
    @Test
    public void partySettingIsCorrect() {
        assertEquals("VIHR", candidate.getParty());
    }
    
    @Test
    public void toStringIsCorrect() {
        assertEquals("Maija, 30, VIHR", candidate.toString());
    }
    
    
}
    
   
