package votingaid.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import votingaid.domain.AnswerList;
import votingaid.domain.Candidate;
import votingaid.domain.CandidateInfo;

/**
 * Class that loads all information related to the candidates and answers.
 * @author mlkul
 */
public class CandidateMemoryDao implements CandidateDao {
    
    private Connection connection;
    
    /**
     * Activate connection to the database.
     * @throws SQLException thrown on connection fail.
     */
    @Override
    public void getConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:./votingAid", "sa", "");
    }
    
    /**
     * Get all candidates.
     * @param district Electoral district of the candidates that will be listed.
     * @return List of Candidates.
     * @throws SQLException thrown if query fails.
     */
    @Override
    public List<Candidate> getCandidatesByDistrict(String district) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Candidate "
                + "JOIN District ON District.id = Candidate.district_id "
                + "JOIN Party ON Party.id = Candidate.party_id WHERE District.name = ? "); 
        stmt.setString(1, district);
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Candidate> candidateList = new ArrayList<>();
        while (rs.next()) {
            Candidate candidate = new Candidate(rs.getInt("Candidate.id"),
                    rs.getInt("Candidate.number"), district, 
                    rs.getString("Candidate.name"), rs.getInt("Candidate.age"),
                    rs.getString("Party.abbr"));
            candidateList.add(candidate);
        }
        return candidateList;
    }
    
    
    /**
     * Get all answers of a candidate.
     * @param candidate
     * @return AnswerList with all answers of a single candidate.
     * @throws SQLException thrown if query fails.
     */
    @Override
    public AnswerList getCandidateAnswers(Candidate candidate) throws SQLException {
        int candidateId = candidate.getId();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Candidate "
                + "JOIN Answer ON Candidate.id = Answer.candidate_id "
                + "JOIN Question ON Question.id = Answer.question_id "
                + "WHERE Candidate.id = ? ");
        stmt.setInt(1, candidateId);
        ResultSet rs = stmt.executeQuery();
        
        AnswerList answerList = new AnswerList(candidate);
        while (rs.next()) {
            int question = rs.getInt("Question.id");
            int answer = rs.getInt("Answer.answer");
            answerList.setAnswer(question, answer);
        } 
        return answerList;
    } 
    
    /**
     * Get additional information of a candidate.
     * @param candidate
     * @return CandidateInfo.
     * @throws SQLException 
     */
    @Override
    public CandidateInfo getCandidateInfo(Candidate candidate) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM CandidateInfo "
            + "WHERE candidate_id = ? "); 
        stmt.setInt(1, candidate.getId());
        ResultSet rs = stmt.executeQuery();

        CandidateInfo candidateInfo = new CandidateInfo(candidate);
        if (rs.next()) {
            candidateInfo = new CandidateInfo(candidate,
                rs.getString("electoraldistrict"), rs.getString("language"), 
                rs.getString("profession"), rs.getString("education"), 
                rs.getString("reasoning"));
        }
         
        stmt.close();
        rs.close();
        return candidateInfo;
    }
    
    /**
     * Close the database connection.
     * @throws IOException  
     */
    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
}
