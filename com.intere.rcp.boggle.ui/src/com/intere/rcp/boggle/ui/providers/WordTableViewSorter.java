package com.intere.rcp.boggle.ui.providers;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

/**
 * The sorter for the Words.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class WordTableViewSorter extends ViewerSorter {

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        
        if(e1 instanceof String && e2 instanceof String) {
            return e1.toString().compareTo(e2.toString());
        }
        
        return super.compare(viewer, e1, e2);
    }
}
