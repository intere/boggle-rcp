package com.intere.rcp.boggle.core.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import com.intere.rcp.boggle.core.util.BoardUtils;

/**
 * This class represents a Player.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Player {

    /** The ID of the player. */
    @XmlAttribute(name="id")
    private String playerId;

    /** The username of the player. */
    @XmlAttribute(name="username")
    private String username;

    /** is this player a computer player? */
    @XmlAttribute(name="computer")
    private boolean computer;

    /**
     * Default constructor; generates a UID for you.
     */
    public Player() {
        this(null);
    }

    /**
     * Constructor that sets the username and generates the UID.
     * 
     * @param playerId
     */
    public Player(String username) {
        this(username, BoardUtils.generateUid());
    }

    /**
     * Constructor that sets the username and UID.
     * 
     * @param username
     * @param playerId
     */
    Player(String username, String playerId) {
        this.playerId = playerId;
        this.username = username;
    }
    
    public abstract PairedList<String, Integer> getWordList();

    /**
     * Get the player's list of words.
     * 
     * @return
     */
    public List<String> getWords() {
        return getWordList().getKeyList();
    }

    /**
     * Clears the word list.
     */
    public void clearWordList() {
        getWordList().clear();
    }

    /**
     * Get the player ID.
     * 
     * @return
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * Set the username.
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the username.
     * 
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Is this Player a computer player?
     * 
     * @return
     */
    public boolean isComputer() {
        return computer;
    }

    /**
     * Set whether or not this is a computer player.
     * 
     * @param computer
     */
    public void setComputer(boolean computer) {
        this.computer = computer;
    }

    /**
     * Adds a word to the word list.
     * 
     * @param word
     */
    public void addWord(String word, int score) {
        getWordList().add(word, score);
    }

    /**
     * Get the score of a given word.
     * 
     * @param word
     * @return
     */
    public int getWordScore(String word) {
        return getWordList().get(word);
    }
}
