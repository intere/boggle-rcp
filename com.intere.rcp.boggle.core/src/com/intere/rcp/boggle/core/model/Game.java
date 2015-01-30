package com.intere.rcp.boggle.core.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.intere.rcp.boggle.core.managers.SpellCheckService;
import com.intere.rcp.boggle.core.model.jaxb.PlayerPairedListAdapter;
import com.intere.rcp.boggle.core.model.jaxb.ScoreSetMapAdapter;
import com.intere.rcp.boggle.core.util.BoardUtils;

/**
 * This class represents the Game; it contains the meta information about the
 * game.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
@XmlRootElement(name="game")
@XmlAccessorType(XmlAccessType.NONE)
public class Game {

    /** The ID of the game. */
    @XmlAttribute(name="game-id")
    private String gameId = BoardUtils.generateUid();

    /** The game creator. */
    @XmlElement(name="creator")
    private Player creator;

    /** The list of players that are in this game. */
    @XmlElement(name="players")
    @XmlJavaTypeAdapter(PlayerPairedListAdapter.class)
    private PairedList<String, Player> players = new PairedList<String, Player>();

    /** The current game score. */
    @XmlElement(name="scores")
    private PairedList<String, Score> scores = new PairedList<String, Score>();

    /** The length of time of the game. */
    @XmlElement(name="game-time")
    private int time = -1;

    /** The "type" of game (New 4x4, Old 4x4, 5x5, etc). */
    @XmlElement(name="game-type")
    private BoggleDice gameType;

    /** The current state of the game. */
    @XmlElement(name="game-state")
    private GameState gameState = GameState.Created;

    /** The board. */
    @XmlElement(name="boggle-board")
    private String board;

    /** The set being used to score the game. */
    @XmlElement(name="score-set")
    @XmlJavaTypeAdapter(ScoreSetMapAdapter.class)
    private ScoreSet scoringSet;

    @XmlElement(name="stats")
    private Stat stats;

    /**
     * Enumeration for the state of the game.
     */
    @XmlEnum
    public enum GameState {
        Created, WaitingForPlayers, InProgress, Ended
    }

    public String getGameId() {
        return gameId;
    }
    
    public PairedList<String, Player> getPlayers() {
        return players;
    }
    
    public List<Player> getPlayerList() {
        return players.getList();
    }

    public int getTime() {
        return time;
    }

    public BoggleDice getGameType() {
        return gameType;
    }

    public Player getCreator() {
        return creator;
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<Score> getScores() {
        return scores.getList();
    }

    public void setCreator(Player creator) {
        this.creator = creator;
        players.add(creator.getPlayerId(), creator);
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setGameType(BoggleDice gameType) {
        this.gameType = gameType;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getBoard() {
        return board;
    }

    public void setScoringSet(ScoreSet scoringSet) {
        this.scoringSet = scoringSet;
    }

    public ScoreSet getScoringSet() {
        return scoringSet;
    }

    /**
     * Lazy initializing getter for the Score (related to the provided user id).
     * 
     * @param userId
     * @return
     */
    public Score getScore(String userId) {
        Score score = scores.get(userId);

        if (score == null) {
            score = new Score(userId, 0);
            scores.add(userId, score);
        }

        return score;
    }

    /**
     * This method guesses a word against the gameboard.
     * 
     * @param userId
     * @param word
     */
    public boolean guessWord(String userId, String word) {

        // Is the word on the board?
        boolean valid = getTime() > 0;

        valid = valid && BoardUtils.getPath(board, word).size() > 0;

        // is the word in the dictionary?
        valid = valid && SpellCheckService.getInstance().spellCheck(word);

        if (valid) {

            Player p = players.get(userId);

            // Has the user guessed this word yet?
            valid = valid && !p.getWordList().containsKey(word);

            if (valid) {
                getScore(userId).addScore(scoringSet.scoreWord(word));
            }
        }

        return valid;
    }
    
    public Stat getStats() {
        return stats;
    }

    public void setStats(Stat stats) {
        this.stats = stats;
    }
}
