package net.anotheria.magicSquareV4;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("Duplicates")
public class MagicSquare4 {


    private final int SIDE;
    private final int MAGIC_SUM;

    private int[][] row;
    private List<Integer> takenElements;


    public MagicSquare4(int side) {
        this.SIDE = side;
        MAGIC_SUM = (side * side * side + side) / 2;


        row = new int[side][side];

        takenElements = new ArrayList<>();
    }



    public int getSIDE() {
        return SIDE;
    }


    public int[][] getRow() {
        return row;
    }

    public void setRow(int[][] row) {
        this.row = row;
    }

    public List<Integer> getTakenElements() {
        return takenElements;
    }

    public void setTakenElements(List<Integer> takenElements) {
        this.takenElements = takenElements;
    }

    public int getMAGIC_SUM() {
        return MAGIC_SUM;
    }

    public void printRow() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                System.out.print(row[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getColumnSum(int y) {

        int columnSum = 0;
        for (int x = 0; x < SIDE; x++) {
            columnSum += row[x][y];
        }

        return columnSum;
    }


    public int getRowSum(int x) {
        int rowSum = 0;
        for (int y = 0; y < SIDE; y++) {
            rowSum += row[x][y];
        }
        return rowSum;
    }


    public boolean isMagicDiagonals() {

        int lDiagonal = 0;
        int rDiagonal = 0;

        for (int i = 0; i < SIDE; i++) {
            lDiagonal += row[i][i];

        }
        if (lDiagonal != MAGIC_SUM) {
            return false;
        }

        for (int i = 0; i < SIDE; i++) {

            rDiagonal += row[i][(SIDE - 1) - i];
        }

        if(rDiagonal!=MAGIC_SUM){
            return false;
        }
        return true;

    }
}
