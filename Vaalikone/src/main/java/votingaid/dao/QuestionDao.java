/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.dao;

import java.sql.SQLException;
import java.util.List;
import votingaid.domain.Question;

/**
 *
 * @author mlkul
 */
public interface QuestionDao {
    
    public List<Question> getQuestions() throws SQLException;
    
}
