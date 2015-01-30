package com.intere.rcp.boggle.ui.providers;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.intere.rcp.boggle.core.model.Game;

/**
 * The content provider for a boggle {@link Game}.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class ScoreContentProvider implements IStructuredContentProvider {

    public Object[] getElements(Object inputElement) {
        
        if(inputElement instanceof Game)
        {
            return ((Game)inputElement).getScores().toArray();            
        }
        
        return null;
    }

    public void dispose() {
        
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if(viewer!=null)
        {
            viewer.refresh();
        }
    }

}
