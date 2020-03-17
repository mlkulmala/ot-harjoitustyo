/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mlkul
 */
public class Maksukortti {

    private int saldo;

    public Maksukortti(int saldo) {
        this.saldo = saldo;
    }

    public int saldo() {
        return saldo;
    }

    public void lataaRahaa(int lisays) {
        this.saldo += lisays;
    }

    public boolean otaRahaa(int maara) {
        if (this.saldo < maara)
            return false;

        this.saldo = this.saldo - maara;
        return true;
    }

    @Override
    public String toString() {
        int euroa = saldo/100;
        int senttia = saldo%100;
        return "saldo: "+euroa+"."+senttia;
    }
}