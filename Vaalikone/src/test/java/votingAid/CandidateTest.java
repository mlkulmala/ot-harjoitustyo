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
    public void answerSettingIsCorrect() {
        assertEquals(Collections.emptyList(), candidate.getAnswers());
    }
    
    @Test
    public void zeroSumAtBeginning() {
        assertEquals(0, candidate.getSum());
    }
    
    @Test
    public void zeroMatchAtBeginning() {
        assertEquals(0, candidate.getMatchPercentage());
    }
    
    @Test
    public void addSumGivesRightSum() {
        candidate.addToSum(100);
        candidate.addToSum(75);
        assertEquals(175, candidate.getSum());
    }
    
    @Test
    public void setMatchGivesRightPercentage() {
        candidate.setMatchPercentage(50);
        candidate.setMatchPercentage(62);
        assertEquals(62, candidate.getMatchPercentage());
    }
    
    @Test
    public void toStringIsCorrect() {
        candidate.setMatchPercentage(88);
        assertEquals("88% Maija, VIHR", candidate.toString());
    }
}
    
   
