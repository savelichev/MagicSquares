package net.savelichev.magicSquare;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Writes square into file or console.
 */
public class SquareWriter {


    /**
     * File for results.
     */
    private File file = new File("result.txt");

    /**
     * Writer of results.
     */
    private FileWriter fileWriter = new FileWriter(file, false);

    public SquareWriter() throws IOException {
    }

    /**
     * Write square into file.
     *
     * @param magicSquare target to write.
     * @throws IOException
     */
    public synchronized void writeToFile(MagicSquare magicSquare) throws IOException {


        for (int i = 0; i < magicSquare.getSIDE(); i++) {
            for (int j = 0; j < magicSquare.getSIDE(); j++) {
                int c = magicSquare.getSquare()[i][j];
                fileWriter.append(String.valueOf(c));
                fileWriter.append(" ");
            }
            fileWriter.append("\n");
        }
        fileWriter.append("\n");
        fileWriter.flush();
    }


    /**
     * Write square into console.
     *
     * @param magicSquare
     * @throws IOException
     */
    public synchronized void writeToConsole(MagicSquare magicSquare) throws IOException {

        for (int i = 0; i < magicSquare.getSIDE(); i++) {
            for (int j = 0; j < magicSquare.getSIDE(); j++) {
                System.out.print(magicSquare.getSquare()[i][j] + " ");
                int c = magicSquare.getSquare()[i][j];
            }
            System.out.println();
        }
        System.out.println();
    }

}
