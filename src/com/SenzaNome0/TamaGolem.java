package com.SenzaNome0;

import java.util.ArrayList;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TamaGolem tamaGolem = (TamaGolem) o;

        if (pietre.size() != tamaGolem.pietre.size()) return false;

        int i = 0;
        for (var pietra : tamaGolem.pietre) {
            if (!Objects.equals(pietra, pietre.get(i))) return false;
            i++;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pietre);
    }
}
