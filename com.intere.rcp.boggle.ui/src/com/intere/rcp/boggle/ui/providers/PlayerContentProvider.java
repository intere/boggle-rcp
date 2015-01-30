package com.intere.rcp.boggle.ui.providers;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.PairedList;
import com.intere.rcp.boggle.core.model.Player;

/**
 * This is the JFace adapter between the Player and the Table.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class PlayerContentProvider implements IStructuredContentProvider {

    @SuppressWarnings("unchecked")
    public Object[] getElements(Object inputElement) {

        if (inputElement instanceof List) {
            List list = (List) inputElement;
            if (list.size() > 0 && list.get(0) instanceof Player) {
                return list.toArray();
            }
        } else if(inputElement instanceof Game) {
            return ((Game)inputElement).getPlayerList().toArray();
        }

        return new Object[] {};
    }

    public void dispose() {

    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if(newInput!=null) {
            viewer.refresh();
        }
    }

}
