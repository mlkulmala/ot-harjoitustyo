package votingaid.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import votingaid.domain.Candidate;
import votingaid.domain.CandidateInfo;

/**
 * Data Access Object for loading additional information 
 * of a candidate from the database.
 * @author mlkul
 */
public class CandidateInfoDao implements InfoDao {
    
    private Connection connection;
    
    @Override
    public CandidateInfo getCandidateInfo(Candidate candidate) throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:./votingAid", "sa", "");
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM CandidateInfo "
            + "WHERE candidate_id = ? "); 
        stmt.setInt(1, candidate.getId());
        ResultSet rs = stmt.executeQuery();
//        if (rs.next() == false) { 
//            return new CandidateInfo(candidate.getId()); 
//        }
        CandidateInfo candidateInfo = new CandidateInfo(candidate.getId());
        if (rs.next()) {
            candidateInfo = new CandidateInfo(rs.getInt("candidate_id"),
                rs.getString("electoraldistrict"), rs.getString("language"), 
                rs.getString("profession"), rs.getString("education"), 
                rs.getString("reasoning"));
        }
        
         
        stmt.close();
        rs.close();
        connection.close();
        return candidateInfo;
    }
    
}
