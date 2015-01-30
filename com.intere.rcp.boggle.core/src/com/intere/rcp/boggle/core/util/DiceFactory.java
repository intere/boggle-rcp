package com.intere.rcp.boggle.core.util;

import java.io.IOException;
import java.io.InputStream;

import com.intere.rcp.boggle.core.model.BoggleDice;

/**
 * 
 * This class will generate dice for you.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class DiceFactory {
    private static final String NEWLINE = "\n";

    public static final String RESOURCES = "/resources/";

    public enum Board {
        OldFourByFour("old4x4.txt"), NewFourByFour("new4x4.txt"), FiveByFive("5x5.txt"), SixBySix("6x6.txt");

        private String filename;

        private Board(String filename) {
            this.filename = filename;
        }
    }

    /**
     * Factory creation method to create a {@link BoggleDice} object.
     * 
     * @param board
     * @return
     * @throws IOException
     */
    public static BoggleDice createGameBoard(Board board) throws IOException {
        return createGameBoard(RESOURCES + board.filename);
    }

    /**
     * Factory creation method that creates a {@link BoggleDice} object from the
     * provided resource name.
     * 
     * @param resource
     * @return
     * @throws IOException
     */
    public static BoggleDice createGameBoard(String resource) throws IOException {
        return new BoggleDice(parseFile(resource));
    }

    /**
     * Factory creation method that creates a {@link BoggleDice} object from the
     * provided {@link InputStream}.
     * 
     * @param in
     * @return
     * @throws IOException
     */
    public static BoggleDice createGameBoard(InputStream in) throws IOException {
        return new BoggleDice(parseStream(in));
    }

    /**
     * Factory creation method that creates a {@link BoggleDice} object form the
     * provided {@link StringBuffer}.
     * 
     * @param buff
     * @return
     */
    public static BoggleDice createGameBoard(StringBuffer buff) {
        return new BoggleDice(parseBuffer(buff));
    }

    /**
     * Helper method to parse the provided {@link InputStream}.
     * 
     * @param in
     * @return
     * @throws IOException
     */
    protected static String[] parseStream(InputStream in) throws IOException {
        StringBuffer buff = FileReader.readStream(in);

        return parseBuffer(buff);
    }

    /**
     * Helper method that reads the provided filename
     * 
     * @param filename
     * @return
     * @throws IOException
     */
    protected static String[] parseFile(String filename) throws IOException {
        StringBuffer buff = FileReader.readBundleFile(filename);

        return parseBuffer(buff);
    }

    /**
     * Helper method that parses the provided {@link StringBuffer} object.
     * 
     * @param buff
     * @return
     */
    protected static String[] parseBuffer(StringBuffer buff) {
        if (buff.length() > 0) {
            return buff.toString().split(NEWLINE);
        }

        return new String[] {};
    }

}
