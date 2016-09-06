package net.anotheria.magicsquarearray;


import java.util.Arrays;


@SuppressWarnings("Duplicates")
public class MagicSquares3 {


    private final int side;
    private final int squareSize;
    private final int magicSum;

    private int counter;
    private int middleCounter;

    public MagicSquares3(int side) {
        this.side = side;
        squareSize = side * side;
        magicSum = (side * side * side + side) / 2;
    }


    public void start() {


        getSquares();

    }

    private void getSquares() {

        int[] array = new int[squareSize];

        for (int i = 1; i <= squareSize; i++) {
            array[0] = i;
            System.out.println("new entry");
            nextElement(array, 0);
        }

//        for (int i = 1; i <= squareSize; i++) {
//
//
//            array[0] = i;
//            nextElement(array, 0);
//        }

    }

    private void nextElement(int[] row, int index) {

//        System.out.println(Arrays.toString(row));
        if(index==squareSize-1){
            System.out.println(Arrays.toString(row));
        }
        if (index / side > 2 && (isMidColSumBig(row, index) || isMidDiagSumBig(row, index))) {
            return;
        }

        if (index == squareSize - 1 && isMagic(row)) {

            System.out.println(++counter);
            System.out.println(Arrays.toString(row));
        }

        if (index < squareSize - 1) {
            index++;
        }


        if ((index + 1) % side == 0) {

            int[] currentRow = Arrays.copyOf(row, row.length);
            if (trySetLastElement(currentRow, index)) {
                nextElement(currentRow, index);
            }
        } else {
            for (int i = 1; i <= row.length; i++) {

                if (rowHasElement(row, i)) {
                    continue;
                }

                int[] currentRow = Arrays.copyOf(row, row.length);
                currentRow[index] = i;
                nextElement(currentRow, index);
            }
        }


    }

    private boolean isMidDiagSumBig(int[] row, int index) {

        int lDiagonalSum = 0;
        int rDiagonalSum = 0;

        for (int j = 0; j <= index; j = j + side + 1) {
            lDiagonalSum += row[j];
        }

        if (lDiagonalSum > magicSum) {
            return true;
        }

        for (int j = side - 1; j < index; j = j + (side - 1)) {
            rDiagonalSum += row[j];
        }

        if (rDiagonalSum > magicSum) {
            return true;
        }

        return false;
    }

    private boolean isMidColSumBig(int[] row, int index) {

        for (int i = 0; i < side; i++) {
            int columnSum = 0;
            for (int j = 0; j < (index); j = j + side) {
                columnSum += row[i + j];
            }
            if (columnSum > magicSum) {
//                System.out.println(++middleCounter+" mid");
                return true;
            }
        }

        return false;
    }

    private boolean isMagic(int[] row) {

        if (!isDiagonalsVAlid(row)) {
            return false;
        }

        if (!isColumnsValid(row)) {
            return false;
        }


        return true;


    }

    private boolean isDiagonalsVAlid(int[] row) {

        int lDiagonal = 0;
        int rDiagonal = 0;

        for (int i = 0; i < squareSize; i += side + 1) {
            lDiagonal += row[i];
        }
        if (lDiagonal != magicSum) {
            return false;
        }

        for (int i = side - 1; i <= squareSize - side; i += side - 1) {
            rDiagonal += row[i];
        }
        if (rDiagonal != magicSum) {
            return false;
        }

        return true;
    }

    private boolean isColumnsValid(int[] row) {

        for (int i = 0; i < side; i++) {

            int columnSum = 0;

            for (int k = i; k < squareSize; k += side) {
                columnSum += row[k];
            }

            if (columnSum != magicSum) {
                return false;
            }
        }
        return true;
    }

    private boolean trySetLastElement(int[] row, int index) {

        int rowSum = 0;

        for (int i = index - index % side; i <= index; i++) {
            rowSum += row[i];
        }

        int difference = magicSum - rowSum;
        if (difference > squareSize || difference < 1) {
            return false;
        }
        if (rowHasElement(row, difference)) {
            return false;
        }

        row[index] = difference;

        return true;
    }

    private boolean validRows(int[] row, int index) {


        for (int i = 0; i < index / side; i += side) {
            int rowSum = 0;
            for (int j = i; j < side; j++) {

                rowSum += row[j];
            }
            if (rowSum > magicSum) {
                return false;
            }
        }

        return true;
    }


    private boolean rowHasElement(int[] currentRow, int i) {

        for (int element : currentRow) {
            if (element == i) {
                return true;
            }
        }
        return false;
    }

//    private int[] initArray(int firstElement) {
//
//        int[] array = new int[squareSize];
//
//        array[0] = firstElement;
//
//        int value = 1;
//
//        for (int i = 1; i < array.length; i++) {
//
//            if (i == array[0]) {
//                array[i] = ++value;
//            }
//            array[i] = value++;
//
//        }
//        System.out.println(Arrays.toString(array));
//        return array;
//    }

}
