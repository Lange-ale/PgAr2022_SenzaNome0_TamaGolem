package com.SenzaNome0;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    private static int MAXVITATAMAGOLEM;
    private static final String[] elementi = new String[]{"acqua", "fuoco", "aria", "terra", "elettricita'"};

    public static void main(String[] args) {
        System.out.println("Benvenuti nel fantastico gioco di TamaGolemGO!");
        while(benvenutoUtente()){

            System.out.println();

            Equilibrio equilibrio = new Equilibrio(elementi);

            for (int i = 0; i < elementi.length; i++)
                for (int j = 0; j < elementi.length; j++)
                    if(MAXVITATAMAGOLEM<equilibrio.getPotenza(i,j))
                        MAXVITATAMAGOLEM=equilibrio.getPotenza(i,j);

            Scontro scontro = new Scontro(MAXVITATAMAGOLEM, equilibrio);
            boolean iniziaGiocatore1 = Math.random()*2>1;

            faseDiSelezionePietreIniziale(iniziaGiocatore1, scontro);

            System.out.println(Console.ANSI_BLUE_BACKGROUND + Console.ANSI_BLACK + "1° scontro della partita!" + Console.ANSI_RESET);
            System.out.println("Stato del gioco a inizio partita: ");
            System.out.println(scontro);

            int turnoCounter = 1;
            int scontroCounter = 1;
            boolean nuovoScontro = true;
            while (scontro.getVincitore() == 0) {
                if (nuovoScontro && scontroCounter > 1) System.out.println("\n\n" + Console.ANSI_BLUE_BACKGROUND + Console.ANSI_BLACK + scontroCounter + "° scontro della partita!\n" + Console.ANSI_RESET);

                scontro.turno();

                // Controllo nel caso in cui i Tamagolem (in termini di pietre nell'ordine) scelti sono gli stessi
                if (scontro.getGiocatore1().getTamaGolem().equals(scontro.getGiocatore2().getTamaGolem())) {
                    Console.stampaErrore("C'è stato un poblema con l'esecuzione del programma...");
                    Console.chiudiProgramma();
                    //TODO: Bisogna sistemare sto problema, ad esempio facendo riscegliere i tamagolem e rimettendo le pietre nel "cesto" comune
                }

                System.out.println("\nStato del gioco dopo il " + turnoCounter + "° turno: ");
                System.out.println(scontro);

                nuovoScontro = false;

                if(scontro.getGiocatore1().getTamaGolem().isMorto()){
                    System.out.println(Console.ANSI_RED + "\nIl golem del GIOCATORE 1 è morto :(\n" + Console.ANSI_RESET);
                    turnoCounter = 0;
                    scontroCounter++;
                    nuovoScontro = true;

                    scontro.getGiocatore1().setG(scontro.getGiocatore1().getG()-1);
                    if(scontro.getGiocatore1().getG()>0)
                        selezionaPietre(true, scontro);
                }
                else if(scontro.getGiocatore2().getTamaGolem().isMorto()){
                    System.out.println(Console.ANSI_RED + "\nIl golem del GIOCATORE 2 è morto :(\n" + Console.ANSI_RESET);
                    turnoCounter = 0;
                    scontroCounter++;
                    nuovoScontro = true;

                    scontro.getGiocatore2().setG(scontro.getGiocatore1().getG()-1);
                    if(scontro.getGiocatore2().getG()>0)
                        selezionaPietre(false, scontro);
                }

                turnoCounter++;
            }

            Console.stampaSuccesso("IL VINCITORE E' GIOCATORE" + scontro.getVincitore() + " !!!\n");
            System.out.println(equilibrio);
        }
    }

    private static boolean benvenutoUtente() {
        System.out.println("Vuoi cominciare una nuova partita? (si/no)");
        String[] opzioni = new String[]{"si", "no"};
        if (Console.stringInput(opzioni).equals("si")) {} else {
            Console.chiudiProgramma();
            System.exit(0);
            return false;
        }
        return true;
    }

    private static int inputPietra(boolean iniziaGiocatore1, Scontro scontro, Set<String> giaPresi) {
        System.out.println("Pietre disponibili (nome - quantità): ");

        ArrayList<String> opzioni = new ArrayList<>();

        for (Map.Entry<String, Integer> pietra : scontro.getPietre().entrySet()) {
            if (pietra.getValue() != 0 && !giaPresi.contains(pietra.getKey()))
                opzioni.add(pietra.getKey());
        }

        if (opzioni.size() == scontro.getP()){
            boolean ultimoTamaGolemEvocabile = true;
            for (String opzione : opzioni)
                if (scontro.getPietre().get(opzione) > 1)
                    ultimoTamaGolemEvocabile = false;
            if (!ultimoTamaGolemEvocabile)
                opzioni.removeIf(s -> scontro.getPietre().get(s) == 1);
        }

        for (String s : opzioni)
            System.out.println(s + " - " + scontro.getPietre().get(s));

        System.out.println();

        if (opzioni.size() == 0) {
            Console.stampaErrore("C'è stato un poblema con l'esecuzione del programma...");
            Console.chiudiProgramma();
            //TODO: Lange Junior devi sistemare l'algoritmo in un qualche modo...
        }

        System.out.println("GIOCATORE " + ((iniziaGiocatore1) ? "1" : "2") + " scegli una tra queste pietre (usa il nome come stampato): ");
        String[] opzioniArray = new String[opzioni.size()];
        String nomePietraScelta = Console.stringInput(opzioni.toArray(opzioniArray));

        scontro.getPietre().replace(nomePietraScelta, scontro.getPietre().get(nomePietraScelta)-1); // decrementa numero pietra scelta

        String[] elementi = scontro.getElementi();
        for (int i = 0; i < elementi.length; i++)
            if (elementi[i].equals(nomePietraScelta)) {
                giaPresi.add(nomePietraScelta);
                return i;
            }

        return -1; // dovrebbe essere impossibile che l'utente scelga un input non valido...
    }

    private static void selezionaPietre(boolean giocatore1, Scontro scontro) {
        ArrayList<Integer> pietreScelte = new ArrayList<Integer>();

        Console.stampaSuccesso("GIOCATORE " + ((giocatore1) ? "1" : "2") +  " devi scegliere le pietre per il tuo nuovo Tamagolem!");

        Set<String> giaPresi = new HashSet<>();
        for (int i = 0; i < scontro.getP(); i++) {
            int pietraScelta = inputPietra(giocatore1, scontro, giaPresi);
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
}
