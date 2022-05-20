package com.SenzaNome0;

import java.util.Arrays;

public class Equilibrio {
    private final int N;
    private final int[][] equilibrio;
    private final String[] elementi;
    public Equilibrio(String[] elementi) {
        this.N = elementi.length;
        equilibrio = new int[N][N];
        this.elementi = elementi;

        creaEquilibrio();
    }

    private void creaEquilibrio(){
        int somma, temp, randomConSommaA0;
        for (int i = 0; i < N-1; i++) {
            somma = 0;
            for (int j = 0; j < i; j++)
                somma += equilibrio[i][j];
            equilibrio[i][i] = 0;
            for (int j = i + 1; j < N - 1; j++) {
                randomConSommaA0 = -1;
                if(somma == 0)
                    randomConSommaA0 = (int)(Math.random()*2);
                temp = (int) (Math.random() * 4) + 1;
                if (somma > 0 || randomConSommaA0==0) {
                    equilibrio[i][j] = -temp;
                    equilibrio[j][i] = temp;
                    somma -= temp;
                } else if(somma < 0 || randomConSommaA0==1) {
                    equilibrio[i][j] = temp;
                    equilibrio[j][i] = -temp;
                    somma += temp;
                }
            }
            equilibrio[i][N - 1] = -somma;
            equilibrio[N - 1][i] = somma;

            if(equilibrio[i][N - 1]==0) {
                if (i == N - 2){
                    //regole non soddisfatte, quindi tutta la matrice
                    //da rifare (la probabilita' che accada e' molto bassa)
                    creaEquilibrio();
                    return;
                }
                else i--; //combinazione non valida
            }
        }
        equilibrio[N - 1][N - 1] = 0;
    }

    public int getN() {
        return N;
    }

    public int getPotenza(int el1, int el2) {
        return equilibrio[el1][el2];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("VINCITORE -> POTENZA -> PERDENTE\n");
        for (int i = 0; i < N - 1; i++)
            for (int j = i + 1; j < N; j++)
                if (equilibrio[i][j]>0)
                    s.append(elementi[i]).append(" -> ").append(equilibrio[i][j]).append(" -> ").append(elementi[j]).append("\n");
                else
                    s.append(elementi[j]).append(" -> ").append(equilibrio[j][i]).append(" -> ").append(elementi[i]).append("\n");

        return s.toString();
    }

    public String debugEquilibrio() {
        StringBuilder s = new StringBuilder();
        for (int[] i: equilibrio)
            s.append(Arrays.toString(i)).append("\n");

        return s.toString();
    }
}
