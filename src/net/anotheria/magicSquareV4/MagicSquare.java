package net.anotheria.magicSquareV4;


import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class MagicSquare {

    /*Square side size*/
    private int SIDE;

    /*Square elements*/
    private int[][] square;

    /*Used elements in the square*/
    private List<Integer> usedElements;


    public MagicSquare(int side) {
        SIDE = side;
        square = new int[side][side];
        usedElements = new ArrayList<>();
    }

    public int[][] getSquare() {
        return square;
    }

    public List<Integer> getUsedElements() {
        return usedElements;
    }

    public int getSIDE() {
        return SIDE;
    }

}
