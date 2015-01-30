package com.intere.rcp.boggle.core.managers;

import java.io.IOException;

import com.intere.rcp.boggle.core.util.FileReader;

/**
 * This class is a singleton that is responsible for keeping track of a list of
 * "names" for you. The names are randomly assigned to the computer players.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class NameManager {

    private static final String PARSE_NEWLINE = "\n";

    /** Plugin variation of the file name. */
    private static final String OSGI_FILE_NAME = "/resources/names.txt";

    /** Classpath variation of the filename. */
    private static final String FILE_NAME = OSGI_FILE_NAME;

    /**
     * The singleton instance variable. This is the instance of the NameManager
     * that everyone gets.
     */
    private static NameManager instance;

    /** The list of names. */
    private String[] names;

    /** private constructor - no external instantiation. */
    private NameManager() {
        readNamesFromBundle();
    }

    /**
     * Singleton accessor method.
     * 
     * @return The NameManager instance.
     */
    public static synchronized NameManager getInstance() {
        if (instance == null) {
            instance = new NameManager();
        }
        return instance;
    }

    /**
     * This method is responsible for reading the filename. Note: if this method
     * can't load the filenames from the bundle, then it delegates to the
     * {@link #readNamesFromClasspath()} method to try and load it the
     * traditional java way - via the classpath.
     */
    private void readNamesFromBundle() {
        try {
            System.out.println("reading names (from bundle)");
            String nameBuffer = FileReader.readBundleFile(OSGI_FILE_NAME).toString();
            splitAndSetNames(nameBuffer);
            System.out.println("names read (from bundle)");
        } catch (IOException e) {
            System.out.println("Couldn't read names from bundle...");
            readNamesFromClasspath();
        }
    }

    /**
     * This method is responsible for reading the filename. This method uses the
     * traditional Java Classpath to try to load the resource rather than the
     * OSGI methodology like the {@link #readNamesFromBundle()} method.
     */
    private void readNamesFromClasspath() {
        try {
            System.out.println("reading names (from classpath)");
            String nameBuffer = FileReader.readClasspathFile(FILE_NAME).toString();
            splitAndSetNames(nameBuffer);
            System.out.println("read names (from classpath)");
        } catch (IOException e) {
            System.out.println("Couldn't read names from classpath...");
            e.printStackTrace();
        }
    }

    /**
     * This method is responsible for parsing the provided names.
     * 
     * @param fileContents
     */
    private void splitAndSetNames(String fileContents) {
        if (fileContents != null) {
            names = fileContents.split(PARSE_NEWLINE);
        }
    }

    /**
     * Get a random name from the Name Manager.
     * 
     * @return
     */
    public String getRandomName() {
        return names[(int) (Math.random() * names.length)];
    }
}
