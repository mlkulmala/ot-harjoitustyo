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
import java.util.ArrayList;
import java.util.List;
import votingaid.domain.Question;

/**
 *
 * @author mlkul
 */
public class QuestionMemoryDao implements QuestionDao {
    
    private Connection connection;
    
    @Override
    public List<Question> getQuestions() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:~/votingAid", "sa", "");
        
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Question"); 
        ResultSet rs = stmt.executeQuery();
        ArrayList<Question> questionList = new ArrayList<>();
        
        while (rs.next()) {
            int id = rs.getInt("Question.id");
            String questionText = rs.getString("Question.questionText");
            Question question = new Question(id, questionText);
            questionList.add(question);
        }
        
        stmt.close();
        rs.close();
        connection.close();
        
        return questionList;
    }
    
}
