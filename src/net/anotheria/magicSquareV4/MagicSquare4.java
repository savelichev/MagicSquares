package net.anotheria.magicSquareV4;


import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class MagicSquare4 {


    private int SIDE;
    private int[][] row;
    private List<Integer> takenElements;


    public MagicSquare4(int side) {
        SIDE = side;
        row = new int[side][side];
        takenElements = new ArrayList<>();
    }

    public int[][] getRow() {
        return row;
    }

    public List<Integer> getTakenElements() {
        return takenElements;
    }

    public int getSIDE() {
        return SIDE;
    }

}
