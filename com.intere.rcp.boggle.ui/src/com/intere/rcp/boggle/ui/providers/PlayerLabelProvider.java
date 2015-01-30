package com.intere.rcp.boggle.ui.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.ui.BoggleUIPlugin;

/**
 * Label provider for the Player Table.
 *
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class PlayerLabelProvider implements ITableLabelProvider {

    /** The User Image.  */
    public static final Image USER_IMAGE = loadImage("/icons/presence_online.png");

    /** The Computer Image.  */
    public static final Image COMP_IMAGE = loadImage("/icons/system.png");

    /** The listeners. */
    private List<ILabelProviderListener> listeners = new ArrayList<ILabelProviderListener>();

    /**
     * Get the image for the player.
     */
    public Image getColumnImage(Object element, int columnIndex) {

        if (columnIndex == 0 && element instanceof Player) {
            if (((Player) element).isComputer()) {
                return COMP_IMAGE;
            } else {
                return USER_IMAGE;
            }
        }

        return null;
    }

    /**
     * Get the text for the player.
     */
    public String getColumnText(Object element, int columnIndex) {

        if (columnIndex == 1 && element instanceof Player) {
            return ((Player) element).getUsername();
        }

        return null;
    }

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

    private static Image loadImage(String url) {
        return BoggleUIPlugin.getImageDescriptor(url).createImage();
    }

}
