package com.SenzaNome0;

import java.util.ArrayList;
import java.util.Map;

public class Main {
    private static int MAXVITATAMAGOLEM;
    private static final String[] elementi = new String[]{"acqua", "fuoco", "aria", "terra", "elettricita'"};

    private static void benvenutoUtente() {
        System.out.println("Benvenuti nel fantastico gioco di TamaGolemGO!");

        // TODO: Ricomincia la partita (crea macrociclo o callback hell)
        System.out.println("Vuoi cominciare una nuova partita? (si/no)");
        String[] opzioni = new String[]{"si", "no"};
        if (Console.stringInput(opzioni).equals("si")) {} else {
            Console.chiudiProgramma();
            System.exit(0);
        }
    }

    private static int inputPietra(boolean iniziaGiocatore1, Scontro scontro) {
        System.out.println("Pietre disponibili (nome - quantità): ");

        ArrayList<String> opzioni = new ArrayList<>();

        int i = 0;
        for (Map.Entry<String, Integer> pietra : scontro.getPietre().entrySet()) {
            if (pietra.getValue() != 0) {
                opzioni.add(pietra.getKey());
                System.out.println(pietra.getKey() + " - " + pietra.getValue());
            }
            i++;
        }
        System.out.println();

        System.out.println("GIOCATORE " + ((iniziaGiocatore1) ? "1" : "2") + " scegli una tra queste pietre (usa il nome come stampato): ");
        String[] opzioniArray = new String[opzioni.size()];
        String nomePietraScelta = Console.stringInput(opzioni.toArray(opzioniArray));

        scontro.getPietre().replace(nomePietraScelta, scontro.getPietre().get(nomePietraScelta)-1); // decrementa numero pietra scelta

        String[] elementi = scontro.getElementi();
        for (i = 0; i < elementi.length; i++)
            if (elementi[i].equals(nomePietraScelta)) return i;

        return -1; // dovrebbe essere impossibile che l'utente scelga un input non valido...
    }

    private static void selezionaPietre(boolean giocatore1, Scontro scontro) {
        ArrayList<Integer> pietreScelte = new ArrayList<Integer>();

        Console.stampaSuccesso("GIOCATORE " + ((giocatore1) ? "1" : "2") +  " devi scegliere le pietre per il tuo nuovo Tamagolem!");

        for (int i = 0; i < scontro.getP(); i++) {
            int pietraScelta = inputPietra(giocatore1, scontro);
            pietreScelte.add(pietraScelta);
            Console.stampaSuccesso("Ottima scelta!\n");
        }

        if (giocatore1)
            scontro.getGiocatore1().setTamaGolem(new TamaGolem(MAXVITATAMAGOLEM, pietreScelte));
        else
            scontro.getGiocatore2().setTamaGolem(new TamaGolem(MAXVITATAMAGOLEM, pietreScelte));
    }

    private static void faseDiSelezionePietreIniziale(boolean iniziaGiocatore1, Scontro scontro) {
        ArrayList<Integer> pietrePrimoGiocatore = new ArrayList<Integer>();
        ArrayList<Integer> pietreSecondoGiocatore = new ArrayList<Integer>();

        if (iniziaGiocatore1) {
            Console.stampaSuccesso("Comincia il GIOCATORE 1 a selezionare le pietre del nuovo Tamagolem!\n");

            selezionaPietre(true, scontro);

            Console.stampaSuccesso("\nOra tocca al GIOCATORE 2!\n");

            selezionaPietre(false, scontro);
        } else {
            Console.stampaSuccesso("Comincia il GIOCATORE 2 a selezionare le pietre del nuovo Tamagolem!\n");

            selezionaPietre(false, scontro);

            Console.stampaSuccesso("\nOra tocca al GIOCATORE 1!\n");

            selezionaPietre(true, scontro);
        }

        Console.stampaSuccesso("\nComplimenti per entrambi in questa fase di scelta, ora a turno vi sfiderete con i vostri tamagolem!\n");
    }

    public static void main(String[] args) {
        benvenutoUtente();

        Equilibrio equilibrio = new Equilibrio(elementi);

        System.out.println();

        for (int i = 0; i < elementi.length; i++)
            for (int j = 0; j < elementi.length; j++)
                if(MAXVITATAMAGOLEM<equilibrio.getPotenza(i,j))
                    MAXVITATAMAGOLEM=equilibrio.getPotenza(i,j);

        Scontro scontro = new Scontro(MAXVITATAMAGOLEM, equilibrio);
        boolean iniziaGiocatore1 = Math.random()*2>1;

        faseDiSelezionePietreIniziale(iniziaGiocatore1, scontro);

        System.out.println("1° scontro della partita!");
        System.out.println("Stato del gioco a inizio partita: ");
        System.out.println(scontro);

        int turnoCounter = 1;
        int scontroCounter = 1;
        while (scontro.getVincitore() == 0) {
            scontro.turno();
            System.out.println("\nStato del gioco dopo il " + turnoCounter + "° turno: ");
            System.out.println(scontro);

            if(scontro.getGiocatore1().getTamaGolem().isMorto()){
                System.out.println(Console.ANSI_RED + "\nIl golem del GIOCATORE 1 è morto :(\n" + Console.ANSI_RESET);
                turnoCounter = 0;
                scontroCounter++;

                scontro.getGiocatore1().setG(scontro.getGiocatore1().getG()-1);
                if(scontro.getGiocatore1().getG()>0)
                    selezionaPietre(true, scontro);

                System.out.println("\n\n" + scontroCounter + "° scontro della partita!\n");
            }
            else if(scontro.getGiocatore2().getTamaGolem().isMorto()){
                System.out.println(Console.ANSI_RED + "\nIl golem del GIOCATORE 2 è morto :(\n" + Console.ANSI_RESET);
                turnoCounter = 0;
                scontroCounter++;

                scontro.getGiocatore2().setG(scontro.getGiocatore1().getG()-1);
                if(scontro.getGiocatore2().getG()>0)
                    selezionaPietre(false, scontro);

                System.out.println("\n\n" + scontroCounter + "° scontro della partita!\n");
            }

            turnoCounter++;
        }

        Console.stampaSuccesso("IL VINCITORE E' GIOCATORE" + scontro.getVincitore() + " !!!\n");
        System.out.println(equilibrio);
    }
}
