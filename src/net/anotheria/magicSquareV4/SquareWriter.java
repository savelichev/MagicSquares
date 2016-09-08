package net.anotheria.magicSquareV4;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SquareWriter {

    private File file = new File("result.txt");
    private FileWriter fileWriter = new FileWriter(file, false);
    private int counter;

    public SquareWriter() throws IOException {
    }

    public synchronized void writeResult(MagicSquare magicSquare) throws IOException {

        System.out.println(++counter);
        for (int i = 0; i < magicSquare.getSIDE(); i++) {
            for (int j = 0; j < magicSquare.getSIDE(); j++) {
                System.out.print(magicSquare.getSquare()[i][j] + " ");
                int c = magicSquare.getSquare()[i][j];
                fileWriter.append(String.valueOf(c));
                fileWriter.append(" ");
            }
            System.out.println();
            fileWriter.append("\n");
        }
        System.out.println();
        fileWriter.append("\n");
        fileWriter.flush();
    }

}
