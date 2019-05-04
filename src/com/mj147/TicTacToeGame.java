package com.mj147;

import com.mj147.computer.ComputerEasy;
import com.mj147.computer.ComputerHard;
import com.mj147.computer.ComputerMedium;
import com.mj147.computer.ComputerOpponent;

import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;

public class TicTacToeGame {

    private int[][] table;
    private int gameMode;
    private int whoStart;
    String abc = "abc";

    public void fillTable() {
        table = new int[3][3];
        gameMode = 3;
//               = 0 (Player vs. Player)
//               = 1 (Player vs. Computer level randomMove)
//               = 2 (Player vs. Computer level medium)
//               = 3 (Player vs. Computer level hard)
        whoStart = 2;
//               = 1 (Player 1);
//               = 2 (Player 2/ Computer)
        int x = 0;
        int y = 0;
        int k = 0;

        System.out.println("--- Tic Tac Toe ---");
        System.out.println("Choose fields from a0 to c2. Arrange three symbols in line to win.");
        System.out.println();


        while ( k < table.length*table[1].length ){
            k = whichTurn();
            int XorO = (k + 2 - whoStart)% 2 + 1;
            String xy;

                System.out.print("Player " + XorO + " turn: ");

            if (XorO == 2) {
                ComputerOpponent computerOpponent;
                switch (gameMode) {
                    case 2:
                        computerOpponent = new ComputerMedium(table, x, y, k);
                        xy = computerOpponent.play();
                        break;
                    case 3:
                        computerOpponent = new ComputerHard(table, x, y, k);
                        xy = computerOpponent.play();
                        break;
                    default:
                        computerOpponent = new ComputerEasy(table, x, y);
                        xy = computerOpponent.play();
                        break;
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
                    System.out.println("Wprowadź poprawną wartość");
                    k--;
                    continue;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wprowadź poprawną wartość");
                k--;
                continue;
            }

            if (XorO == 2) {
                System.out.println(String.valueOf(abc.charAt(y)) + x);
            }
            System.out.println(printTable(table));

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
//        System.out.println(whichTurn); //  info for developer
        return whichTurn;
    }

    public String input(int XorO) {

        String input;
        Scanner scanner = new Scanner(System.in);
        String charToNumber;
        char x,y;

        do {
            input = scanner.nextLine();

            if (input.length() != 2) {
                System.out.println(printTable(table));
                System.out.println("Wprowadź poprawną wartość");
                System.out.println("Player " + XorO + " turn:");
            }

        } while (input.length() != 2);

        x = input.charAt(0);
        y = input.charAt(1);
        charToNumber = String.valueOf(abc.indexOf(x));
        return y + charToNumber;
    }

    public String printTable(int[][] tableToConvert) {

        String convertedTable = Arrays.deepToString(tableToConvert).replace("], ", "\n")
                                                                   .replace(",", " ")
                                                                   .replace("[", "")
                                                                   .replace("]", "")
                                                                   .replace("0", "-")
                                                                   .replace("1", "O")
                                                                   .replace("2", "X")
                                                                    + "\n";



        return convertedTable;
    }

}
