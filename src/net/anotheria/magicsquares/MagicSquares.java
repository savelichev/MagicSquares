package net.anotheria.magicsquares;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@SuppressWarnings("Duplicates")
public class MagicSquares {


    private final int side;
    private final int squareSize;
    private final int magicSum;

    private int counter;
    private int middleCounter;

    public MagicSquares(int side) {
        this.side = side;
        squareSize = side * side;
        magicSum = (side * side * side + side) / 2;
    }


    private SquareWriter squareWriter = new SquareWriter();


    public void start() {

        for (int i = 1; i <= squareSize; i++) {
            getSquares(i);
        }

    }

    private void getSquares(int firstElement) {

        List<Integer> row = new ArrayList<>();

        row.add(firstElement);

        nextElement(row);

    }

    private void nextElement(List<Integer> currentRow) {


        for (int i = 1; i <= squareSize; i++) {

            List<Integer> tempCurrentRow = new ArrayList<>(squareSize);
            tempCurrentRow.addAll(currentRow);

            if (tempCurrentRow.contains(i)) {
                continue;
            }

            if (tempCurrentRow.size() % side == 0 && !isValidRows(tempCurrentRow)) {
                return;
            }

            if (tempCurrentRow.size() > 2 && isMidColumnSumVeryBig(tempCurrentRow)
                    ||isMidDiagonalSumVeryBig(tempCurrentRow)) {
                return;
            }


            if (tempCurrentRow.size() < squareSize) {
                tempCurrentRow.add(i);
            }

            if (tempCurrentRow.size() == squareSize && !isMagic(tempCurrentRow)) {
                return;
            }


            if (tempCurrentRow.size() == squareSize) {
                System.out.println(++counter);
                System.out.println(tempCurrentRow);

            }
            nextElement(tempCurrentRow);
        }

    }

    private boolean isMagic(List<Integer> tempCurrentRow) {


        if (!isValidColumns(tempCurrentRow)) {
            return false;
        }

        if (!isValidDiagonals(tempCurrentRow)) {
            return false;
        }

        return true;
    }


    private boolean isValidRows(List<Integer> tempCurrentRow) {

        for (int k = 0; k < tempCurrentRow.size(); k = k + side) {

            int rowSum = 0;

            for (int elem : tempCurrentRow.subList(k, k + side)) {
                rowSum += elem;
            }

            if (rowSum != magicSum) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidColumns(List<Integer> tempCurrentRow) {

        for (int i = 0; i < side; i++) {

            int columnSum = 0;

            for (int j = i; j < squareSize; j = j + side) {
                columnSum += tempCurrentRow.get(j);
            }

            if (columnSum != magicSum) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidDiagonals(List<Integer> tempCurrentRow) {

        int lDiagonalSum = 0;
        int rDiagonalSum = 0;

        for (int i = 0; i < squareSize; i = i + (side + 1)) {
            lDiagonalSum += tempCurrentRow.get(i);
        }

        if (lDiagonalSum != magicSum) {
            return false;
        }

        for (int i = side - 1; i < squareSize - 1; i = i + (side - 1)) {
            rDiagonalSum += tempCurrentRow.get(i);
        }

        if (rDiagonalSum != magicSum) {
            return false;
        }

        return true;
    }

    private boolean isMidColumnSumVeryBig(List<Integer> tempCurrentRow) {

        for (int i = 0; i < side; i++) {
            int columnSum = 0;
            for (int j = 0; j < (tempCurrentRow.size() - side); j = j + side) {
                columnSum += tempCurrentRow.get(i + j);
            }
            if (columnSum > magicSum) {
//                System.out.println(++middleCounter+" mid");
                return true;
            }
        }

        return false;
    }

    private boolean isMidDiagonalSumVeryBig(List<Integer> tempCurrentRow) {


        int lDiagonalSum = 0;
        int rDiagonalSum = 0;

        for (int j = 0; j < tempCurrentRow.size(); j = j + side + 1) {
            lDiagonalSum += tempCurrentRow.get(j);
        }

        if (lDiagonalSum > magicSum) {
            return true;
        }

        for (int j = side-1; j < tempCurrentRow.size(); j = j + (side - 1)) {
            rDiagonalSum += tempCurrentRow.get(j);
        }

        if (rDiagonalSum > magicSum) {
            return true;
        }


        return false;
    }


}
