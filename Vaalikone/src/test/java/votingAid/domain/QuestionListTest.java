package votingAid.domain;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import votingaid.domain.QuestionList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import votingaid.dao.QuestionMemoryDao;

/**
 *
 * @author mlkul
 */
public class QuestionListTest {
    
    QuestionList questionList;
    
    @Before
    public void setUp() {
        QuestionMemoryDao questionMemoryDao = new QuestionMemoryDao();
        questionList = new QuestionList(questionMemoryDao);
        try {
            questionList.getQuestions();
        } catch (Exception ex) {
            ex.printStackTrace();;
        }
    }
    
    @Test
    public void getCurrentGivesCorrectAtTheBeginning() {
        assertEquals(1, questionList.getCurrent().getId());
    }
    
    @Test
    public void getPreviousGivesNullAtTheBeginning() {
        assertEquals(null, questionList.getPrevious());
    }
    
    @Test
    public void getNextGivesCorrectAtTheBeginning() {
        assertEquals(2, questionList.getNext().getId());
    }
    
    @Test
    public void getPreviousGivesCorrect() {
        questionList.getNext();
        questionList.getNext();
        questionList.getNext();
        assertEquals(3, questionList.getPrevious().getId());
    }
    
    @Test
    public void indexGrowsCorrectly() {
        for (int i = 1; i <= 5; i++) {
            questionList.getNext();
        }
        assertEquals(5, questionList.getIndex());
    }
    
}
