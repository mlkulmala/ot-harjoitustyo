/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import votingaid.dao.QuestionDao;

/**
 *
 * @author mlkul
 */
public class QuestionMemoryDao implements QuestionDao {

    @Override
    public List<String> getQuestions() {
        ArrayList<String> questions = new ArrayList<>();
        try ( Scanner reader = new Scanner(new File("votingaidquestions.txt"))) {
            while (reader.hasNextLine()) {
                questions.add(reader.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
        }
        
        
        return questions;
    }
    
}
