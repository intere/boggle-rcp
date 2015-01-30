package com.intere.rcp.boggle.core.managers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.intere.rcp.boggle.core.BoggleCorePlugin;
import com.intere.rcp.boggle.core.util.FileReader;
import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.SpellChecker;

/**
 * This class will perform the spell checking for you.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class SpellCheckService {
    /**
     * The singleton instance variable. This is the instance of the SpellChecker
     * that everyone gets.
     */
    private static SpellCheckService instance;

    /** The spell checker. */
    private SpellChecker spellChecker;

    /** The word list. */
    private List<String> wordList = new ArrayList<String>();

    /** private constructor - no external instantiation. */
    private SpellCheckService() {
        Thread t = new Thread() {
            public void run() {
                // TODO - define an extension point so we can load "n" dictionaries...
                spellChecker = new SpellChecker();
                loadDictionary("/resources/eng_com.dic");
                loadDictionary("/resources/custom.dic");
            }
        };
        
        t.start();
    }

    /**
     * This method will spellcheck the provided word for you.
     * 
     * @param word
     * @return
     */
    public boolean spellCheck(String word) {
        return spellChecker.isCorrect(word);
    }

    /**
     * Singleton accessor method.
     * 
     * @return The SpellChecker instance.
     */
    public static synchronized SpellCheckService getInstance() {
        if (instance == null) {
            instance = new SpellCheckService();
        }
        return instance;
    }

    /**
     * This method loads the dictionary.
     * 
     * @param path
     */
    protected void loadDictionary(String path) {
        try {
            SpellDictionaryHashMap dict = new SpellDictionaryHashMap(new InputStreamReader(BoggleCorePlugin.getDefault().getBundle().getResource(path).openStream()));
            spellChecker.addDictionary(dict);

            InputStream in = BoggleCorePlugin.getDefault().getBundle().getResource(path).openStream();
            String[] elems = FileReader.readStream(in).toString().split("\n");
            in.close();

            for (String word : elems) {
                wordList.add(word);
            }

        } catch (IOException e) {
            BoggleCorePlugin.getDefault().logErrorMessage("Error trying to load dictionary", e);
        }
    }

    /**
     * Gives you a random word from the dictionary.
     * 
     * @return
     */
    public String getRandomWord() {
        int index = (int) (Math.random() * wordList.size());
        return wordList.get(index);
    }

    /**
     * Get suggestions from the spell checker.
     * 
     * @param word
     * @param count
     * @return
     */
    public List<String> getSuggestions(String word, int count) {

        List<Object> words = spellChecker.getSuggestions(word, count);

        List<String> retWords = new ArrayList<String>();

        for (Object w : words) {
            retWords.add(w.toString());
        }

        return retWords;
    }
}
