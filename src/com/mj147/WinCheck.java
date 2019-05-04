package com.mj147;

public class WinCheck {

    private int x,y;
    private int table[][];

    public WinCheck(int x, int y, int table[][]) {
        this.x = x;
        this.y = y;
        this.table = table;
    }

    public boolean winCheck() {
        int i;
        int ifWin = 1;
        int requiredInRow = 3;
        // x-dir check
        i = 1;
        try {
            while (table[x+i][y] == table[x][y]) {
                i++;
                ifWin++;
                if (ifWin == requiredInRow) {
                    System.out.println("<<<||| Player " + table[x][y] + " wins |||>>>");
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {}
        i = 1;
        try {
            while (table[x-i][y] == table[x][y]) {
                i++;
                ifWin++;
                if (ifWin == requiredInRow) {
                    System.out.println("<<<||| Player " + table[x][y] + " wins |||>>>");
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {}
// y-dir check
        i = 1;
        ifWin = 1;
        try {
            while (table[x][y+i] == table[x][y]) {
                i++;
                ifWin++;
                if (ifWin == requiredInRow) {
                    System.out.println("<<<||| Player " + table[x][y] + " wins |||>>>");
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {}
        i = 1;
        try {
            while (table[x][y-i] == table[x][y]) {
                i++;
                ifWin++;
                if (ifWin == requiredInRow) {
                    System.out.println("<<<||| Player " + table[x][y] + " wins |||>>>");
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
// cross-dir (down-right) check
        i = 1;
        ifWin = 1;
        try {
            while (table[x+i][y+i] == table[x][y]) {
                i++;
                ifWin++;
                if (ifWin == requiredInRow) {
                    System.out.println("<<<||| Player " + table[x][y] + " wins |||>>>");
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        i = 1;
        try {
            while (table[x-i][y-i] == table[x][y]) {
                i++;
                ifWin++;
                if (ifWin == requiredInRow) {
                    System.out.println("<<<||| Player " + table[x][y] + " wins |||>>>");
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
// cross-dir (up-right) check
        i = 1;
        ifWin = 1;
        try {
            while (table[x-i][y+i] == table[x][y]) {
                i++;
                ifWin++;
                if (ifWin == requiredInRow) {
                    System.out.println("<<<||| Player " + table[x][y] + " wins |||>>>");
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        i = 1;
        try {
            while (table[x+i][y-i] == table[x][y]) {
                i++;
                ifWin++;
                if (ifWin == requiredInRow) {
                    System.out.println("<<<||| Player " + table[x][y] + " wins |||>>>");
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return false;
    }

}
