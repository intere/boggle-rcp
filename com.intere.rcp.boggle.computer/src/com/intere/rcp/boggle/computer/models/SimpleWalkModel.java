package com.intere.rcp.boggle.computer.models;

import java.util.ArrayList;
import java.util.List;

import com.intere.rcp.boggle.core.model.Position;

/**
 * Simple model that is used to maintain the state of the Simple Walk producer.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class SimpleWalkModel {

    /** The length of the board. */
    private int length;

    /** The list of word lengths to use. */
    private List<Integer> lengths = new ArrayList<Integer>();

    /** The list of starting (algorithm seeding) positions. */
    private List<Position> startPositions = new ArrayList<Position>();

    /** The current word length that we're using. */
    private Integer currentLength;

    /** The current algorithm seeding position that we're using. */
    private Position currentPosition;

    /**
     * Constructor that sets the word length.
     * 
     * @param length
     */
    public SimpleWalkModel(int length) {
        this.length = length;

        for (int i = 2; i < length + 1; i++) {
            lengths.add(i);
        }
    }

    /**
     * This method will randomize the internal state of the Simple Walk Model.
     */
    public void randomizeState() {
        if (startPositions.size() == 0) {
            setCurrentLength(getRandomLength());
            reinitializePositions();
        }

        setCurrentPosition(getRandomPosition());
    }

    /**
     * This method will reinitilize the positions using the length of the board.
     */
    protected void reinitializePositions() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                startPositions.add(new Position(i, j));
            }
        }
    }

    /**
     * Getter for the current length.
     * 
     * @return
     */
    public Integer getCurrentLength() {
        return currentLength;
    }

    /**
     * Setter for the current length.
     * 
     * @param currentLength
     */
    public void setCurrentLength(Integer currentLength) {
        this.currentLength = currentLength;
    }

    /**
     * Getter for the current position.
     * 
     * @return
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Setter for the current position.
     * 
     * @param currentPosition
     */
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * Get a "random" length from the list of lengths. This length is removed;
     * so that it is no longer a choice from the list.
     * 
     * @return
     */
    private Integer getRandomLength() {
        return lengths.remove((int) (Math.random() * lengths.size()));
    }

    /**
     * Get a "random" starting position from the list of start positions. This
     * position is removed so that it is no longer a choice from the list.
     * 
     * @return
     */
    private Position getRandomPosition() {
        return startPositions.remove((int) (Math.random() * startPositions.size()));
    }

}
