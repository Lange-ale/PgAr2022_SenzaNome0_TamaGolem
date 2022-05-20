package com.SenzaNome0;

import java.util.ArrayList;

public class TamaGolem {
    private int V;
    ArrayList<Integer> pietre;

    public TamaGolem(int V, ArrayList<Integer> pietre) {
        this.V = V;
        this.pietre = pietre;
    }

    public void subisciDanno(int dannoSubito){
        this.V-=dannoSubito;
    }

    public int getV() {
        return V;
    }
    public void setV(int V) {
        this.V = V;
    }
    public boolean isMorto (){
        return this.V <= 0;
    }

    public int getPietra(int index) {
        return pietre.get(index);
    }

    @Override
    public String toString() {
        return "vita rimanente:" + V;
    }
}
