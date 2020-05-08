package votingAid.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import votingaid.domain.CandidateInfo;
import votingaid.domain.Candidate;

/**
 *
 * @author mlkul
 */
public class CandidateInfoTest {
    
    CandidateInfo info;
    
    @Before
    public void setUp() {
        Candidate maija = new Candidate(11, 123, "Hki", "Maija", 25, "FP");
        info = new CandidateInfo(maija, "Hki", "Suomi", "opiskelija", 
                "korkeakoulututkinto", "Koska olen paras.");
    }
    
    @Test
    public void constructionOk() {
        assertEquals("Maija", info.getCandidate().getName());
        assertEquals("Hki", info.getElectoralDistrict());
        assertEquals("Suomi", info.getLanguage());
        assertEquals("opiskelija", info.getProfession());
        assertEquals("korkeakoulututkinto", info.getEducation());
        assertEquals("Koska olen paras.", info.getReasoning());
    }
    
    @Test
    public void constructionOfEmptyInfoOk() {
        Candidate matti = new Candidate(15, 246, "Hki", "Matti", 70, "PS");
        info = new CandidateInfo(matti);
        assertEquals(15, info.getCandidate().getId());
        assertEquals("-", info.getElectoralDistrict());
        assertEquals("-", info.getLanguage());
        assertEquals("-", info.getProfession());
        assertEquals("-", info.getEducation());
        assertEquals("-", info.getReasoning());
    }
            
    
}
