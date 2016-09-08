package net.savelichev.magicSquare;


import java.io.IOException;

public class MagicSolver implements Runnable {


    private final int SQUARE_SIDE;
    private final int MAGIC_SUM;
    private int fromElement;
    private int toElement;

    private MagicSquare magicSquare;
    private SquareWriter squareWriter = new SquareWriter();


    /**
     * Constructor for one thread realization
     *
     * @param squareSide size of square side
     * @throws IOException when SquareWriter init problems
     */
    public MagicSolver(int squareSide) throws IOException {
        magicSquare = new MagicSquare(squareSide);
        SQUARE_SIDE = squareSide;
        MAGIC_SUM = (SQUARE_SIDE * SQUARE_SIDE * SQUARE_SIDE + SQUARE_SIDE) / 2;
    }

    /**
     * Constructor for multithreading realization
     *
     * @param squareSide   size of square side
     * @param fromElement  begin of range for first element, inclusive this value
     * @param toElement    end of range for first element, not inclusive this value
     * @param squareWriter write result into the file.
     * @throws IOException when SquareWriter init problems
     */
    public MagicSolver(int squareSide, int fromElement, int toElement, SquareWriter squareWriter) throws IOException {
        magicSquare = new MagicSquare(squareSide);
        SQUARE_SIDE = squareSide;
        MAGIC_SUM = (SQUARE_SIDE * SQUARE_SIDE * SQUARE_SIDE + SQUARE_SIDE) / 2;
        this.fromElement = fromElement;
        this.toElement = toElement;
        this.squareWriter = squareWriter;
    }


    /**
     * Fills row using recursion.
     * When it's  the penultimate index of the row, try's to fill last element in row
     * as a difference of MAGIC_SUM and row sum. If calculated last element not
     * valid fo this row, it go for one step back.
     * When all elements in row are filled, invokes method for filling column.
     * When it's the last column, it means that square is completed, then checks is
     * the square magic. If is, then send result to the SquareWriter, if not, go step back.
     *
     * @param row    index of the row
     * @param column index of the column
     */
    private void completeRow(int row, int column) {

        for (int i = 1; i <= SQUARE_SIDE * SQUARE_SIDE; i++) {

            if (magicSquare.getUsedElements().contains(i)) {
                continue;
            }
            reservePosition(row, column, i);

            if (SQUARE_SIDE - column == 2) {
                int lastElement = MAGIC_SUM - getRowSum(row);

                if (lastElement > 0 && lastElement <= SQUARE_SIDE * SQUARE_SIDE &&
                        !magicSquare.getUsedElements().contains(lastElement)) {
                    reservePosition(row, column + 1, lastElement);
                    completeColumn(row + 1, row);
                    clearPosition(row, column + 1, lastElement);
                }
            } else if (SQUARE_SIDE - column == 1) {

                if (getRowSum(row) == MAGIC_SUM) {

                    if (isMagicDiagonals()) {
                        try {
                            squareWriter.writeResult(magicSquare);
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

    /**
     * Fills column using recursion.
     * When it's  the penultimate index of the row, try's to fill last element in row
     * as a difference of MAGIC_SUM and row sum. If calculated last element not
     * valid fo this row, it go for one step back.
     * When all elements in the column are filled, invokes method for filling row.
     *
     * @param row    index of the row
     * @param column index of the column
     */
    private void completeColumn(int row, int column) {

        for (int i = 1; i <= SQUARE_SIDE * SQUARE_SIDE; i++) {
            if (magicSquare.getUsedElements().contains(i)) {
                continue;
            }
            reservePosition(row, column, i);

            if (SQUARE_SIDE - row == 2) {
                int lastElement = MAGIC_SUM - getColumnSum(column);

                if (lastElement > 0 && lastElement <= SQUARE_SIDE * SQUARE_SIDE &&
                        !magicSquare.getUsedElements().contains(lastElement)) {
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


    /**
     * Reserving position in square with relevant coordinates.
     * Also adds current value into used elements list.
     *
     * @param row    index of the row
     * @param column index of the column
     * @param value  value for reserving
     */
    private void reservePosition(int row, int column, int value) {
        magicSquare.getSquare()[row][column] = value;
        magicSquare.getUsedElements().add(value);
    }

    /**
     * Clearing position in square with relevant coordinates.
     * Also removes current value from used elements list
     *
     * @param row    index of the row
     * @param column index of the column
     * @param value  value for reserving
     */
    private void clearPosition(int row, int column, int value) {
        magicSquare.getSquare()[row][column] = 0;
        magicSquare.getUsedElements().remove(magicSquare.getUsedElements().indexOf(value));
    }


    /**
     * Checking diagonals for MAGIC_SUM equals
     *
     * @return true if equals, false if not
     */
    public boolean isMagicDiagonals() {

        int lDiagonal = 0;
        int rDiagonal = 0;

        for (int i = 0; i < SQUARE_SIDE; i++) {
            lDiagonal += magicSquare.getSquare()[i][i];

        }
        if (lDiagonal != MAGIC_SUM) {
            return false;
        }

        for (int i = 0; i < SQUARE_SIDE; i++) {

            rDiagonal += magicSquare.getSquare()[i][(SQUARE_SIDE - 1) - i];
        }

        if (rDiagonal != MAGIC_SUM) {
            return false;
        }
        return true;
    }

    /**
     * Calculate sum of the row
     *
     * @param x row index
     * @return sum of all elements in row
     */
    public int getRowSum(int x) {
        int rowSum = 0;
        for (int y = 0; y < SQUARE_SIDE; y++) {
            rowSum += magicSquare.getSquare()[x][y];
        }
        return rowSum;
    }

    /**
     * Calculate sum of the column
     *
     * @param y column index
     * @return sum of all elements in column
     */
    public int getColumnSum(int y) {

        int columnSum = 0;
        for (int x = 0; x < SQUARE_SIDE; x++) {
            columnSum += magicSquare.getSquare()[x][y];
        }
        return columnSum;
    }


    @Override
    public void run() {

        for (int i = fromElement; i < toElement; i++) {
            reservePosition(0, 0, i);
            completeRow(0, 1);
            clearPosition(0, 0, i);
        }
    }
}
