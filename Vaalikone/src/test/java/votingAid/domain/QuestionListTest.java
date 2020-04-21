/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingAid.domain;

import votingaid.dao.QuestionMemoryDao;
import votingaid.domain.QuestionList;
import votingaid.domain.Question;
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
public class QuestionListTest {
    
    QuestionList questionList;
    
    @Before
    public void setUp() {
        QuestionMemoryDao questionMemoryDao = new QuestionMemoryDao();
        questionList = new QuestionList(questionMemoryDao);
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
    public void getNextGivesNullAtTheEnd() {
        for (int i = 1; i <= 19; i++) {
            questionList.getNext();
        }
        assertEquals(null, questionList.getNext());
    }
    
}
