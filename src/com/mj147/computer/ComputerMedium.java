package com.mj147.computer;

public class ComputerMedium extends ComputerOpponent{

    protected int k;

    public ComputerMedium(int[][] table, int x, int y, int k) {
        super(table, x, y);
        this.k = k;
    }

    @Override
    public String play() {

        int whoToCheck = 1;
        int requiredToBlock = 2;

        if (k == 1) {                              // first move, if computer player is started
            return randomMove();
        }

        if ( checkInLine(whoToCheck, requiredToBlock) != "0" ) {
            return checkInLine(whoToCheck, requiredToBlock);
        }

        if ( blockStrategy() != "0" ) {
            return blockStrategy();
        }

        return randomMove();
    }

    public String checkInLine(int whoToCheck, int requiredToBlock) {
//        whoToCheck = 1 (block player 1)
//                     2 (block player 2)
//        requiredToBlock = 2 (block if find 2 in line)
//                          3 (block if find 3 in line)
//                          4...etc.
        int xB;
        int yB;
        int ifWin;

//// x-dir check
        try{
            for (int j = 0; j < table[1].length; j++) {
                ifWin = 0;
                xB = -1;
                yB = -1;
                for (int i = 0; i < table.length; i++) {
                    if (table[i][j] == 0) {
                        xB = i;
                        yB = j;
                    }
                    if (table[i][j] == whoToCheck) {
                        ifWin++;
                    }
                    if (ifWin == requiredToBlock && xB != -1) {
//                        System.out.println("<<< BLOCK X!!! >>>");       // info in console for developer
                        return String.valueOf(xB) + yB;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {}
//// y-dir check
        try{
            for (int i = 0; i < table.length; i++) {
                ifWin = 0;
                xB = -1;
                yB = -1;
                for (int j = 0; j < table[1].length; j++) {
                    if (table[i][j] == 0) {
                        xB = i;
                        yB = j;
                    }
                    if (table[i][j] == whoToCheck) {
                        ifWin++;
                    }
                    if (ifWin == requiredToBlock && xB != -1) {
//                        System.out.println("<<< BLOCK Y!!! >>>");        // info in console for developer
                        return String.valueOf(xB) + yB;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {}
//// cross-dir (down-right) check
        try{
            ifWin = 0;
            xB = -1;
            yB = -1;
            for (int i = 0; i < table.length; i++) {
                if (table[i][i] == 0) {
                    xB = i;
                    yB = i;
                }
                if (table[i][i] == whoToCheck) {
                    ifWin++;
                }
                if (ifWin == requiredToBlock && xB != -1) {
//                    System.out.println("<<< BLOCK down-right!!! >>>");         // info in console for developer
                    return String.valueOf(xB) + yB;

                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {}
//// cross-dir (up-right) check
        try{
            ifWin = 0;
            xB = -1;
            yB = -1;
            for (int i = 0; i < table.length; i++) {
                int j = table.length - 1 - i;
                if (table[i][j] == 0) {
                    xB = i;
                    yB = j;
                }
                if (table[i][j] == whoToCheck) {
                    ifWin++;
                }
                if (ifWin == requiredToBlock && xB != -1) {
//                    System.out.println("<<< BLOCK up-right!!! >>>");        // info in console for developer
                    return String.valueOf(xB) + yB;

                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {}

        return "0";
    }

    public String blockStrategy() {
//        System.out.println("BLOCKSTRATEGY");          // info in console for developer
        if (table[1][1] == 0) {
            return String.valueOf(1) + 1;
        } else {
            if (table[0][0] + table[2][2] + table[0][2] + table[2][0] == 0 || table[0][0] + table[2][2] + table[0][2] + table[2][0] == 3) {
                do {
                    x = random.nextInt(3);
                    y = random.nextInt(3);
                } while (x == 1 || y == 1);
                return String.valueOf(x) + y;
            } else {
                if (table[0][0] + table[2][2] + table[0][2] + table[2][0] == 2) {
                    do {
                        x = random.nextInt(3);
                        y = random.nextInt(3);
                    } while (x + y != 1 && x + y != 3);
                    return String.valueOf(x) + y;
                } else {
                    return "0";
                }
            }
        }
    }

}
