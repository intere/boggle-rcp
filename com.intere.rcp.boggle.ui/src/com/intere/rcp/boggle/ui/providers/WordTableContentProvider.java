package com.intere.rcp.boggle.ui.providers;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.intere.rcp.boggle.core.model.Player;

/**
 * Content provider for the Word Table.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class WordTableContentProvider implements IStructuredContentProvider {

    public Object[] getElements(Object inputElement) {
        
        if(inputElement instanceof Player)
        {
            return ((Player)inputElement).getWords().toArray();
        } else if(inputElement instanceof List) {
            return ((List)inputElement).toArray();
        }

        return null;
    }

    public void dispose() {
        
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        viewer.refresh();
    }

}
