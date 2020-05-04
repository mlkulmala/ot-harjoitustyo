/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import votingaid.dao.CandidateDao;
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
        //Connection connection = DriverManager.getConnection("jdbc:h2:~/votingAid", "sa", "");
        
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Candidate "
                + "JOIN District ON District.id = Candidate.district_id "
                + "JOIN Party ON Party.id = Candidate.party_id WHERE District.name = ? "); 
        stmt.setString(1, district);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Candidate> candidateList = new ArrayList<>();
        
        while(rs.next()) {
            int id = rs.getInt("Candidate.id");
            int number = rs.getInt("Candidate.number");
            String name = rs.getString("Candidate.name");
            int age = rs.getInt("Candidate.age");
            String party = rs.getString("Party.abbr");
            Candidate candidate = new Candidate(id, number, district, name, age, party);
            candidateList.add(candidate);
        }
        return candidateList;
    }
    
    @Override
    public AnswerList getCandidateAnswers(Candidate candidate) throws SQLException {
        int candidate_id = candidate.getId();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Answer "
                + "JOIN Candidate ON Candidate.id = Answer.candidate_id "
                + "WHERE Candidate.id = ? "); 
        stmt.setInt(1, candidate_id);
        ResultSet rs = stmt.executeQuery();
        
        AnswerList answerList = new AnswerList(candidate);
        while(rs.next()) {
            int question = rs.getInt("Question.id");
            int answer = rs.getInt("Answer.answer");
            answerList.setAnswer(question, answer);
        }
        return answerList;
    } 
    

    
    
    
    
//    @Override
//    public List<AnswerList> getAllAnswers() {
//        ArrayList<AnswerList> allAnswers = new ArrayList<>();
//        
//        //tietokantaa ei vielä luotu, joten luodaan tässä
//        //ehdokkaat ja vastaukset testaamista varten
//        
//        Candidate aku = new Candidate(331, "Uusimaa", "Aku", 34, "KOK");
//        Candidate lasse = new Candidate(124, "Uusimaa", "Lasse", 49, "VAS");
//        Candidate heli = new Candidate(127, "Uusimaa", "Heli", 25, "VIHR");
//        
//        AnswerList listAku = new AnswerList(aku);
//        AnswerList listLasse = new AnswerList(lasse);
//        AnswerList listHeli = new AnswerList(heli);
//        
//        for (int i = 1; i <= 20; i++) {
//            listAku.setAnswer(i, 2);
//            listLasse.setAnswer(i, 5);
//            listHeli.setAnswer(i, 3);
//        }
//        
//        allAnswers.add(listAku); 
//        allAnswers.add(listLasse);
//        allAnswers.add(listHeli);
//        
//        
//        
//        return allAnswers;
//    }
     
     
    
}
