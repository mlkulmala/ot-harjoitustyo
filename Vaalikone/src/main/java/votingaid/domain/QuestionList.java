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
    //int answeredQuestions; //??
    
    
    public QuestionList(QuestionDao questionDao) {
        this.questions = questionDao.getQuestions(); //hakee kysymykset tiedostosta
        this.questionDao = questionDao;
        this.index = 0;
        //this.answeredQuestions = 0; //??
        
    }
    
    public Question getCurrent() {
        return this.questions.get(index);
    }
    
    public Question getPrevious() {
        if(this.index > 0) {
            this.index--;
        return this.questions.get(index);
        }
        return null;
    }
    
    public Question getNext() {
        if(this.index < 24) {
            this.index++;
            return this.questions.get(index);
        }
        return null;
    }
    
//    public void addAnsweredQuestion() {
//        this.answeredQuestions++;
//    }
    
    // ???
//    public int getAnsweredQuestions() {
//        for(Question question : this.questions) {
//            if(question.isAnswered()) {
//                this.answeredQuestions++;
//            }
//        }
//        return this.answeredQuestions;
//    }
    
}
