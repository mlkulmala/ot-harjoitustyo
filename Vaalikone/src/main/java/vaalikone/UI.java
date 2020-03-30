/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaalikone;

import java.util.*;
import java.lang.*;

/**
 *
 * @author mlkul
 */
public class UI {
    private ArrayList<Candidate> candidates;
    private Candidate candidate;
    private Scanner scanner;
    private String[] claims;
    private int c;

    public UI(Scanner scanner) {
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
        
        //luetaan tietokannasta, kunhan saadaan tehtyä:
        Candidate aku = new Candidate(331, "Uusimaa", "Aku", 34, "KOK");
        Candidate lasse = new Candidate(124, "Uusimaa", "Lasse", 49, "VAS");
        Candidate heli = new Candidate(127, "Uusimaa", "Heli", 25, "VIHR");
        aku.addAllAnswers(2,1,2,3,1);
        lasse.addAllAnswers(3,5,4,5,5);
        heli.addAllAnswers(1,1,1,1,1);
        
        this.candidates.add(aku);
        this.candidates.add(lasse);
        this.candidates.add(heli);
        
        System.out.println("\nTERVETULOA VAALIKONEESEEN!\n");
        System.out.println("Vastaa seuraaviin väitteisiin");
        readAndCompare();
        System.out.println("\nSiirrytään tuloksiin");
    }

    public void readAndCompare() {     
	for(int i=0; i<=4; i++) {
            System.out.println();
            System.out.println(claims[i]);  
            System.out.println("(1=täysin eri mieltä, 2=osittain eri mieltä");
            System.out.println("3=en osaa sanoa, 4=osittain samaa mieltä");
            System.out.println("5=täysin samaa mieltä, x=lopeta)");
            String answer = scanner.nextLine();
            int number = 0;
            if(answer.equals("x")) {
                //System.out.println("");
                break;
            } else {
                number = Integer.valueOf(answer);
            }
            compareToCandidates(i, number);
            c++;
	}
    }
	
    public void compareToCandidates(int qNumber, int userAnswer) {
	// for(int i=1; i<=3; i++) {
	for(Candidate x : this.candidates) {
            int candAnswer = x.getAnswers().get(qNumber);
            int diff = Math.abs(userAnswer - candAnswer);
            int sum = x.getSum();  //aluksi 0
            sum += 100 - diff*25;
            //System.out.println("Summa ennen jakolaskua: "+sum);
            
            sum = sum/c;
            x.setMatchPercentage(sum);
            //System.out.println("Summa jakolaskun jälkeen: "+sum);
            x.addToSum(sum); 
	} 
	Collections.sort(this.candidates);
        System.out.println("\n* * * * * * * * * *");
        for(Candidate x: this.candidates) {
            System.out.println(x.toString());
        }
    }

        
    
}
