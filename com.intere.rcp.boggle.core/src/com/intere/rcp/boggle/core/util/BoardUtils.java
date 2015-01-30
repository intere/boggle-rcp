package com.intere.rcp.boggle.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.intere.rcp.boggle.core.BoggleCorePlugin;
import com.intere.rcp.boggle.core.model.Position;

/**
 * This class provides useful board utility methods.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public abstract class BoardUtils {

    /**
     * This method will generate you a random UID.
     * 
     * @return
     */
    public static String generateUid() {
        return UUID.randomUUID().toString();
    }

    /**
     * This method gives you the char at the provided indices.
     * 
     * @param board
     * @param i
     * @param j
     * @return
     */
    public static char getCharAt(String board, int i, int j) {
        int size = getBoardSize(board);
        return board.charAt((size * i) + j);
    }

    /**
     * This method tells you how big the board is (4 = 4x4, 5 = 5x5, etc).
     * 
     * @param board
     * @return
     */
    public static int getBoardSize(String board) {
        if (board != null) {
            return (int) Math.sqrt(board.length());
        }
        return 0;
    }

    /**
     * This method will provide you with the list of positions for a walk that
     * selects the provided word on the provided board.
     * 
     * @param board
     * @param word
     * @return
     */
    public static List<Position> getPath(String board, String word) {
        List<Position> walk = new ArrayList<Position>();

        board = board.toLowerCase();
        word = word.toLowerCase().replaceAll("qu", "q");

        getPath(board, word, walk, 0);

        return walk;
    }
    
    /**
     * This method performs a rotation of the board.
     * @param board
     * @return
     */
    public static String rotate(String board)
    {
        int size = getBoardSize(board);
        StringBuffer rotation = new StringBuffer();
        
        for(int j=0;j<size; j++)
        {
            for(int i=size-1;i>=0;--i)
            {
                rotation.append(getCharAt(board, i, j));
            }
        }
        
        return rotation.toString();
    }
    
    /**
     * Builds you a string from your walk.
     * @param walk
     * @param board
     * @return
     */
    public static String getWord(List<Position> walk, String board) {
        StringBuffer text = new StringBuffer();
        
        for(Position pos : walk) {
            char c = getCharAt(board, pos.getI(), pos.getJ());
            if(c=='q') {
                text.append("qu");
            } else {
                text.append(c);
            }
        }
        
        return text.toString();
    }

    /**
     * This method is a recursive helper method that will build the walk.
     * 
     * @param board
     * @param word
     * @param walk
     * @param position
     * @return
     */
    private static boolean getPath(String board, String word, List<Position> walk, int position) {

        boolean valid = false;

        try {
            int size = getBoardSize(board);

            if (position == word.length()) {
                return true;
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (word.charAt(position) == getCharAt(board, i, j)) {
                        Position p = new Position(i, j);
                        if (!walk.contains(p)) {
                            if (walk.size() == 0 || isValidStep(board, walk.get(walk.size() - 1), p)) {

                                walk.add(p);

                                valid = getPath(board, word, walk, position + 1);

                                if (valid && position == word.length() - 1) {
                                    return true;
                                } else if (!valid) {
                                    walk.remove(walk.size() - 1);
                                } else if (walk.size() == word.length()) {
                                    return valid;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            BoggleCorePlugin.getDefault().logErrorMessage("Error getting path", ex);
        }

        return valid;
    }

    /**
     * This method will tell you if the provided data evaluates to a valid step.
     * First the character at the provided position in the provided word is
     * checked against the "to" position on the provided board. If this
     * validates, then it we check to see if the provided step is a valid step
     * (1 away).
     * 
     * @param board
     * @param word
     * @param from
     * @param to
     * @param position
     * @return
     */
    public static boolean isValidStep(String board, Position from, Position to) {
        int di = Math.abs(from.getI() - to.getI());
        int dj = Math.abs(from.getJ() - to.getJ());

        return (di == 0 && dj == 1) || (di == 1 && dj == 0) || (di == 1 && dj == 1);
    }
}
