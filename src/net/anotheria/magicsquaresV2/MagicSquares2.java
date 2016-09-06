package net.anotheria.magicsquaresV2;


import net.anotheria.magicsquares.SquareWriter;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class MagicSquares2 {


    private final int side;
    private final int squareSize;
    private final int magicSum;

    private int counter;
    private int middleCounter;

    public MagicSquares2(int side) {
        this.side = side;
        squareSize = side * side;
        magicSum = (side * side * side + side) / 2;
    }

    private SquareWriter squareWriter = new SquareWriter();


    List<List<Integer>> compositions = new ArrayList<>();

    public List<List<Integer>> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<List<Integer>> compositions) {
        this.compositions = compositions;
    }


    public void start() {
        getAllCompositions();
        getAllMagicSquare();
    }


    public void getAllCompositions() {


        for (int i = 1; i <= squareSize; i++) {

            List<Integer> list = new ArrayList<>();
            list.add(i);
            getNext(list);
        }
        System.out.println(compositions.size());

    }

    private void getNext(List<Integer> row) {


        if (row.size() == side) {
            int sum = 0;
            for (int i : row) {
                sum += i;
            }
            if (sum == magicSum) {
                compositions.add(row);
                System.out.println(row);

            }
        }

        for (int i = 1; i <= squareSize; i++) {
            List<Integer> tempRow = new ArrayList<>(squareSize);
            tempRow.addAll(row);

            if (tempRow.size() < side && !tempRow.contains(i)) {
                tempRow.add(i);
                getNext(tempRow);
            }

        }
    }

    public void getAllMagicSquare() {

        for (List<Integer> composition : compositions) {

            getNextMG(composition, compositions);
        }

    }


    public void getNextMG(List<Integer> row, List<List<Integer>> possibleCompositions) {

//        if (row.size()==squareSize){
//            System.out.println(row);
//        }


        if (row.size() % side>=2 && isMidColumnSumVeryBig(row) ) {
            return;
        }
        if (row.size() % side>=2 && isMidDiagonalSumVeryBig(row)) {
            return;
        }



        if (row.size() == squareSize && isMagic(row)) {
            System.out.println(row);
            System.out.println(++counter);
            return;
        }


        List<List<Integer>> currentPossibleCompositions = filterPossibleCompositions(possibleCompositions, row);

//        System.out.println("before: " + possibleCompositions.size());
//        System.out.println("after: " + currentPossibleCompositions.size());
//        System.out.println(row);

        for (int i = 0; i < currentPossibleCompositions.size(); i++) {

            List<Integer> currentRow = new ArrayList<>(row.size()+side);
            currentRow.addAll(row);

            if (currentRow.size() < squareSize) {
                currentRow.addAll(currentPossibleCompositions.get(i));
                getNextMG(currentRow, currentPossibleCompositions);
            }


        }

    }

    private List<List<Integer>> filterPossibleCompositions(List<List<Integer>> possibleCompositions, List<Integer> row) {

        List<List<Integer>> newList = new ArrayList<>(squareSize);

        for (List<Integer> composition : possibleCompositions) {

            if (!hasDuplicates(composition, row)) {
                newList.add(composition);
            }
        }
        return newList;
    }


    public boolean hasDuplicates(List<Integer> row, List<Integer> otherRow) {

        for (int element : otherRow) {

            if (row.contains(element)) {
                return true;
            }
        }
        return false;
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


//    private boolean isValidRows(List<Integer> tempCurrentRow) {
//
//        for (int k = 0; k < tempCurrentRow.size(); k = k + side) {
//
//            int rowSum = 0;
//
//            for (int elem : tempCurrentRow.subList(k, k + side)) {
//                rowSum += elem;
//            }
//
//            if (rowSum != magicSum) {
//                return false;
//            }
//        }
//        return true;
//    }

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

            for (int j = 0; j < (tempCurrentRow.size()); j = j + side) {
                columnSum += tempCurrentRow.get(i + j);
            }

            if (columnSum > magicSum) {
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

        for (int j = side - 1; j < tempCurrentRow.size(); j = j + (side - 1)) {
            rDiagonalSum += tempCurrentRow.get(j);
        }

        if (rDiagonalSum > magicSum) {
            return true;
        }


        return false;
    }


}
