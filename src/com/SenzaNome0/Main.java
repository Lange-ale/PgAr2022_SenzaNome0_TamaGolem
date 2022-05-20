package com.SenzaNome0;

public class Main {
    private static final int MAXVITATAMAGOLEM = 10;
    public static void main(String[] args) {
        String[] elementi = new String[]{"acqua", "fuoco", "aria", "terra", "elettricita'"};
        Scontro scontro = new Scontro(elementi, MAXVITATAMAGOLEM);
        System.out.println(scontro.getEquilibrio());
    }
}
