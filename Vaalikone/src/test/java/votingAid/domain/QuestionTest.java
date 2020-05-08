/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingAid.domain;

import votingaid.domain.Question;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import votingaid.dao.QuestionMemoryDao;
import votingaid.domain.QuestionList;

/**
 *
 * @author mlkul
 */
public class QuestionTest {
    
    Question question;
    
    @Before
    public void setUp() {
        question = new Question(1, "VotingAid");
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
    public void setUserAnswerGivesCorrectAnswer() {
        question.setUserAnswer(5);
        question.setUserAnswer(3);
        assertEquals(3, question.getUserAnswer());
        assertEquals(true, question.isAnswered());
    }
    
    
    
    
    
    
}
