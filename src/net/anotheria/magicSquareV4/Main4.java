package net.anotheria.magicSquareV4;


import java.io.IOException;

public class Main4 {
    public static void main(String[] args) {

        MagicSolver4 magicSolver4 = null;
        try {
            magicSolver4 = new MagicSolver4(5);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (magicSolver4 != null) {
            magicSolver4.completeRow(0,0);
        }
    }


}
