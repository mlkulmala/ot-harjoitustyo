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

/**
 *
 * @author mlkul
 */
public class CandidateMemoryDao implements CandidateDao {
    
    private Connection connection;
    
    @Override
    public void getConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:./votingAid", "sa", "");
    }
    
    @Override
    public List<Candidate> getCandidatesByDistrict(String district) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Candidate "
                + "JOIN District ON District.id = Candidate.district_id "
                + "JOIN Party ON Party.id = Candidate.party_id WHERE District.name = ? "); 
        stmt.setString(1, district);
        ResultSet rs = stmt.executeQuery();
//        if (rs.next() == false) { 
//            return null; 
//        }
        
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
    
    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
}
