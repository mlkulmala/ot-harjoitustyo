/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.dao;

import java.util.*;
import votingaid.domain.AnswerList;

/**
 *
 * @author mlkul
 */
public interface CandidateDao {
    
    
    public List<AnswerList> getAllAnswers();
    
}
