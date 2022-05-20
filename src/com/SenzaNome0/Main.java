package com.SenzaNome0;

public class Main {
    private static final int MAXVITATAMAGOLEM = 10;
    private static final String[] elementi = new String[]{"acqua", "fuoco", "aria", "terra", "elettricita'"};
    public static void main(String[] args) {
        Scontro scontro = new Scontro(elementi, MAXVITATAMAGOLEM);
        while (scontro.getGiocatore1().getG()>0 && scontro.getGiocatore2().getG()>0){
            //scontro
        }
    }


}
