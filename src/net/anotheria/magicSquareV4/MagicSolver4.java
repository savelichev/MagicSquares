package net.anotheria.magicSquareV4;


import java.io.IOException;

public class MagicSolver4 implements Runnable {


    private final int SQUARE_SIDE;
    private final int MAGIC_SUM;
    private MagicSquare4 magicSquare4;
    private SquareWriter squareWriter = new SquareWriter();
    private int fromElement;
    private int toElement;


    public MagicSolver4(int squareSide) throws IOException {
        magicSquare4 = new MagicSquare4(squareSide);
        SQUARE_SIDE = squareSide;
        MAGIC_SUM = (SQUARE_SIDE * SQUARE_SIDE * SQUARE_SIDE + SQUARE_SIDE) / 2;
    }

    public MagicSolver4(int squareSide, int fromElement, int toElement, SquareWriter squareWriter) throws IOException {
        magicSquare4 = new MagicSquare4(squareSide);
        SQUARE_SIDE = squareSide;
        MAGIC_SUM = (SQUARE_SIDE * SQUARE_SIDE * SQUARE_SIDE + SQUARE_SIDE) / 2;
        this.fromElement = fromElement;
        this.toElement = toElement;
        this.squareWriter = squareWriter;
    }


    private void completeRow(int row, int column) {

        for (int i = 1; i <= SQUARE_SIDE * SQUARE_SIDE; i++) {

            if (magicSquare4.getTakenElements().contains(i)) {
                continue;
            }
            reservePosition(row, column, i);

            if (SQUARE_SIDE - column == 2) {
                int lastElement = MAGIC_SUM - getRowSum(row);

                if (lastElement > 0 && lastElement <= SQUARE_SIDE * SQUARE_SIDE &&
                        !magicSquare4.getTakenElements().contains(lastElement)) {
                    reservePosition(row, column + 1, lastElement);
                    completeColumn(row + 1, row);
                    clearPosition(row, column + 1, lastElement);
                }
            } else if (SQUARE_SIDE - column == 1) {

                if (getRowSum(row) == MAGIC_SUM) {

                    if (isMagicDiagonals()) {
                        try {
                            squareWriter.writeResult(magicSquare4);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                completeRow(row, column + 1);
            }
            clearPosition(row, column, i);
        }
    }

    private void completeColumn(int row, int column) {

        for (int i = 1; i <= SQUARE_SIDE * SQUARE_SIDE; i++) {
            if (magicSquare4.getTakenElements().contains(i)) {
                continue;
            }
            reservePosition(row, column, i);

            if (SQUARE_SIDE - row == 2) {
                int lastElement = MAGIC_SUM - getColumnSum(column);

                if (lastElement > 0 && lastElement <= SQUARE_SIDE * SQUARE_SIDE &&
                        !magicSquare4.getTakenElements().contains(lastElement)) {
                    reservePosition(row + 1, column, lastElement);
                    completeRow(column + 1, column + 1);
                    clearPosition(row + 1, column, lastElement);
                }
            } else if (SQUARE_SIDE - row == 1) {

                if (getColumnSum(column) == MAGIC_SUM) {
                    completeRow(column + 1, column + 1);
                }
            } else {
                completeColumn(row + 1, column);
            }
            clearPosition(row, column, i);
        }
    }

    private void reservePosition(int row, int column, int value) {
        magicSquare4.getRow()[row][column] = value;
        magicSquare4.getTakenElements().add(value);
    }

    private void clearPosition(int row, int column, int value) {
        magicSquare4.getRow()[row][column] = 0;
        magicSquare4.getTakenElements().remove(magicSquare4.getTakenElements().indexOf(value));
    }

    public boolean isMagicDiagonals() {

        int lDiagonal = 0;
        int rDiagonal = 0;

        for (int i = 0; i < SQUARE_SIDE; i++) {
            lDiagonal += magicSquare4.getRow()[i][i];

        }
        if (lDiagonal != MAGIC_SUM) {
            return false;
        }

        for (int i = 0; i < SQUARE_SIDE; i++) {

            rDiagonal += magicSquare4.getRow()[i][(SQUARE_SIDE - 1) - i];
        }

        if (rDiagonal != MAGIC_SUM) {
            return false;
        }
        return true;
    }

    public int getRowSum(int x) {
        int rowSum = 0;
        for (int y = 0; y < SQUARE_SIDE; y++) {
            rowSum += magicSquare4.getRow()[x][y];
        }
        return rowSum;
    }

    public int getColumnSum(int y) {

        int columnSum = 0;
        for (int x = 0; x < SQUARE_SIDE; x++) {
            columnSum += magicSquare4.getRow()[x][y];
        }
        return columnSum;
    }


    @Override
    public void run() {

        for (int i = fromElement; i < toElement; i++) {
            magicSquare4.getRow()[0][0] = i;
            magicSquare4.getTakenElements().add(i);

            completeRow(0, 1);

            magicSquare4.getRow()[0][0] = 0;
            magicSquare4.getTakenElements().remove(magicSquare4.getTakenElements().indexOf(i));
        }
    }
}
