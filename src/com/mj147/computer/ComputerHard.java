package com.mj147.computer;

public class ComputerHard extends ComputerMedium {

    public ComputerHard(int[][] table, int x, int y, int k) {
        super(table, x, y, k);
    }

    @Override
    public String play() {

        int whoToCheck;
        int requiredToBlock = 2;

        if (k == 1) {                           // first move, if computer player is started
            return randomMove();
//            return String.valueOf(1) + 1;     // if you want force first move
        }

        whoToCheck = 2;
        String checkPlayer2 = checkInLine(whoToCheck, requiredToBlock);
        if ( !checkPlayer2.equals("0")) {
            return checkPlayer2;
        }

        whoToCheck = 1;
        String checkPlayer1 = checkInLine(whoToCheck, requiredToBlock);
        if ( !checkPlayer1.equals("0")) {
            return checkPlayer1;
        }

        String aS = attackStrategy();
        if ( !aS.equals("0") ) {
            return aS;
        }

        String bS = blockStrategy();
        if ( !bS.equals("0") ) {
            return bS;
        }

        return randomMove();
    }

    public String attackStrategy() {
//        System.out.println("ATTACK STRATEGY");        // info in console for developer
        if (table[1][1] == 2) {
            if (k == 3) {
                String cC = checkCorners(1);
                String oC = oppositeField(cC);

                return oC;
            } else {
                if (k == 5) {
                    if (table[0][0] + table[2][2] + table[0][2] + table[2][0] == 3) {
                        String cC = checkCorners(2);
                        int xCorner = Character.getNumericValue(cC.charAt(0));
                        int yCorner = Character.getNumericValue(cC.charAt(1));

                        do {
                            x = random.nextInt(3);
                            y = random.nextInt(3);
                        } while (x != 1 && x != xCorner || y != yCorner && y != 1);

                        return String.valueOf(x) + y;
                    }
                }
            }
        }

        if (table[1][1] == 1) {
            if (k == 3) {
                if (table[0][0] + table[2][2] + table[0][2] + table[2][0] == 2) {
                    String cC = checkCorners(2);
                    String oF = oppositeField(cC);

                    return oF;
                } else {
                    String cS = checkSides(2);
                    String oF = oppositeField(cS);
                    do {
                        x = random.nextInt(3);
                        y = random.nextInt(3);
                    } while ( x + y != 1 && x + y != 3 || (String.valueOf(x) + y).equals(oF) );

                    return String.valueOf(x) + y;
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
            return String.valueOf(2) + 0;
        } else {
            if (table[0][1] == table[1][2]) {
                return String.valueOf(0) + 2;
            } else {
                if (table[1][0] == table[0][1]) {
                    return String.valueOf(0) + 0;
                } else {
                    if (table[1][2] == table[2][1]) {
                        return String.valueOf(2) + 2;
                    }
                }
            }
        }
        return "0";
    }

    public String oppositeField(String field) {
        x = Character.getNumericValue(field.charAt(0));
        y = Character.getNumericValue(field.charAt(1));
//        System.out.println("z oppositeField" + x + y);      // info in console for developer
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
        return String.valueOf(x) + y;
    }

    public String checkCorners(int x) {
        if (table[0][0] == x) {
            return String.valueOf(0) + 0;
        } else {
            if (table[2][2] == x) {
                return String.valueOf(2) + 2;
            } else {
                if (table[0][2] == x) {
                    return String.valueOf(0) + 2;
                } else {
                    if (table[2][0] == x) {
                        return String.valueOf(2) + 0;
                    } else {
                        do {
                            x = random.nextInt(3);
                            y = random.nextInt(3);
                        } while (x == 1 || y == 1);
                        return String.valueOf(x) + y;
                    }
                }
            }
        }
    }

    public String checkSides(int x) {
        if (table[1][0] == x) {
            return String.valueOf(1) + 0;
        } else {
            if (table[0][1] == x) {
                return String.valueOf(0) + 1;
            } else {
                if (table[2][1] == x) {
                    return String.valueOf(2) + 1;
                } else {
                    if (table[1][2] == x) {
                        return String.valueOf(1) + 2;
                    } else {
                        return String.valueOf(-1) + -1;
                    }
                }
            }
        }
    }
}
