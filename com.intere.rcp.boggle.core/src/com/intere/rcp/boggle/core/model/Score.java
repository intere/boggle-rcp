package com.intere.rcp.boggle.core.model;

/**
 * This class is a model class that represents a Score.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class Score {

    /** The User ID that the score correlates to. */
    private String userId;

    /** The User's Score. */
    private int score;

    /**
     * Constructor to set the userId, Score.
     * 
     * @param userId
     * @param score
     */
    public Score(String userId, int score) {
        this.userId = userId;
        this.score = score;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }

    /**
     * Adds the score to the internal score.
     * 
     * @param wordScore
     */
    public void addScore(int wordScore) {
        score += wordScore;
    }
}
