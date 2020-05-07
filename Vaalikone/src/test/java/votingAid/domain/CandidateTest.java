package votingAid.domain;

import votingaid.domain.Candidate;
import org.junit.Before;
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
    public void candidateSettingIsCorrect() {
        assertEquals(123, candidate.getNumber());
        assertEquals("Maija", candidate.getName());
        assertEquals(30, candidate.getAge());
        assertEquals("Uusimaa", candidate.getDistrict());
        assertEquals("VIHR", candidate.getParty());
        assertEquals("Maija, 30, VIHR", candidate.toString());
    }

    
}
    
   
