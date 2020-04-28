/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingAid.domain;

import votingaid.domain.Question;
import votingaid.dao.QuestionMemoryDao;
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
        QuestionMemoryDao questionMemoryDao = new QuestionMemoryDao();
        question = questionMemoryDao.getQuestions().get(0);
        //question = new Question(2, "Mitä tämä tarkoittaa?");
    }
    
//    @Test
//    public void questionNotAnsweredAtBeginning() {
//        assertEquals(false, question.isAnswered());
//    }
    
    @Test
    public void questionTextIsCorrect() {
        assertTrue(question.getQuestionText().contains("olla edelläkävijä"));
    }
    
    @Test
    public void questionIdIsCorrect() {
        assertEquals(1, question.getId());
    }
    
//    @Test
//    public void questionUserAnswerIsCorrect() {
//        assertEquals(0, question.getUserAnswer());
//    }
    
    
    
    
}
