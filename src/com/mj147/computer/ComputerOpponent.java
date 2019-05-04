package com.mj147.computer;

import java.util.Random;

public abstract class ComputerOpponent {

    protected Random random = new Random();
    protected int[][] table;
    protected int x,y;

    public ComputerOpponent(int[][] table, int x, int y) {
        this.table = table;
        this.x = x;
        this.y = y;
    }

    public String randomMove() {

        String xy;

        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (table[x][y] != 0);

        xy = String.valueOf(x) + y;
//        System.out.println(xy);      // info in console for developer

        return xy;
    }

    public abstract String play();

}
