/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.domain;

import java.util.List;
import votingaid.dao.QuestionDao;

/**
 * QuestionList loads the questions from database and keeps count which 
 * question is the next one in line to be shown to the user.
 * @author mlkul
 */
public class QuestionList {
    
    List<Question> questions;
    QuestionDao questionDao;
    int index;
    
    
    public QuestionList(QuestionDao questionDao) {
        this.questions = questionDao.getQuestions(); //hakee kysymykset tiedostosta
        this.questionDao = questionDao;
        this.index = 0;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public int getSize() {
        return this.questions.size();
    }
    
    /**
     * @return the current question in line
     */
    public Question getCurrent() {
        return this.questions.get(index);
    }
    /**
     * @return the next question in line
     */
    public Question getNext() {
        if (this.index < questions.size() - 1) {
            this.index++;
            return this.questions.get(index);
        }
        return null;
    }
    /**
     * 
     * @return the previous question in line
     */
    public Question getPrevious() {
        if (this.index > 0) {
            this.index--;
            return this.questions.get(index);
        }
        return null;
    }
    
    
    

    
}
