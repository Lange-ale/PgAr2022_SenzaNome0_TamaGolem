package com.SenzaNome0;

import java.util.ArrayList;
import java.util.Map;

public class Main {
    private static int MAXVITATAMAGOLEM;
    private static final String[] elementi = new String[]{"acqua", "fuoco", "aria", "terra", "elettricita'"};

    private static void benvenutoUtente() {
        System.out.println("Benvenuti nel fantastico gioco di TamaGolemGO!");

        System.out.println("Vuoi cominciare una nuova partita? (si/no)");
        String[] opzioni = new String[]{"si", "no"};
        if (Console.stringInput(opzioni).equals("si")) {
            // TODO: Start the actual game which is currently done in the main method
            return;
        } else {
            Console.chiudiProgramma();
            System.exit(0);
        }
    }

    private static int richiediPietra(boolean iniziaGiocatore1, Scontro scontro) {
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

    private static void faseDiSelezionePietre(boolean iniziaGiocatore1, Scontro scontro) {
        ArrayList<Integer> pietrePrimoGiocatore = new ArrayList<Integer>();
        ArrayList<Integer> pietreSecondoGiocatore = new ArrayList<Integer>();

        if (iniziaGiocatore1) {
            Console.stampaSuccesso("Comincia il GIOCATORE 1 a selezionare le pietre del nuovo Tamagolem!\n");

            for (int i = 0; i < scontro.getP(); i++) {
                int pietraScelta = richiediPietra(true, scontro);
                pietrePrimoGiocatore.add(pietraScelta);
                Console.stampaSuccesso("Ottima scelta!\n");
            }

            Console.stampaSuccesso("\nOra tocca al GIOCATORE 2!\n");

            for (int i = 0; i < scontro.getP(); i++) {
                int pietraScelta = richiediPietra(false, scontro);
                pietreSecondoGiocatore.add(pietraScelta);
                Console.stampaSuccesso("Ottima scelta!\n");
            }
            System.out.println();

            scontro.getGiocatore1().setTamaGolem(new TamaGolem(MAXVITATAMAGOLEM, pietrePrimoGiocatore));
            scontro.getGiocatore2().setTamaGolem(new TamaGolem(MAXVITATAMAGOLEM, pietreSecondoGiocatore));
        } else {
            Console.stampaSuccesso("Comincia il GIOCATORE 2 a selezionare le pietre del nuovo Tamagolem!\n");

            for (int i = 0; i < scontro.getP(); i++) {
                int pietraScelta = richiediPietra(false, scontro);
                pietreSecondoGiocatore.add(pietraScelta);
                Console.stampaSuccesso("Ottima scelta!\n");
            }

            Console.stampaSuccesso("\nOra tocca al GIOCATORE 1!\n");

            for (int i = 0; i < scontro.getP(); i++) {
                int pietraScelta = richiediPietra(true, scontro);
                pietrePrimoGiocatore.add(pietraScelta);
                Console.stampaSuccesso("Ottima scelta!\n");
            }
            System.out.println();

            scontro.getGiocatore1().setTamaGolem(new TamaGolem(MAXVITATAMAGOLEM, pietrePrimoGiocatore));
            scontro.getGiocatore2().setTamaGolem(new TamaGolem(MAXVITATAMAGOLEM, pietreSecondoGiocatore));
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

        faseDiSelezionePietre(iniziaGiocatore1, scontro);

        while (scontro.getVincitore()==0) {
            scontro.turno();

        }
    }
}
