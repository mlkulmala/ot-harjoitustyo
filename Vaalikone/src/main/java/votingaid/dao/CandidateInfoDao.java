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
public class CandidateInfoDao implements InfoDao{
    
    private Connection connection;
    
    @Override
    public CandidateInfo getCandidateInfo(Candidate candidate) throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:~/votingAid", "sa", "");
        
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM CandidateInfo "
            + "WHERE candidate_id = ? "); 
        stmt.setInt(1, candidate.getId());
        ResultSet rs = stmt.executeQuery();
        if (rs.next() == false) { 
            return new CandidateInfo(candidate.getId()); 
        }
        
        int id = rs.getInt("candidate_id");
        String eDistrict = rs.getString("electoraldistrict");
        String lang = rs.getString("language");
        String prof = rs.getString("profession");
        String edu = rs.getString("education");
        String reason = rs.getString("reasoning");
        String pro1 = rs.getString("promise1");
        String pro2 = rs.getString("promise2");
        String pro3 = rs.getString("promise3");
        CandidateInfo candidateInfo = new CandidateInfo(id, eDistrict, lang,
            prof, edu, reason, pro1, pro2, pro3);
         
        stmt.close();
        rs.close();
        connection.close();
        
        return candidateInfo;
    }
    
}
