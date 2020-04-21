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
    
    @Before
    public void setUp() {
        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
        candidateLogic = new CandidateLogic(candMemoryDao, "Uusimaa");
    }
    
    
    
    @Test
    public void firstOneOnFirstQuestionIsCorrect() {
        candidateLogic.createCandidateList();
        List<Candidate> candidates = candidateLogic.compareToCandidates(3, 1);
        assertEquals("100% Lasse, VAS", candidates.get(0).toString());
    }
    
    @Test
    public void secondOneOnFirstQuestionIsCorrect() {
        candidateLogic.createCandidateList();
        List<Candidate> candidates = candidateLogic.compareToCandidates(3, 1);
        assertEquals("75% Aku, KOK", candidates.get(1).toString());
    }
    
    @Test
    public void thirdOneOnFirstQuestionIsCorrect() {
        candidateLogic.createCandidateList();
        List<Candidate> candidates = candidateLogic.compareToCandidates(3, 1);
        assertEquals("50% Heli, VIHR", candidates.get(2).toString());
    }
    
    @Test
    public void firstOneOnSecondQuestionIsCorrect() {
        candidateLogic.createCandidateList();
        candidateLogic.compareToCandidates(3, 1);
        List<Candidate> candidates = candidateLogic.compareToCandidates(2, 2);
        assertEquals("75% Aku, KOK", candidates.get(0).toString());
    }
    
    @Test
    public void firstOneOnFifthQuestionIsCorrect() {
        candidateLogic.createCandidateList();
        candidateLogic.compareToCandidates(3, 1);
        candidateLogic.compareToCandidates(2, 2);
        candidateLogic.compareToCandidates(4, 3);
        candidateLogic.compareToCandidates(4, 4);
        List<Candidate> candidates = candidateLogic.compareToCandidates(5, 5);
        assertEquals("80% Lasse, VAS", candidates.get(0).toString());
    }
   
}
