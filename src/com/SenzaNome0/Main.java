package com.SenzaNome0;

public class Main {
    private static int MAXVITATAMAGOLEM;
    private static final String[] elementi = new String[]{"acqua", "fuoco", "aria", "terra", "elettricita'"};
    public static void main(String[] args) {
        Equilibrio equilibrio = new Equilibrio(elementi);

        //MAXVITATAMAGOLEM = val massimo nell'equilibrio
        for (int i = 0; i < elementi.length; i++) {
            for (int j = 0; j < elementi.length; j++) {
                if(MAXVITATAMAGOLEM<equilibrio.getPotenza(i,j))
                    MAXVITATAMAGOLEM=equilibrio.getPotenza(i,j);
            }
        }


        Scontro scontro = new Scontro(MAXVITATAMAGOLEM, equilibrio);
        while (scontro.getGiocatore1().getG()>0 && scontro.getGiocatore2().getG()>0){
            //scontro
        }
    }
}
