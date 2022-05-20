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
        /*
        * Premessa: La matrice equilibrio[][] che rappresenta il nostro grafo è una matrice simmetrica opposta,
        * quindi per ogni valore v = equilibrio[i][j] corrisponderà un valore w = equilibrio[j][i] per cui v = -w
        *
        * Complessità computazionale dell'algoritmo:
        * O(N^2)
        * S(N^2)
        *
        * La variabile somma ci servirà nel tempo per calcolare la somma di tutti valori in una riga della matrice equilibrio[][]
        * La variabile temp ci servirà per
        * La variabile randomConSommaA0 ci servirà per
        */
        int somma, valoreRandomAssoluto, randomConSommaA0;

        // Per ogni riga della matrice a esclusione dell'ultima
        for (int i = 0; i < N-1; i++) {

            // Resettiamo la somma a zero
            somma = 0;

            // Ricalcoliamo la somma per ogni elemento della riga fino all'elemento precedente il nodo corrente
            for (int j = 0; j < i; j++)
                somma += equilibrio[i][j];

            // Settiamo la potenza del nodo corrente a zero in quanto non vi è nessuna differenza di potenza tra elementi uguali.
            // Ad esempio se si mettesse a confronto una pietra Terra con un'altra pietra Terra non vi sarebbe vincitore (Ma un possibile loop...).
            equilibrio[i][i] = 0;

            // Per ogni elemento nella riga successivi al nodo corrente
            for (int j = i + 1; j < N - 1; j++) {

                // Resettiamo il random a -1
                randomConSommaA0 = -1;

                // Se la somma è 0, e quindi i numeri random sono ancora da estrarre,
                // tiriamo fuori un valore random o zero o uno e lo mettiamo nella variabile randomConSommaA0.
                // Questa variabile rappresenterà la condizione per la quale il primo elemento random da inserire è positivo o negativo.
                if(somma == 0) randomConSommaA0 = (int)(Math.random()*2);

                // Calcoliamo ora il valore da inserire assoluto compreso tra 1 e 100 (o numero a nostra scelta) che dovrà
                // essere inserito nelle posizioni [i][j] e [j][i] correnti con la direzione derivata dalla condizione successiva...
                valoreRandomAssoluto = (int) (Math.random() * 100) + 1;

                // Se la somma è maggiore di zero, e quindi il nodo è sbilanciato per i bordi in uscita,
                // oppure randomConSomma è uguale a zero (esso non è sempre stabilito e da questo deriva il fatto di settarlo a -1)
                if (somma > 0 || randomConSommaA0 == 0) {
                    // La freccia è in entrata in questo nodo dal nodo in colonna j ed ha valore opposto a valoreRandomAssoluto
                    // Il meno davanti sta a significare che la freccia è in ingresso verso il nodo corrente...
                    equilibrio[i][j] = -valoreRandomAssoluto;

                    // D'altro canto il nodo simmetrico è in questo caso l'elemento forte
                    equilibrio[j][i] = valoreRandomAssoluto;

                    // Infine si ricalcola la somma col negativo del valore random assoluto
                    somma -= valoreRandomAssoluto;
                } else if(somma < 0 || randomConSommaA0 == 1) { // nodo sbilanciato per valori in ingresso o randomConSommaA0 è capitato come 1
                    // La freccia è in uscita da questo nodo al nodo in colonna j ed ha valore pari a valoreRandomAssoluto
                    equilibrio[i][j] = valoreRandomAssoluto;

                    // D'altro canto il nodo simmetrico è in questo caso l'elemento debole (da questo deriva il meno davanti...)
                    equilibrio[j][i] = -valoreRandomAssoluto;

                    // Infine si ricalcola la somma col positivo del valore random assoluto
                    somma += valoreRandomAssoluto;
                }
            }

            // Infine per bilanciare il tutto settiamo l'ultimo elemento della nostra riga al negativo di somma
            equilibrio[i][N - 1] = -somma;
            // E settiamo l'elemento corrisposto a somma senza cambio di segno
            equilibrio[N - 1][i] = somma;

            // Infine controlliamo che l'ultimo elemento della riga sia zero, se lo è, allora ci possono essere vari problemi
            // in quanto in una singola riga deve esserci un solo elemento con valore zero, il nodo stesso (posizione [i][i])
            if(equilibrio[i][N - 1] == 0) {
                // Se ci troviamo alla penultima riga le regole non sono soddisfatte
                if (i == N - 2){
                    // Dobbiamo rifare tutta la matrice... La probabilità che questo accada è molto bassa
                    creaEquilibrio();
                    return;
                } else i--; // Combinazione non valida, da ricalcolare l'intera riga
            }
        }

        // L'ultima cella in basso a destra della matrice è settata a zero in quanto è il nodo stesso dell'ultima riga
        equilibrio[N - 1][N - 1] = 0;
    }

    public int getN() { return N; }

    public int getPotenza(int el1, int el2) { return equilibrio[el1][el2]; }

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

    public String[] getElementi() { return elementi; }
}
