package votingaid.domain;

import java.sql.SQLException;
import java.util.List;
import votingaid.dao.QuestionDao;

/**
 * A class that loads the questions from the database. Keeps count which 
 * question is the next one in line to be shown to the user.
 * @author mlkul
 */
public class QuestionList {
    
    List<Question> questions;
    QuestionDao questionDao;
    int index;
    
    /**
     * Construct 
     * @param questionDao 
     */
    public QuestionList(QuestionDao questionDao) {
        this.questionDao = questionDao;
        this.index = 0;
    }
    
    /**
     * Load the questions from the database.
     * @throws SQLException 
     */
    public void getQuestions() throws SQLException {
        this.questions = questionDao.getQuestions();
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
