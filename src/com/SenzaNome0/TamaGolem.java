package com.SenzaNome0;

import java.util.ArrayList;

public class TamaGolem {
    private int V;
    ArrayList<String> pietre;

    public TamaGolem(int V, ArrayList<String> pietre) {
        this.V = V;
        this.pietre = pietre;
    }

    public int getV() {
        return V;
    }
    public void setV(int V) {
        this.V = V;
    }

    public String getPietra(int index) {
        return pietre.get(index);
    }
}
