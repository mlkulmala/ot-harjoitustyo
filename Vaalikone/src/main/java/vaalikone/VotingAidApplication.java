/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaalikone;

import java.util.*;

/**
 *
 * @author mlkul
 */
public class VotingAidApplication {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UI ui = new UI(scanner);
        ui.start();
    }
    
}
