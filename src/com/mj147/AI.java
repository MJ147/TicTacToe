package com.mj147;

import java.util.Random;

public class AI {

    Random random = new Random();
    private int[][] table;
    private int x,y;
    private int k;

    public AI(int table[][], int x, int y, int k) {
        this.table = table;
        this.x = x;
        this.y = y;
        this.k = k;
    }

    public String easy() {
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (table[x][y] != 0);
        System.out.println(String.valueOf(x) + String.valueOf(y));
        return String.valueOf(x) + String.valueOf(y);
    }

    public String medium() {
        int whoToCheck = 1;
        int requiredToBlock = 2;
        if (k == 1) {
            return easy();
        }
        if ( checkInLine(whoToCheck, requiredToBlock) != "0" ) {
            return checkInLine(whoToCheck, requiredToBlock);
        }
        if ( blockStrategy() != "0" ) {
            return blockStrategy();
        }
        return easy();
    }

    public String hard() {
        int whoToCheck;
        int requiredToBlock = 2;
        if (k == 1) {
            return easy();
//            return String.valueOf(2) + String.valueOf(1);
        }
        whoToCheck = 2;
        String cIL2 = checkInLine(whoToCheck, requiredToBlock);
        if ( !cIL2.equals("0")) {
            return cIL2;
        }
        whoToCheck = 1;
        String cIL1 = checkInLine(whoToCheck, requiredToBlock);
        if ( !cIL1.equals("0")) {
            return cIL1;
        }
        String aS = attackStrategy();
        if ( !aS.equals("0") ) {
            return aS;
        }
        String bS = blockStrategy();
        if ( !bS.equals("0") ) {
            return bS;
        }
        return easy();
    }

    public String blockStrategy() {
        System.out.println("BLOCKSTRATEGY");
        if (table[1][1] == 0) {
            return String.valueOf(1) + String.valueOf(1);
        } else {
            if (table[0][0] + table[2][2] + table[0][2] + table[2][0] == 0 || table[0][0] + table[2][2] + table[0][2] + table[2][0] == 3) {
                do {
                    x = random.nextInt(3);
                    y = random.nextInt(3);
                } while (x == 1 || y == 1);
                return String.valueOf(x) + String.valueOf(y);
            } else {
                if (table[0][0] + table[2][2] + table[0][2] + table[2][0] == 2) {
                    do {
                        x = random.nextInt(3);
                        y = random.nextInt(3);
                    } while (x + y != 1 && x + y != 3);
                    return String.valueOf(x) + String.valueOf(y);
                } else {
                    return "0";
                }
            }
        }
    }

    public String attackStrategy() {
        System.out.println("ATTACK STRATEGY");
        if (table[1][1] == 2) {
            if (k == 3) {
                String cC = checkCorners(1);
                String oC = oppositeField(cC);
                System.out.println(oC);
                return oC;
            } else {
                if (k == 5) {
                    if (table[0][0] + table[2][2] + table[0][2] + table[2][0] == 3) {
                        String cC = checkCorners(2);
                        int xCorner = Character.getNumericValue(cC.charAt(0));
                        int yCorner = Character.getNumericValue(cC.charAt(1));
                        System.out.println(xCorner + "" + yCorner);
                        do {
                            x = random.nextInt(3);
                            y = random.nextInt(3);
                        } while (x != 1 && x != xCorner || y != yCorner && y != 1);
                        System.out.println(x + "" + y);
                        return String.valueOf(x) + String.valueOf(y);
                    }
                }
            }
        }
        if (table[1][1] == 1) {
            if (k == 3) {
                if (table[0][0] + table[2][2] + table[0][2] + table[2][0] == 2) {
                    String cC = checkCorners(2);
                    String oF = oppositeField(cC);
                    System.out.println(oF);
                    return oF;
                } else {
                    String cS = checkSides(2);
                    String oF = oppositeField(cS);
                    System.out.println(oF);
                    do {
                        x = random.nextInt(3);
                        y = random.nextInt(3);
//                        } while (x + y != 1 && x + y != 3);
                    } while ( x + y != 1 && x + y != 3 || (String.valueOf(x) + String.valueOf(y)).equals(oF) );
                    return String.valueOf(x) + String.valueOf(y);
                }
            }
            if (k == 5 && table[0][1] + table[1][0] + table[1][2] + table[2][1] == 5 && table[0][0] + table[0][2] + table[2][2] + table [2][0] == 0) {
                return finishIt();
            }
        }
        return "0";
    }

    public String finishIt() {
        if (table[1][0] == table[2][1]) {
            return String.valueOf(2) + String.valueOf(0);
        } else {
            if (table[0][1] == table[1][2]) {
                return String.valueOf(0) + String.valueOf(2);
            } else {
                if (table[1][0] == table[0][1]) {
                    return String.valueOf(0) + String.valueOf(0);
                } else {
                    if (table[1][2] == table[2][1]) {
                        return String.valueOf(2) + String.valueOf(2);
                    }
                }
            }
        }
        return "0";
    }

    public String oppositeField(String field) {
        x = Character.getNumericValue(field.charAt(0));
        y = Character.getNumericValue(field.charAt(1));
        System.out.println("Z OPPOS" + String.valueOf(x) + String.valueOf(y));
        if (x == 0) {
            x = 2;
        } else {
            if (x == 2) {
                x = 0;
            }
        }
        if (y == 0) {
            y = 2;
        } else {
            if (y == 2) {
                y = 0;
            }
        }
        return String.valueOf(x) + String.valueOf(y);
    }
    public String checkCorners(int x) {
        if (table[0][0] == x) {
            return String.valueOf(0) + String.valueOf(0);
        } else {
            if (table[2][2] == x) {
                return String.valueOf(2) + String.valueOf(2);
            } else {
                if (table[0][2] == x) {
                    return String.valueOf(0) + String.valueOf(2);
                } else {
                    if (table[2][0] == x) {
                        return String.valueOf(2) + String.valueOf(0);
                    } else {
                        return String.valueOf(-1) + String.valueOf(-1);
                    }
                }
            }
        }
    }
    public String checkSides(int x) {
        if (table[1][0] == x) {
            return String.valueOf(1) + String.valueOf(0);
        } else {
            if (table[0][1] == x) {
                return String.valueOf(0) + String.valueOf(1);
            } else {
                if (table[2][1] == x) {
                    return String.valueOf(2) + String.valueOf(1);
                } else {
                    if (table[1][2] == x) {
                        return String.valueOf(1) + String.valueOf(2);
                    } else {
                        return String.valueOf(-1) + String.valueOf(-1);
                    }
                }
            }
        }
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
                        System.out.println("<<< BLOCK X!!! >>>");
                        return String.valueOf(xB) + String.valueOf(yB);
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
                        System.out.println("<<< BLOCK Y!!! >>>");
                        return String.valueOf(xB) + String.valueOf(yB);
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
                    System.out.println("<<< BLOCK down-right!!! >>>");
                    return String.valueOf(xB) + String.valueOf(yB);

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
                    System.out.println("<<< BLOCK up-right!!! >>>");
                    return String.valueOf(xB) + String.valueOf(yB);

                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {}

        return "0";
    }
}
