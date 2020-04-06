/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingaid.ui;

import java.io.File;
import votingaid.domain.CandidateLogic;
import votingaid.domain.Candidate;
import votingaid.dao.CandidateMemoryDao;
import java.util.*;
import java.lang.*;

/**
 *
 * @author mlkul
 */
public class UI {

    private CandidateLogic candidatelogic;
    private List<Candidate> candidates;
    private Scanner scanner;
    private String[] claims;
    private int c;

    public UI(Scanner scanner) {
        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
        this.candidatelogic = new CandidateLogic(candMemoryDao);
        this.candidates = new ArrayList<>();
        this.scanner = scanner;
        this.claims = new String[6];
        this.c = 1;
    }

    public void start() {
        //luetaan myöhemmin tiedostosta/tietokannasta:
        claims[0] = "Väite 1: Rajat auki!\n";
        claims[1] = "Väite 2: Perustulo kaikille\n";
        claims[2] = "Väite 3: Viinaa vain Virosta\n";
        claims[3] = "Väite 4: Suomi karanteeniin\n";
        claims[4] = "Väite 5: Opiskelijoille lisää rahaa\n";

        this.candidatelogic.createCandidateList();

        System.out.println("\nTERVETULOA VAALIKONEESEEN!\n");
        System.out.println("Vastaa seuraaviin väitteisiin\n");
        readAnswers();
        System.out.println("\nSiirrytään tuloksiin");
    }

    public void readAnswers() {
        try ( Scanner lukija = new Scanner(new File("votingaidquestions.txt"))) {
            while (lukija.hasNextLine()) {
                System.out.println(lukija.nextLine());
                System.out.println("\n(1=täysin eri mieltä, 2=osittain eri mieltä");
                System.out.println("3=en osaa sanoa, 4=osittain samaa mieltä");
                System.out.println("5=täysin samaa mieltä, x=lopeta)");
                String answer = scanner.nextLine();
                int number = 0;
                if (answer.equals("x")) {
                    break;
                } else {
                    number = Integer.valueOf(answer);
                }
                compareAndListAnswers(number);
            }
        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
        }
    }

    public void compareAndListAnswers(int number) {
        this.candidates = candidatelogic.compareToCandidates(c, number);
        System.out.println("\n* * * * * * * * * *");
        for (Candidate x : this.candidates) {
            System.out.println(x.toString());
        }
        c++;
    }

//    public void readAndCompare() {     
//        for (int i = 0; i <= 4; i++) {
//            System.out.println("\n" + claims[i]);  
//            System.out.println("(1=täysin eri mieltä, 2=osittain eri mieltä");
//            System.out.println("3=en osaa sanoa, 4=osittain samaa mieltä");
//            System.out.println("5=täysin samaa mieltä, x=lopeta)");
//            String answer = scanner.nextLine();
//            int number = 0;
//            if (answer.equals("x")) {
//                break;
//            } else {
//                number = Integer.valueOf(answer);
//            }
//            this.candidates = candidatelogic.compareToCandidates(c, i, number);
//            System.out.println("\n* * * * * * * * * *");
//            for (Candidate x: this.candidates) {
//                System.out.println(x.toString());
//            }
//            c++;
//        }
//    }
}
