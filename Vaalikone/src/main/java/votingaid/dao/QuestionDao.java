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
