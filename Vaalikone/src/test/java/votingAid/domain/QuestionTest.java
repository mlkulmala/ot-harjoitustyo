/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingAid.domain;

import votingaid.domain.Question;
import votingaid.dao.QuestionFileDao;
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
public class QuestionTest {
    
    Question question;
    
    @Before
    public void setUp() {
        QuestionFileDao questionMemoryDao = new QuestionFileDao();
        question = questionMemoryDao.getQuestions().get(0);
    }
    
    @Test
    public void questionNotAnsweredAtBeginning() {
        assertEquals(false, question.isAnswered());
    }
    
    @Test
    public void questionSetAnswered() {
        question.setAnswered();
        assertEquals(true, question.isAnswered());
    }
    
    @Test
    public void firstQuestionIsReadCorrectly() {
        assertEquals(false, question.isAnswered());
        assertTrue(question.getQuestionText().contains("olla edelläkävijä"));
        assertEquals(1, question.getId());
        assertEquals(0, question.getUserAnswer());
    }
    
    @Test
    public void setUserAnswerGivesCorrectAnswer() {
        question.setUserAnswer(5);
        question.setUserAnswer(3);
        assertEquals(3, question.getUserAnswer());
        assertEquals(true, question.isAnswered());
    }
    
    
    
    
    
    
}
