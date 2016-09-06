package net.anotheria.magicsquarearray;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        MagicSquares3 magicSquares3 = new MagicSquares3(4);
        magicSquares3.start();
        System.out.println((System.currentTimeMillis()-start)/1000+"sec");
    }

}
