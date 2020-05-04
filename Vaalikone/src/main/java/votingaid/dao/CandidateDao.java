/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
