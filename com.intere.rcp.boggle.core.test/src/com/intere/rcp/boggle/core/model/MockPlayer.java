package com.intere.rcp.boggle.core.model;

/**
 * Mock implementation of a player.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class MockPlayer extends Player {
    
    private PairedList<String, Integer> wordList = new PairedList<String, Integer>();
    
    /**
     * Default constructor.
     */
    public MockPlayer() {
        super();
    }
    
    /**
     * Constructor that sets the username.
     * @param username
     */
    public MockPlayer(String username) {
        super(username);
    }

    @Override
    public PairedList<String, Integer> getWordList() {
        return wordList;
    }

}
