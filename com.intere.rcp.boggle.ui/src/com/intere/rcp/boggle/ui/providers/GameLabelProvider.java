package com.intere.rcp.boggle.ui.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.intere.rcp.boggle.core.model.Game;

/**
 * The Game Label provider (for rendering the game in a table).
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class GameLabelProvider implements ITableLabelProvider {

    List<ILabelProviderListener> listeners = new ArrayList<ILabelProviderListener>();

    public void addListener(ILabelProviderListener listener) {
        listeners.add(listener);
    }

    public void dispose() {

    }

    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    public void removeListener(ILabelProviderListener listener) {
        listeners.remove(listener);
    }

    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    /**
     * Get the text for the column.
     */
    public String getColumnText(Object element, int columnIndex) {
        if (element instanceof Game) {
            Game game = (Game) element;

            switch (columnIndex) {
            case 0: {
                return game.getCreator().getUsername();
            }

            case 1: {
                return String.valueOf(game.getGameType().getBoardSize()) + "x" + String.valueOf(game.getGameType().getBoardSize());
            }

            case 2: {
                return game.getGameState().name();
            }

            case 3: {
                return String.valueOf(game.getPlayerList().size());
            }

            case 4: {
                return String.valueOf(game.getTime());
            }

            }
        }
        return null;
    }
}
