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

    public Scontro(String[] elementi, int V) {
        this.elementi = elementi;
        this.N = elementi.length;
        P = ((N + 1) / 3) + 1;
        int G = (N - 1) * (N - 2) / (2 * P); //numero di TamaGolem
        S = 2*G*P + N-(2*G*P)%N;
        this.V = V;
        equilibrio = new Equilibrio(elementi);
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
}
