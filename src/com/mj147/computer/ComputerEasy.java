package com.mj147.computer;

public class ComputerEasy extends ComputerOpponent {

    public ComputerEasy(int[][] table, int x, int y) {
        super(table, x, y);
    }

    @Override
    public String play() {

        return randomMove();
    }
}
