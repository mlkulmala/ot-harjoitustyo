package votingAid.domain;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import votingaid.domain.CandidateLogic;
import votingaid.domain.Candidate;
import votingaid.domain.AnswerList;
import votingaid.dao.CandidateMemoryDao;
import java.util.List;
import org.junit.Before;
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
    public void setUp() throws SQLException, IOException {
        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
        this.candidateLogic = new CandidateLogic(candMemoryDao);
        this.candidateLogic.createAnswerLists("Helsinki");
    }
    
   
    @Test
    public void firstOneOnFirstQuestionIsCorrect() {
        allAnswers = candidateLogic.compareToCandidateAnswers(1, 1);
        assertEquals("100% Jussi, PS", allAnswers.get(0).toString());
    }
    
    @Test
    public void firstOneAfterSecondQuestionIsCorrect() {
        candidateLogic.compareToCandidateAnswers(1, 4);
        allAnswers = candidateLogic.compareToCandidateAnswers(2, 2);
        assertEquals("100% Eveliina, SDP", allAnswers.get(0).toString());
    }
    
    @Test
    public void secondOneAfterSecondQuestionIsCorrect() {
        candidateLogic.compareToCandidateAnswers(1, 4);
        allAnswers = candidateLogic.compareToCandidateAnswers(2, 2);
        assertEquals("88% Juha, PS", allAnswers.get(1).toString());
    }
    
    @Test
    public void thirdOneAfterSecondQuestionIsCorrect() {
        candidateLogic.compareToCandidateAnswers(1, 4);
        allAnswers = candidateLogic.compareToCandidateAnswers(2, 2);
        assertEquals("88% Lotta, KOK", allAnswers.get(2).toString());
    }
    
    @Test
    public void firstOneAfterThirdQuestionIsCorrect() {
        candidateLogic.compareToCandidateAnswers(1, 4);
        candidateLogic.compareToCandidateAnswers(2, 4);
        allAnswers = candidateLogic.compareToCandidateAnswers(3, 3);
        assertEquals("92% Ben, KOK", allAnswers.get(0).toString());
    }
    
    @Test
    public void secondOneAfterThirdQuestionIsCorrect() {
        candidateLogic.compareToCandidateAnswers(1, 4);
        candidateLogic.compareToCandidateAnswers(2, 4);
        allAnswers = candidateLogic.compareToCandidateAnswers(3, 3);
        assertEquals("83% Jaana, KOK", allAnswers.get(1).toString());
    }
    
    @Test
    public void firstOneOnFifthQuestionIsCorrect() {
        candidateLogic.compareToCandidateAnswers(1, 5);
        candidateLogic.compareToCandidateAnswers(2, 1);
        candidateLogic.compareToCandidateAnswers(3, 5);
        candidateLogic.compareToCandidateAnswers(4, 5);
        allAnswers = candidateLogic.compareToCandidateAnswers(5, 4);
        assertEquals("100% Atte, VIHR", allAnswers.get(0).toString());
        assertEquals("100% Atte, VIHR", candidateLogic.getAnswerList(0).toString());
    }
   
    @Test
    public void getCountGivesCorrect() {
        assertEquals(13, candidateLogic.getCountOfAllAnswerLists());
    }
}
