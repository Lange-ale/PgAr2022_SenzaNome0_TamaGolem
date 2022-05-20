package com.SenzaNome0;

import java.util.HashMap;
import java.util.Map;

public class Scontro {
    private final String[] elementi;
    private final int N; //numero elementi
    private final int P; //numero pietre per TamaGolem
    private final int S; //scorta comune pietre
    private final int V; //vita massima tamagolem
    private final Equilibrio equilibrio;
    private final Map<String, Integer> pietre;
    private final Giocatore giocatore1, giocatore2;

    private int indexPietraAttuale = 0; //pietra
    public void turno(){
        int potenza = equilibrio.getPotenza(giocatore1.getTamaGolem().getPietra(indexPietraAttuale),
                giocatore2.getTamaGolem().getPietra(indexPietraAttuale));
        if (potenza > 0)
            giocatore2.getTamaGolem().subisciDanno(potenza);
        else
            giocatore1.getTamaGolem().subisciDanno(-potenza);
        indexPietraAttuale = (indexPietraAttuale+1)%P;
    }


    public Scontro(int V, Equilibrio equilibrio) {
        this.V = V;
        this.equilibrio = equilibrio;
        this.elementi = equilibrio.getElementi();
        this.N = elementi.length;
        P = ((N + 1) / 3) + 1;
        int G = (N - 1) * (N - 2) / (2 * P); //numero di TamaGolem
        S = 2*G*P + N-(2*G*P)%N;
        pietre = new HashMap<>();
        int pietrePerElemento = S / N;
        for (String el : elementi)
            pietre.put(el, pietrePerElemento);
        giocatore1 = new Giocatore(G);
        giocatore2 = new Giocatore(G);
    }

    public Equilibrio getEquilibrio() {
        return equilibrio;
    }

    public Giocatore getGiocatore1() {
        return giocatore1;
    }

    public Giocatore getGiocatore2() {
        return giocatore2;
    }

    int getVincitore(){
        if (giocatore2.getG()<=0) return 1;
        if (giocatore1.getG()<=0) return 2;
        return 0;
    }

    public String[] getElementi() {
        return elementi;
    }

    public int getP() {
        return P;
    }

    public Map<String, Integer> getPietre() {
        return pietre;
    }
}
