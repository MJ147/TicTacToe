package com.mj147;

import java.util.Scanner;

public class OX {

    private int[][] table;
    private int gameMode;
    private int whoStart;

    public static void main(String[] args) {
        OX ox = new OX();
        ox.fillTable();
    }

    public void fillTable() {
        table = new int[3][3];
        gameMode = 3;
//               = 0 (Player vs. Player)
//               = 1 (Player vs. Computer level easy)
//               = 2 (Player vs. Computer level medium)
//               = 3 (Player vs. Computer level hard)
        whoStart = 2;
//               = 1 (Player 1);
//               = 2 (Player 2/ Computer)
        int x = 0;
        int y = 0;
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = 0;
                System.out.print(table[j][i] + "  ");
            }
            System.out.println("");
        }

        while ( k < table.length*table[1].length ){
            k = whichTurn();
            int XorO = (k + 2 - whoStart)% 2 + 1;
            String xy;
            System.out.println("Player " + XorO + " turn:");
            if (XorO == 2) {
                AI ai = new AI(table, x, y, k);
                switch (gameMode) {
                    case 1: xy = ai.easy(); break;
                    case 2: xy = ai.medium(); break;
                    case 3: xy = ai.hard(); break;
                    default: xy = ai.easy();
                }
            } else {
                xy = input(XorO);
            }
            x = Character.getNumericValue(xy.charAt(0));
            y = Character.getNumericValue(xy.charAt(1));
            try {
                if (table[x][y] == 0) {
                    table[x][y] = XorO ;
                } else {
                    printTable();
                    System.out.println("Wprowadź poprawną wartość");
                    k--;
                    continue;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                printTable();
                System.out.println("Wprowadź poprawną wartość");
                k--;
                continue;
            }
            printTable();
            WinCheck wc = new WinCheck(x, y, table);
            if (wc.winCheck()) {
                break;
            }
            if (k == table.length*table[1].length) {
                System.out.println("<<<||| REMIS |||>>>");
            }
        }
    }

    public int whichTurn() {
        int whichTurn = 1;
        for ( int i = 0; i < table.length; i++){
            for ( int j = 0; j < table[0].length; j++){
                if (table[i][j] != 0) {
                    whichTurn++;
                }
            }
        }
        System.out.println(whichTurn);
        return whichTurn;
    }

    public void printTable() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(table[j][i] + "  ");
            }
            System.out.println("");
        }
    }

    public String input(int XorO) {
        String input;
        Scanner scanner = new Scanner(System.in);
        do {
            input = scanner.nextLine();
            if (input.length() != 2) {
                printTable();
                System.out.println("Wprowadź poprawną wartość");
                System.out.println("Player " + XorO + " turn:");
            }
        } while (input.length() != 2);
        return input;
    }

}
