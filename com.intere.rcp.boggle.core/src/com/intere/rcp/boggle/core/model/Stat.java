package com.intere.rcp.boggle.core.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * The game statistics (after the game is over).
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class Stat {

    /** The Game ID. */
    @XmlAttribute(name="game-id")
    private String gameId;

    /** The data structure of players. */
    @XmlElement(name="players")
    private PairedList<String, Player> players = new PairedList<String, Player>();

    /** The data structure of words for each player. */
    @XmlElement(name="word-list")
    private PairedList<String, List<String>> playerWords = new PairedList<String, List<String>>();

    /**
     * Getter for the players.
     * 
     * @return
     */
    public PairedList<String, Player> getPlayers() {
        return players;
    }

    /**
     * Getter for the Game ID.
     * 
     * @return
     */
    public String getGameId() {
        return gameId;
    }

    /**
     * Getter for the Player Words.
     * 
     * @return
     */
    public PairedList<String, List<String>> getPlayerWords() {
        return playerWords;
    }

    /**
     * Creates the states from the provided game.
     * 
     * @param game
     * @return
     */
    public static Stat fromGame(Game game) {
        Stat stat = new Stat();
        stat.gameId = game.getGameId();

        for (Player p : game.getPlayerList()) {
            stat.players.add(p.getPlayerId(), p);
            stat.playerWords.add(p.getPlayerId(), p.getWords());
        }

        return stat;
    }
}
