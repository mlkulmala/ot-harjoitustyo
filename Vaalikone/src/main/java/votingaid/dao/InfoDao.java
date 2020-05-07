/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.dao;

import votingaid.domain.Candidate;
import votingaid.domain.CandidateInfo;

/**
 *
 * @author mlkul
 */
public interface InfoDao {
    
    public CandidateInfo getCandidateInfo(Candidate candidate) throws Exception;
    
}
