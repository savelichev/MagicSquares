package net.savelichev.magicSquare;


import net.savelichev.magicSquare.hibernate.HibernateSessionFactory;
import net.savelichev.magicSquare.hibernate.MagicEntity;
import org.hibernate.Session;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

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
    public void writeToFile(MagicSquare magicSquare) throws IOException {


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
     * @throws IOException
     */
    public void writeToConsole(MagicSquare magicSquare) throws IOException {

        for (int i = 0; i < magicSquare.getSIDE(); i++) {
            for (int j = 0; j < magicSquare.getSIDE(); j++) {
                System.out.print(magicSquare.getSquare()[i][j] + " ");
                int c = magicSquare.getSquare()[i][j];
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Write square into database using Hibernate.
     *
     * @param magicSquare target to write.
     */
    public void writeToDatabase(MagicSquare magicSquare) {


        Session session = HibernateSessionFactory.getSession();

        session.beginTransaction();

        MagicEntity magicSquareEntity = new MagicEntity();

        StringBuilder result = new StringBuilder();
        for (int[] row : magicSquare.getSquare()) {
            result.append(Arrays.toString(row));
        }

        magicSquareEntity.setResult(result.toString());

        session.save(magicSquareEntity);
        session.getTransaction().commit();

        session.close();
    }

}
