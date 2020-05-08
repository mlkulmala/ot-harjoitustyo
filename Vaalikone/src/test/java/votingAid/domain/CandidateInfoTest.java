package votingAid.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import votingaid.domain.CandidateInfo;

/**
 *
 * @author mlkul
 */
public class CandidateInfoTest {
    
    CandidateInfo info;
    
    @Before
    public void setUp() {
        info = new CandidateInfo(11, "Hki", "Suomi", "opiskelija", 
                "korkeakoulututkinto", "Koska olen paras.");
    }
    
    @Test
    public void constructionCorrect() {
        assertEquals(11, info.getId());
        assertEquals("Hki", info.getElectoralDistrict());
        assertEquals("Suomi", info.getLanguage());
        assertEquals("opiskelija", info.getProfession());
        assertEquals("korkeakoulututkinto", info.getEducation());
        assertEquals("Koska olen paras.", info.getReasoning());
    }
    
    @Test
    public void constructionOfEmptyInfoCorrect() {
        info = new CandidateInfo(17);
        assertEquals(17, info.getId());
        assertEquals("-", info.getElectoralDistrict());
        assertEquals("-", info.getLanguage());
        assertEquals("-", info.getProfession());
        assertEquals("-", info.getEducation());
        assertEquals("-", info.getReasoning());
    }
            
    
}
