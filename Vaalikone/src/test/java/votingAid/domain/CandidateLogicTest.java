/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingAid.domain;



import votingaid.domain.CandidateLogic;
import votingaid.domain.Candidate;
import votingaid.domain.AnswerList;
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
    List<AnswerList> allAnswers;
    
    @Before
    public void setUp() {
        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
        candidateLogic = new CandidateLogic(candMemoryDao, "Uusimaa");
        candidateLogic.createAnswerList();
    }
    
    
    
    @Test
    public void firstOneOnFirstQuestionIsCorrect() {
        allAnswers = candidateLogic.compareToCandidateAnswers(1, 3);
        assertEquals("100% Heli, VIHR", allAnswers.get(0).toString());
    }
    
    @Test
    public void secondOneOnFirstQuestionIsCorrect() {
        allAnswers = candidateLogic.compareToCandidateAnswers(1, 3);
        assertEquals("75% Aku, KOK", allAnswers.get(1).toString());
    }
    
    @Test
    public void thirdOneOnFirstQuestionIsCorrect() {
        allAnswers = candidateLogic.compareToCandidateAnswers(1, 3);
        assertEquals("50% Lasse, VAS", allAnswers.get(2).toString());
    }
    
    @Test
    public void thirdOneOnSecondQuestionIsCorrect() {
        candidateLogic.compareToCandidateAnswers(1, 3);
        allAnswers = candidateLogic.compareToCandidateAnswers(2, 2);
        assertEquals("37% Lasse, VAS", allAnswers.get(2).toString());
    }
    
    @Test
    public void firstOneOnFifthQuestionIsCorrect() {
        candidateLogic.compareToCandidateAnswers(1, 3);
        candidateLogic.compareToCandidateAnswers(2, 2);
        candidateLogic.compareToCandidateAnswers(3, 2);
        candidateLogic.compareToCandidateAnswers(4, 4);
        allAnswers = candidateLogic.compareToCandidateAnswers(5, 5);
        assertEquals("75% Heli, VIHR", allAnswers.get(0).toString());
    }
   
}
