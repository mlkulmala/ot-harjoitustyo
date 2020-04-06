/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingAid;



import votindAid.domain.CandidateLogic;
import votindAid.domain.Candidate;
import votindAid.dao.CandidateMemoryDao;
import java.util.ArrayList;
import java.util.List;
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
public class CandidateLogicTest {
    
    CandidateLogic candidateLogic;
    
    @Before
    public void setUp() {
        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
        candidateLogic = new CandidateLogic(candMemoryDao);
    }
    
    @Test
    public void firstOneOnFirstQuestionIsCorrect() {
        candidateLogic.createCandidateList();
        List<Candidate> candidates = candidateLogic.compareToCandidates(1, 0, 3);
        assertEquals("100% Lasse, VAS", candidates.get(0).toString());
    }
    
    @Test
    public void secondOneOnFirstQuestionIsCorrect() {
        candidateLogic.createCandidateList();
        List<Candidate> candidates = candidateLogic.compareToCandidates(1, 0, 3);
        assertEquals("75% Aku, KOK", candidates.get(1).toString());
    }
    
    @Test
    public void thirdOneOnFirstQuestionIsCorrect() {
        candidateLogic.createCandidateList();
        List<Candidate> candidates = candidateLogic.compareToCandidates(1, 0, 3);
        assertEquals("50% Heli, VIHR", candidates.get(2).toString());
    }
    
    @Test
    public void firstOneOnFifthQuestionIsCorrect() {
        candidateLogic.createCandidateList();
        candidateLogic.compareToCandidates(1, 0, 3);
        candidateLogic.compareToCandidates(2, 1, 2);
        candidateLogic.compareToCandidates(3, 2, 4);
        candidateLogic.compareToCandidates(4, 3, 4);
        List<Candidate> candidates = candidateLogic.compareToCandidates(5, 4, 5);
        assertEquals("80% Lasse, VAS", candidates.get(0).toString());
    }
   
}
