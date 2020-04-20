///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package votingaid.ui;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import votingaid.domain.CandidateLogic;
//import votingaid.domain.Candidate;
//import votingaid.dao.CandidateMemoryDao;
//import java.util.*;
//import java.lang.*;
//import java.nio.charset.Charset;
//
///**
// *
// * @author mlkul
// */
//public class UI {
//
//    private CandidateLogic candidatelogic;
//    private List<Candidate> candidates;
//    private Scanner scanner;
//    private String[] claims;
//    private int c;
//
//    public UI(Scanner scanner) {
//        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
//        this.candidatelogic = new CandidateLogic(candMemoryDao);
//        this.candidates = new ArrayList<>();
//        this.scanner = scanner;
//        this.claims = new String[6];
//        this.c = 1;
//    }
//
//    public void start() {
//        
//        this.candidatelogic.createCandidateList();
//
//        System.out.println("\nWelcome to Voting Aid Application!\n");
//        System.out.println("Respond to the statements\n");
//        readAnswers();
//        System.out.println("\nSee results...");
//    }
//
//    public void readAnswers() {
//        try ( Scanner reader = new Scanner(new File("votingaidquestions.txt"))) {
//            while (reader.hasNextLine()) {
//                System.out.println(reader.nextLine());
//                System.out.println("\n(1=Completely disagree, 2=Partly disagree");
//                System.out.println("3=I don't know, 4=Partly agree");
//                System.out.println("5=Completely agree, x=Stop)");
//                String answer = scanner.nextLine();
//                int number = 0;
//                if (answer.equals("x")) {
//                    break;
//                } else { 
//                    number = Integer.valueOf(answer);
//                }
//                compareAndListAnswers(number);
//            }
//        } catch (Exception e) {
//            System.out.println("Virhe: " + e.getMessage());
//        }
//    }
//
//    public void compareAndListAnswers(int number) {
//        this.candidates = candidatelogic.compareToCandidates(c, number);
//        System.out.println("\n* * * * * * * * * *");
//        for (Candidate x : this.candidates) {
//            System.out.println(x.toString());
//        }
//        System.out.println();
//        c++;
//    }
//
////    public void readAndCompare() {     
////        for (int i = 0; i <= 4; i++) {
////            System.out.println("\n" + claims[i]);  
////            System.out.println("(1=täysin eri mieltä, 2=osittain eri mieltä");
////            System.out.println("3=en osaa sanoa, 4=osittain samaa mieltä");
////            System.out.println("5=täysin samaa mieltä, x=lopeta)");
////            String answer = scanner.nextLine();
////            int number = 0;
////            if (answer.equals("x")) {
////                break;
////            } else {
////                number = Integer.valueOf(answer);
////            }
////            this.candidates = candidatelogic.compareToCandidates(c, i, number);
////            System.out.println("\n* * * * * * * * * *");
////            for (Candidate x: this.candidates) {
////                System.out.println(x.toString());
////            }
////            c++;
////        }
////    }
//}
