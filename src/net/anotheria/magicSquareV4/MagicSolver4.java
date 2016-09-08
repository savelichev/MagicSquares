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
            magicSquare4.getRow()[row][column] = i;
            magicSquare4.getTakenElements().add(i);

            if (SQUARE_SIDE - column == 2) {

                int lastElement = MAGIC_SUM - magicSquare4.getRowSum(row);

                if (lastElement > 0 && lastElement <= SQUARE_SIDE * SQUARE_SIDE &&
                        !magicSquare4.getTakenElements().contains(lastElement)) {

                    magicSquare4.getRow()[row][column + 1] = lastElement;
                    magicSquare4.getTakenElements().add(lastElement);

                    completeColumn(row + 1, row);

                    magicSquare4.getRow()[row][column + 1] = 0;
                    magicSquare4.getTakenElements().remove(magicSquare4.getTakenElements().indexOf(lastElement));
                }
            } else if (SQUARE_SIDE - column == 1) {

                if (magicSquare4.getRowSum(row) == MAGIC_SUM) {

                    if (magicSquare4.isMagicDiagonals()) {
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
            magicSquare4.getRow()[row][column] = 0;
            magicSquare4.getTakenElements().remove(magicSquare4.getTakenElements().indexOf(i));
        }
    }

    private void completeColumn(int row, int column) {

        for (int i = 1; i <= SQUARE_SIDE * SQUARE_SIDE; i++) {
            if (magicSquare4.getTakenElements().contains(i)) {
                continue;
            }

            magicSquare4.getRow()[row][column] = i;
            magicSquare4.getTakenElements().add(i);

            if (SQUARE_SIDE - row == 2) {

                int lastElement = MAGIC_SUM - magicSquare4.getColumnSum(column);

                if (lastElement > 0 && lastElement <= SQUARE_SIDE * SQUARE_SIDE &&
                        !magicSquare4.getTakenElements().contains(lastElement)) {

                    magicSquare4.getRow()[row + 1][column] = lastElement;
                    magicSquare4.getTakenElements().add(lastElement);

                    completeRow(column + 1, column + 1);

                    magicSquare4.getRow()[row + 1][column] = 0;
                    magicSquare4.getTakenElements().remove(magicSquare4.getTakenElements().indexOf(lastElement));
                }
            } else if (SQUARE_SIDE - row == 1) {

                if (magicSquare4.getColumnSum(column) == MAGIC_SUM) {
                    completeRow(column + 1, column + 1);
                }
            } else {
                completeColumn(row + 1, column);
            }
            magicSquare4.getRow()[row][column] = 0;
            magicSquare4.getTakenElements().remove(magicSquare4.getTakenElements().indexOf(i));
        }
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
