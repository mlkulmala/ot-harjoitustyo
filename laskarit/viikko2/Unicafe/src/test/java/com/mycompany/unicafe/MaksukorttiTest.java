package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void rahanLatausKasvattaaSaldoaOikein(){
        kortti.lataaRahaa(10000);
        assertEquals("saldo: 110.0", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikeinJosRahaaTarpeeksi(){
        kortti.otaRahaa(800);
        assertEquals("saldo: 2.0", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosEiRahaaTarpeeksi(){
        kortti.otaRahaa(1200);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void metodiPalauttaaTrueJosRahaaTarpeeksi(){
        assertEquals(true, kortti.otaRahaa(800));
    }
    
    @Test
    public void metodiPalauttaaFalseJosRahaaLiianVahan(){
        assertEquals(false, kortti.otaRahaa(1500));
    }
    
    
}
