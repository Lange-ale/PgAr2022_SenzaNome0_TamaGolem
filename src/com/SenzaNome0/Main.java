package com.SenzaNome0;

public class Main {
    private static int MAXVITATAMAGOLEM;
    private static final String[] elementi = new String[]{"acqua", "fuoco", "aria", "terra", "elettricita'"};

    private static void benvenutoUtente() {
        System.out.println("Benvenuti nel fantastico gioco di TamaGolemGO!");

        System.out.println("Vuoi cominciare una nuova partita? ");
        String[] opzioni = new String[]{"si", "no"};
        if (Console.stringInput(opzioni).equals("si")) {

        } else {
            Console.chiudiProgramma();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        benvenutoUtente();

        Equilibrio equilibrio = new Equilibrio(elementi);

        for (int i = 0; i < elementi.length; i++)
            for (int j = 0; j < elementi.length; j++)
                if(MAXVITATAMAGOLEM<equilibrio.getPotenza(i,j))
                    MAXVITATAMAGOLEM=equilibrio.getPotenza(i,j);

        Scontro scontro = new Scontro(MAXVITATAMAGOLEM, equilibrio);
        boolean iniziaGiocatore1 = Math.random()*2>1;
        //TODO sostituire con l'input delle pietre ai null
        if (iniziaGiocatore1){
            scontro.getGiocatore1().setTamaGolem(new TamaGolem(MAXVITATAMAGOLEM, null));
            scontro.getGiocatore2().setTamaGolem(new TamaGolem(MAXVITATAMAGOLEM, null));
        } else {
            scontro.getGiocatore2().setTamaGolem(new TamaGolem(MAXVITATAMAGOLEM, null));
            scontro.getGiocatore1().setTamaGolem(new TamaGolem(MAXVITATAMAGOLEM, null));
        }

        while (scontro.getGiocatore1().getG()>0 && scontro.getGiocatore2().getG()>0) {

        }
    }
}
