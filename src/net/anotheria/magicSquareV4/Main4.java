package net.anotheria.magicSquareV4;


import java.io.IOException;

public class Main4 {


    public static void main(String[] args) {

        int squareSide = 4;
        SquareWriter squareWriter = null;


        try {
            squareWriter = new SquareWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < squareSide*squareSide; i+=squareSide) {
            try {
                new Thread(new MagicSolver4(squareSide, i, i+squareSide, squareWriter)).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        try {
//            MagicSolver4 magicSolver4 = new MagicSolver4(5);
//            magicSolver4.completeRow(0, 0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}




