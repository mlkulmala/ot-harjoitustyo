/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.domain;

import java.util.List;
import votingaid.dao.QuestionDao;

/**
 *
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
    
    public Question getCurrent() {
        return this.questions.get(index);
    }
    
    public Question getPrevious() {
        if (this.index > 0) {
            this.index--;
            return this.questions.get(index);
        }
        return null;
    }
    
    public Question getNext() {
        if (this.index < 19) {
            this.index++;
            return this.questions.get(index);
        }
        return null;
    }
    

    
}
