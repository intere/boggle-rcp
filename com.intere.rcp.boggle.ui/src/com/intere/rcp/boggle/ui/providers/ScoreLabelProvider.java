package com.intere.rcp.boggle.ui.providers;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.intere.rcp.boggle.core.model.Observable;
import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.core.model.Score;
import com.intere.rcp.boggle.ui.controllers.PlayerManager;

/**
 * The label provider for the Score Table.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class ScoreLabelProvider extends Observable<ILabelProviderListener> implements ITableLabelProvider {

    /**
     * Get the image for the provided player (if the column is correct).
     */
    public Image getColumnImage(Object element, int columnIndex) {
        if (element instanceof Score) {
            if (columnIndex == 0) {
                Player p = PlayerManager.getInstance().getPlayer(((Score) element).getUserId());
                if (p.isComputer()) {
                    return PlayerLabelProvider.COMP_IMAGE;
                } else {
                    return PlayerLabelProvider.USER_IMAGE;
                }
            }
        }
        return null;
    }

    /**
     * Get the text for the appropriate column (if the provided object is a
     * {@link Score} object.
     */
    public String getColumnText(Object element, int columnIndex) {

        if (element instanceof Score) {
            Score score = (Score) element;

            switch (columnIndex) {
            case 1: {
                Player p = PlayerManager.getInstance().getPlayer(score.getUserId());
                if(p.isComputer()) {
                    return p.getUsername();
                } else {
                    return p.getUsername();
                }
            }

            case 2: {
                return String.valueOf(score.getScore());
            }
            }
        }

        return null;
    }

    public void addListener(ILabelProviderListener listener) {
        super.addListener(listener);
    }

    public void dispose() {
        for (ILabelProviderListener listener : getListeners()) {
            removeListener(listener);
        }
    }

    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    public void removeListener(ILabelProviderListener listener) {
        super.removeListener(listener);
    }

}
