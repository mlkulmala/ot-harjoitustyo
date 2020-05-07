package votingaid.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import votingaid.domain.AnswerList;
import votingaid.domain.Candidate;

/**
 *
 * @author mlkul
 */
public interface CandidateDao {
    
    void getConnection() throws SQLException;
    
    public List<Candidate> getCandidatesByDistrict(String district) throws SQLException;
    
    public AnswerList getCandidateAnswers(Candidate candidate) throws SQLException;
    
    public void close() throws IOException; 
    
    
}
