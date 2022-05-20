package com.SenzaNome0;


public class Giocatore {
    private int G; //numero di TamaGolem rimanenti
    private TamaGolem tamaGolem;

    public Giocatore(int G) {
        this.G = G;
    }

    public int getG() {
        return G;
    }

    public void setG(int G) {
        this.G = G;
    }
}
