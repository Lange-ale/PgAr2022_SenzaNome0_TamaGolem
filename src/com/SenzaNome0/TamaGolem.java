package com.SenzaNome0;

import java.util.ArrayList;

public class TamaGolem {
    private int V;
    ArrayList<Integer> pietre;

    public TamaGolem(int V, ArrayList<Integer> pietre) {
        this.V = V;
        this.pietre = pietre;
    }

    public int getV() {
        return V;
    }
    public void setV(int V) {
        this.V = V;
    }
    public boolean isMorto (){
        if (this.V <=0)
            return true;
        return false;
    }

    public int getPietra(int index) {
        return pietre.get(index);
    }
}
