/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mlkul
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    } 
    
    @Test
    public void kassassaOikeaAlkusaldo() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void alussaMaukkaidenMyyntiNolla() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void alussaEdullistenMyyntiNolla() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    
    //testit edullisten lounaiden käteisostoille
    @Test
    public void edullinenKasvattaaKassaaOikein() {
        kassa.syoEdullisesti(500);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullinenPalauttaaVaihtorahanOikein() {
        assertEquals(260, kassa.syoEdullisesti(500));
    }
    
    @Test
    public void edullinenKasvattaaMyytyjenMaaraa() {
        kassa.syoEdullisesti(240);
        kassa.syoEdullisesti(240);
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassaEiMuutuJosMaksuLiianPieni() {
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void rahatTakaisinJosMaksuLiianPieni() {
        assertEquals(200, kassa.syoEdullisesti(200));
    }
     
    @Test
    public void edullistenMaaraEiMuutuJosMaksuLiianPieni() {
        kassa.syoEdullisesti(300);
        kassa.syoEdullisesti(100);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
  
    
    //testit maukkaiden lounaiden käteisostoille
    @Test
    public void maukasKasvattaaKassaaOikein() {
        kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukasPalauttaaVaihtorahanOikein() {
        assertEquals(100, kassa.syoMaukkaasti(500));
    }
    
    @Test
    public void maukasKasvattaaMyytyjenMaaraa() {
        kassa.syoMaukkaasti(400);
        kassa.syoMaukkaasti(400);
        kassa.syoMaukkaasti(400);
        assertEquals(3, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaEiMuutuJosMaksuEiRiitaMaukkaaseen() {
        kassa.syoMaukkaasti(300);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void rahatTakaisinJosMaksuEiRiitaMaukkaaseen() {
        assertEquals(300, kassa.syoMaukkaasti(300));
    }
     
    @Test
    public void maukkaidenMaaraEiMuutuJosMaksuLiianPieni() {
        kassa.syoMaukkaasti(400);
        kassa.syoMaukkaasti(400);
        kassa.syoMaukkaasti(200);
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());
    }
    
    
    //testit edullisten lounaiden korttiostoille
    @Test
    public void edullinenVeloittaaKortiltaOikein() {
        kassa.syoEdullisesti(kortti);
        assertEquals("saldo: 7.60", kortti.toString());
    }
    
    @Test
    public void edullinenPalauttaaTrueJosKortillaRahaa() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullistenMaaraKasvaaJosKortillaRahaa() {
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kortinSaldoEiMuutuJosEiRahaaEdulliseen() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals("saldo: 2.0", kortti.toString()); 
    }
    
    @Test
    public void edullistenMaaraEiKasvaJosKortillaEiRahaa() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty()); 
    }
    
    @Test
    public void edullinenPalauttaaFalseJosKortillaEiRahaa() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(false, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void kassanSaldoEiMuutuKunKortillaMaksetaanEdullinen() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    //testit maukkaiden lounaiden korttiostoille
    @Test
    public void maukasVeloittaaKortiltaOikein() {
        kassa.syoMaukkaasti(kortti);
        assertEquals("saldo: 6.0", kortti.toString());
    }
    
    @Test
    public void maukasPalauttaaTrueJosKortillaRahaa() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void maukkaidenMaaraKasvaaJosKortillaRahaa() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortinSaldoEiMuutuJosEiRahaaMaukkaaseen() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals("saldo: 2.0", kortti.toString()); 
    }
    
    @Test
    public void maukkaidenMaaraEiKasvaJosKortillaEiRahaa() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty()); 
    }
    
    @Test
    public void maukasPalauttaaFalseJosKortillaEiRahaa() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kassanSaldoEiMuutuKunKortillaMaksetaanMaukas() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    //kortin lataukseen liittyvät testit
    @Test
    public void kortinSaldoMuuttuuLadattaessa() {
        kassa.lataaRahaaKortille(kortti, 2500);
        assertEquals("saldo: 35.0", kortti.toString());
    }
    
    @Test
    public void kassanSaldoMuuttuuKorttiaLadattaessa() {
        kassa.lataaRahaaKortille(kortti, 2500);
        assertEquals(102500, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortinSaldoEiMuutuJosLatauksenArvoNegatiivinen() {
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals("saldo: 10.0", kortti.toString());
    }
}
