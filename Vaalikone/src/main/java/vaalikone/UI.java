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
    private CandidateLogic candidatelogic;
    private Scanner scanner;
    private String[] claims;
    private int c;

    public UI(Scanner scanner) {
        CandidateMemoryDao candMemoryDao = new CandidateMemoryDao();
        this.candidatelogic = new CandidateLogic(candMemoryDao);
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
                break;
            } else {
                number = Integer.valueOf(answer);
            }
            candidatelogic.compareToCandidates(c, i, number);
            c++;
	}
    }
	

        
    
}
