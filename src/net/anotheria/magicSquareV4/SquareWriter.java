package net.anotheria.magicSquareV4;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SquareWriter {

    private File file = new File("result.txt");
    private FileWriter fileWriter = new FileWriter(file, false);
    int counter;

    public SquareWriter() throws IOException {
    }

    public synchronized void writeResult(MagicSquare4 magicSquare4) throws IOException {

        System.out.println(++counter);
        for (int i = 0; i < magicSquare4.getSIDE(); i++) {
            for (int j = 0; j < magicSquare4.getSIDE(); j++) {
                System.out.print(magicSquare4.getRow()[i][j] + " ");
                int c = magicSquare4.getRow()[i][j];
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
