/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingAid;

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
        candidate = new Candidate(123, "Uusimaa", "Maija", 30, "VIHR");
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
        assertEquals("Uusimaa", candidate.getArea());
    }
    
    @Test
    public void partySettingIsCorrect() {
        assertEquals("VIHR", candidate.getParty());
    }
    
    @Test
    public void setAnswerGivesCorrect() {
        candidate.setAnswer(1, 5);
        candidate.setAnswer(2, 5);
        assertEquals(5, candidate.getAnswer(2));
    }
   
    @Test
    public void zeroMatchAtBeginning() {
        assertEquals(0, candidate.getMatchPercentage());
    }
    
    @Test
    public void singleMatchesGiveCorrectPercentage() {
        candidate.setSingleMatch(1, 75);
        candidate.setSingleMatch(2, 75);
        candidate.setSingleMatch(3, 50);
        candidate.setSingleMatch(4, 75);
        candidate.setSingleMatch(5, 0);
        assertEquals(55, candidate.getMatchPercentage());
    }
    
    
    @Test
    public void setSingleMatchGivesRightPercentage() {
        candidate.setSingleMatch(1, 75);
        candidate.setSingleMatch(1, 25);
        assertEquals(25, candidate.getSingleMatch(1));
    }
    
    @Test
    public void toStringIsCorrect() {
        candidate.setSingleMatch(1, 75);
        candidate.setSingleMatch(2, 75);
        candidate.setSingleMatch(3, 50);
        candidate.setSingleMatch(4, 75);
        candidate.setSingleMatch(5, 0);
        assertEquals("55% Maija, VIHR", candidate.toString());
    }
}
    
   
