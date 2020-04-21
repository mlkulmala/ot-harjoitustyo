/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingAid.domain;



import votingaid.domain.CandidateLogic;
import votingaid.domain.Candidate;
import votingaid.dao.CandidateMemoryDao;
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
    List<Candidate> candidates;
    
    @Before
    public void setUp() {
        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
        candidateLogic = new CandidateLogic(candMemoryDao, "Uusimaa");
    }
    
    
    
    @Test
    public void firstOneOnFirstQuestionIsCorrect() {
        candidateLogic.createCandidateList();
        candidates = candidateLogic.compareToCandidates(1, 3);
        assertEquals("100% Heli, VIHR", candidates.get(0).toString());
    }
    
    @Test
    public void secondOneOnFirstQuestionIsCorrect() {
        candidateLogic.createCandidateList();
        candidates = candidateLogic.compareToCandidates(1, 3);
        assertEquals("75% Aku, KOK", candidates.get(1).toString());
    }
    
    @Test
    public void thirdOneOnFirstQuestionIsCorrect() {
        candidateLogic.createCandidateList();
        candidates = candidateLogic.compareToCandidates(1, 3);
        assertEquals("50% Lasse, VAS", candidates.get(2).toString());
    }
    
    @Test
    public void thirdOneOnSecondQuestionIsCorrect() {
        candidateLogic.createCandidateList();
        candidateLogic.compareToCandidates(1, 3);
        candidates = candidateLogic.compareToCandidates(2, 1);
        assertEquals("25% Lasse, VAS", candidates.get(2).toString());
    }
    
    @Test
    public void firstOneOnFifthQuestionIsCorrect() {
        candidateLogic.createCandidateList();
        candidateLogic.compareToCandidates(1, 3);
        candidateLogic.compareToCandidates(2, 2);
        candidateLogic.compareToCandidates(3, 2);
        candidateLogic.compareToCandidates(4, 4);
        candidates = candidateLogic.compareToCandidates(5, 5);
        assertEquals("75% Heli, VIHR", candidates.get(0).toString());
    }
   
}
