package votingaid.domain;

import java.io.IOException;
import java.sql.SQLException;
import votingaid.dao.CandidateDao;
import java.util.*;

/**
 * A class that contains lists of all candidates and their answers. 
 * Contains the operation needed for comparison of election candidates.
 * @author mlkul
 */
public class CandidateLogic {
    
    List<Candidate> allCandidates;
    List<AnswerList> allAnswers;
    CandidateDao candidateDao;
    
    /**
     * Construct a new CandidateLogic using given DAO. 
     * @param candidatedao dao that is used for loading candidates
     * 
     */
    public CandidateLogic(CandidateDao candidatedao) {
        allCandidates = new ArrayList<>();
        allAnswers = new ArrayList<>();
        candidateDao = candidatedao;
    }
    
    public int getCountOfAllAnswerLists() {
        return allAnswers.size();
    }
    
    /**
     * Load all candidates and their answers from database.
     * @param district Electional district whose candidates' answers are loaded.
     */
    public void createAnswerLists(String district) throws SQLException, IOException {
        
        candidateDao.getConnection();
        allCandidates = candidateDao.getCandidatesByDistrict(district);
        for (Candidate candidate : allCandidates) {
            AnswerList answerList = candidateDao.getCandidateAnswers(candidate);
            allAnswers.add(answerList);
        }
        candidateDao.close();
    }
                                                                
    /**
     * Compare the answer given by the user with the answers of all candidates. 
     * Count the percentage unit that indicates how well the opinions match. 
     * Sort the list of AnswerLists according to the matchPercentage.
     * @param questionNumber the number of the question whose answers are being compared.
     * @param userAnswer the answer of the user on scale 1 to 5.
     * @return the list of AnswerLists sorted by the matchPercentage of each 
     * candidate
     */
    public List<AnswerList> compareToCandidateAnswers(int questionNumber, int userAnswer) {
        for (AnswerList answerList : allAnswers) {
            if (!answerList.getAnswers().keySet().isEmpty()) {
                int candAnswer = answerList.getAnswer(questionNumber);
                if (candAnswer != 0) {
                    int diff = Math.abs(userAnswer - candAnswer);
                    int percentage = 100 - diff * 25;
                    answerList.setSingleMatch(questionNumber, percentage);
                }
            }
        } 
        Collections.sort(allAnswers);
        return allAnswers;
    }
    
    
    public AnswerList getAnswerList(int number) {
        return this.allAnswers.get(number);
    }
}
