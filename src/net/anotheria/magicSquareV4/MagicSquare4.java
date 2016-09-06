package net.anotheria.magicSquareV4;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("Duplicates")
public class MagicSquare4 {


    private final int SIDE;


    private int[][] row;
    private List<Integer> takenElements;


    public MagicSquare4(int side) {
        this.SIDE = side;



        row = new int[side][side];

        takenElements = new ArrayList<>();
    }

    public MagicSquare4(MagicSquare4 magicSquare4) {
        this.SIDE = magicSquare4.getSIDE();


        this.row = Arrays.copyOf(magicSquare4.getRow(), magicSquare4.getRow().length);
        this.takenElements = new ArrayList<>(magicSquare4.getTakenElements());
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


    public void printRow() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                System.out.print(row[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
