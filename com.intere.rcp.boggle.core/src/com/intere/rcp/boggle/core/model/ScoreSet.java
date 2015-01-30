package com.intere.rcp.boggle.core.model;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.intere.rcp.boggle.core.model.jaxb.ScoreSetMapAdapter;
import com.intere.rcp.boggle.core.util.FileReader;

/**
 * This class is a model that will provide you a score per character.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class ScoreSet {

    /** The Table that contains the score map. */
    private Map<Character, Integer> scoreMap = new Hashtable<Character, Integer>();

    /**
     * This method will score a word for you using the internal Score Map.
     * 
     * @param word
     * @return
     */
    public int scoreWord(String word) {
        int score = 0;
        int multiplier = getMultiplier(word);

        for (int i = 0; i < word.length(); i++) {
            score += scoreLetter(word.charAt(i));
        }
        
        score *= multiplier;

        return score;
    }

    /**
     * This method will give you a score multiplier based on the length of the word.
     * @param word
     * @return
     */
    protected int getMultiplier(String word) {
        int mult = 1;
        
        int len = word.length();
        
        if(len<5)
        {
            mult = 1;
        } else if(len>=5&&len<7)
        {
            mult = 3;
        } else if(len>=7&&len<9)
        {
            mult = 4;
        } else if(len>=9)
        {
            mult = 5;
        }
        
        return mult;
    }

    /**
     * Loads the Score Map using the provided Properties.
     * 
     * @param props
     */
    protected void loadFromProperties(Properties props) {
        for (char i = 'a'; i < 'z' + 1; i++) {
            String key = String.valueOf(i);

            scoreMap.put(key.charAt(0), Integer.valueOf(props.get(key).toString()));
        }
    }

    /**
     * This method scores each provided letter for you.
     * 
     * @param letter
     * @return
     */
    public int scoreLetter(char letter) {
        return scoreMap.get(letter);
    }

    /**
     * Factory creation method that will create your ScoreSet using the provided
     * bundle file name.
     * 
     * @param filename
     * @return
     * @throws IOException
     */
    public static ScoreSet fromBundleFile(String filename) throws IOException {
        ScoreSet set = new ScoreSet();
        set.loadFromProperties(FileReader.readBundlePropertyFile(filename));

        return set;
    }
    
    public Map<Character, Integer> getScoreMap() {
        return scoreMap;
    }
    
    public void setScoreMap(Map<Character, Integer> scoreMap) {
        this.scoreMap = scoreMap;
    }
}
