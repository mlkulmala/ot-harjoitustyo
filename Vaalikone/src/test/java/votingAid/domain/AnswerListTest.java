package votingAid.domain;


import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import votingaid.domain.Candidate;
import votingaid.domain.AnswerList;

/**
 *
 * @author mlkul
 */
public class AnswerListTest {
    
    AnswerList answerList;
    Candidate candidate;
    
    @Before
    public void setUp() {
        candidate = new Candidate(1, 331, "Uusimaa", "Aku", 34, "KOK");
        answerList = new AnswerList(candidate);
    }
    
    @Test
    public void getCandidateGivesCorrect() {
        Candidate ben = new Candidate(2, 345, "Helsinki", "Ben", 56, "KOK");
        answerList = new AnswerList(ben);
        answerList.setAnswer(1, 5);
        answerList.setAnswer(1, 1);
        assertEquals("Ben", answerList.getCandidate().getName());
        assertEquals(1, answerList.getAnswer(1));
    }
    
    @Test
    public void setAnswerGivesCorrect() {
        answerList.setAnswer(1, 5);
        answerList.setAnswer(2, 5);
        assertEquals(5, answerList.getAnswer(2));
    }
   
    @Test
    public void zeroMatchAtBeginning() {
        assertEquals(0, answerList.getMatchPercentage());
    }
    
    @Test
    public void singleMatchesGiveCorrectPercentage() {
        answerList.setSingleMatch(1, 75);
        answerList.setSingleMatch(2, 75);
        answerList.setSingleMatch(3, 50);
        answerList.setSingleMatch(4, 75);
        answerList.setSingleMatch(5, 0);
        assertEquals(55, answerList.getMatchPercentage());
    }
    
    
    @Test
    public void setSingleMatchGivesRightPercentage() {
        answerList.setSingleMatch(1, 75);
        answerList.setSingleMatch(1, 25);
        assertEquals(25, answerList.getSingleMatch(1));
    }
    
    @Test
    public void toStringIsCorrect() {
        answerList.setSingleMatch(1, 75);
        answerList.setSingleMatch(2, 75);
        answerList.setSingleMatch(3, 50);
        answerList.setSingleMatch(4, 75);
        answerList.setSingleMatch(5, 0);
        assertEquals("55% Aku, KOK", answerList.toString());
    }
 
}
