package net.anotheria.magicSquareV4;


import java.util.Arrays;

@SuppressWarnings("Duplicates")
public class MagicSolver4 {


    private final int SQUARE_SIDE;
    private final int MAGIC_SUM;


    public MagicSolver4(int squareSide) {
        SQUARE_SIDE = squareSide;
        MAGIC_SUM = (SQUARE_SIDE * SQUARE_SIDE * SQUARE_SIDE + SQUARE_SIDE) / 2;
    }

    public void start() {

        for (int i = 1; i <= SQUARE_SIDE * SQUARE_SIDE; i++) {
            MagicSquare4 magicSquare4 = new MagicSquare4(SQUARE_SIDE);
            magicSquare4.getRow()[0][0] = i;
            magicSquare4.getTakenElements().add(i);
            fillNext(magicSquare4, 0, 0);
        }
    }

    public void fillNext(MagicSquare4 magicSquare4, int X, int Y) {

//        magicSquare4.printRow();

        if (X == magicSquare4.getSIDE() || Y == magicSquare4.getSIDE()) {
            System.out.println("found!");
            magicSquare4.printRow();
            return;
        }

        if (X == Y) {
            completeRow(magicSquare4, X, Y);

        }
        if (X - Y == 1) {
            completeColumn(magicSquare4, X, Y);
        }
    }

    public void completeRow(MagicSquare4 magicSquare4, int X, int Y) {

        int rowSum = 0;

        for (int y = 0; y < SQUARE_SIDE; y++) {
            rowSum += magicSquare4.getRow()[X][y];
        }
        if (rowSum > MAGIC_SUM) {
            return;
        }

        for (int y = 0; y < SQUARE_SIDE; y++) {
            if (magicSquare4.getRow()[X][y] > 0) {
                continue;
            }

            if (SQUARE_SIDE - y == 1) {

                int lastElement = MAGIC_SUM - rowSum;
                if (lastElement <= 0 || lastElement > SQUARE_SIDE * SQUARE_SIDE || magicSquare4.getTakenElements().contains(lastElement)) {
                    return;
                }

                MagicSquare4 tempMagicSquare4 = new MagicSquare4(magicSquare4);

                tempMagicSquare4.getRow()[X][y] = lastElement;
                tempMagicSquare4.getTakenElements().add(lastElement);

                fillNext(tempMagicSquare4, ++X, Y);

            }

            for (int j = 1; j <= SQUARE_SIDE * SQUARE_SIDE; j++) {

                MagicSquare4 tempMagicSquare4 = new MagicSquare4(magicSquare4);

                if (tempMagicSquare4.getTakenElements().contains(j)) {
                    continue;
                } else {

                    tempMagicSquare4.getRow()[X][y] = j;
                    tempMagicSquare4.getTakenElements().add(j);

                    completeRow(tempMagicSquare4, X, Y);
                }
            }
        }
    }

    public void completeColumn(MagicSquare4 magicSquare4, int X, int Y) {

        int columnSum = 0;

        for (int x = 0; x < SQUARE_SIDE; x++) {
            columnSum += magicSquare4.getRow()[x][Y];
        }

        if (columnSum > MAGIC_SUM) {
            return;
        }

        for (int x = 0; x < SQUARE_SIDE; x++) {
            if (magicSquare4.getRow()[x][Y] > 0) {
                continue;
            }

            if (SQUARE_SIDE - x == 1) {

                MagicSquare4 tempMagicSquare4 = new MagicSquare4(magicSquare4);
                int lastElement = MAGIC_SUM - columnSum;
                if (lastElement <= 0 || lastElement > SQUARE_SIDE * SQUARE_SIDE) {
                    return;
                }
                if (!tempMagicSquare4.getTakenElements().contains(lastElement)) {

                    tempMagicSquare4.getRow()[x][Y] = lastElement;
                    tempMagicSquare4.getTakenElements().add(lastElement);
                    fillNext(tempMagicSquare4, X, ++Y);
                }


            }

            for (int j = 1; j <= SQUARE_SIDE * SQUARE_SIDE; j++) {

                if ((magicSquare4.getTakenElements().contains(j))) {
                    continue;

                } else {
                    MagicSquare4 tempMagicSquare4 = new MagicSquare4(magicSquare4);

                    tempMagicSquare4.getRow()[x][Y] = j;
                    tempMagicSquare4.getTakenElements().add(j);

                    completeColumn(tempMagicSquare4, X, Y);
                }
            }
        }
    }

}
