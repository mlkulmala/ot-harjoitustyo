/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import votingaid.dao.QuestionDao;
import votingaid.domain.Question;

/**
 *
 * @author mlkul
 */
public class QuestionMemoryDao implements QuestionDao {

    @Override
    public List<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        //ArrayList<String> questions = new ArrayList<>();
        
        try {
            File fileDir = new File("questions_new.txt");
   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(fileDir), "UTF8"));
            String str;
            int i = 1;
            while ((str = in.readLine()) != null) {
                //questions.add(str);
                questions.add(new Question(i, str));
                i++;
            }
            in.close();
            
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return questions;
    }
    
}
