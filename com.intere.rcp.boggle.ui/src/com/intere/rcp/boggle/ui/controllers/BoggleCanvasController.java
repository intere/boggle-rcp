package com.intere.rcp.boggle.ui.controllers;

import java.util.List;

import com.intere.rcp.boggle.core.model.Position;
import com.intere.rcp.boggle.core.util.BoardUtils;
import com.intere.rcp.boggle.ui.components.BoggleCanvas;

/**
 * This class is the controller behind the Boggle Canvas.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class BoggleCanvasController {

    /** The View. */
    private BoggleCanvas view;

    /** The Game Board. */
    private String board;

    /** The Current text. */
    private String walkText = "";

    /** The Current walk. */
    private List<Position> walk;

    /**
     * Constructor that sets the view.
     * 
     * @param view
     */
    public BoggleCanvasController(final BoggleCanvas view) {
        setView(view);
    }

    /**
     * This method tells you the size of the board (4 = 4x4, 5 = 5x5, etc).
     * 
     * @return
     */
    public int getBoardSize() {
        return BoardUtils.getBoardSize(board);
    }

    /**
     * This method gives you the char at the provided indices.
     * 
     * @param i
     * @param j
     * @return
     */
    protected char getCharAt(int i, int j) {
        return BoardUtils.getCharAt(board, i, j);
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getBoard() {
        return board;
    }

    public void setView(BoggleCanvas view) {
        this.view = view;
    }

    public BoggleCanvas getView() {
        return view;
    }

    public List<Position> getWalk() {
        return walk;
    }

    public String getWalkText() {
        return walkText;
    }

    public void setWalkText(String walkText) {
        this.walkText = walkText;
        walk = BoardUtils.getPath(board, walkText);
        view.getDisplay().asyncExec(new Runnable() {
            public void run() {
                view.redraw();
                view.update();
            }
        });
    }

    /**
     * Rotates the board -90 degrees.  Call 3 times to do a +90 rotation.
     */
    public void rotate() {
        board = BoardUtils.rotate(board);
        view.redraw();
        view.update();
        setWalkText(walkText);
    }
    
    /**
     * This method resets the walk (use for a new game - so we don't have the same "walk" highlighted as the prior game).
     */
    public void resetWalk()
    {
        if(this.walk!=null)
        {
            this.walk.clear();
        }
        view.redraw();
        view.update();
    }
}
