package com.intere.rcp.boggle.computer.producers;

import java.util.ArrayList;
import java.util.List;

import com.intere.rcp.boggle.computer.interfaces.IWordProducer;
import com.intere.rcp.boggle.computer.models.SimpleWalkModel;
import com.intere.rcp.boggle.core.event.IModelListener;
import com.intere.rcp.boggle.core.managers.SpellCheckService;
import com.intere.rcp.boggle.core.model.PairedList;
import com.intere.rcp.boggle.core.model.Position;
import com.intere.rcp.boggle.core.util.BoardUtils;

/**
 * This class is the implementation of the Simple Walk based algorithm
 * implementation of the Word Producer. Essentially it will (when asked) produce
 * words for you using the provided game board.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class SimpleWalkProducer implements IWordProducer {

    /** Structure of "all words" so that we can eliminate duplicate guesses. */
    private PairedList<String, String> globalWords = new PairedList<String, String>();

    /** The structure of the words to be provided to the consumer. */
    private PairedList<String, String> consumerWords = new PairedList<String, String>();

    /** The list of listeners to be notified when a word is made available. */
    private List<IModelListener> listeners = new ArrayList<IModelListener>();

    /** The length of the board. */
    private int boardLength;

    /** The Gameboard. */
    private String gameBoard;

    /** The "state" of the Simple Walk Producer. */
    private SimpleWalkModel model;

    /**
     * Constructor that creates the Simple Walk Producer using the provided game
     * board.
     * 
     * @param gameBoard
     */
    public SimpleWalkProducer(String gameBoard) {
        this.gameBoard = gameBoard;
        this.boardLength = BoardUtils.getBoardSize(gameBoard);
        model = new SimpleWalkModel(boardLength);
    }

    /**
     * Get the list of consumer words.
     */
    public PairedList<String, String> getWords() {
        return consumerWords;
    }

    /**
     * This method is responsible for generating the words from the simple walk
     * algorithm.
     */
    public void produceWords() {
        model.randomizeState();

        List<Position> walk = new ArrayList<Position>();
        walk.add(model.getCurrentPosition());

        helpBuildSampleList(model.getCurrentLength(), walk);
        
    }

    /**
     * This method is the algorithm implementation for the Simple Walk
     * Algorithm.
     * 
     * @param sampleLength
     * @param walk
     */
    private void helpBuildSampleList(int sampleLength, List<Position> walk) {
        if (walk.size() == sampleLength) {
            String word = BoardUtils.getWord(walk, gameBoard);
            List<String> suggest = SpellCheckService.getInstance().getSuggestions(word, 5);
            for (String sug : suggest) {
                if (!globalWords.containsKey(sug)) {
                    consumerWords.add(sug, sug);
                }
            }

        } else {
            for (int i = 0; i < boardLength; i++) {
                for (int j = 0; j < boardLength; j++) {
                    Position pos = new Position(i, j);
                    if (BoardUtils.isValidStep(gameBoard, walk.get(walk.size() - 1), pos)) {
                        walk.add(pos);
                        helpBuildSampleList(sampleLength, walk);
                        walk.remove(pos);
                    }
                }
            }
        }
    }

    /**
     * Adds a listener to the list of listeners.
     */
    public void addListener(IModelListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a listener from the list of listeners.
     */
    public void removeListener(IModelListener listener) {
        listeners.remove(listener);
    }

}
