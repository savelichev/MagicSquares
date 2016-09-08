package net.savelichev.magicSquare;


import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        int squareSide = 5;
        SquareWriter squareWriter = null;


        try {
            squareWriter = new SquareWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < squareSide*squareSide; i+=squareSide) {
            try {
                new Thread(new MagicSolver(squareSide, i, i+squareSide, squareWriter)).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        try {
//            MagicSolver magicSolver4 = new MagicSolver(5);
//            magicSolver4.completeRow(0, 0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}




