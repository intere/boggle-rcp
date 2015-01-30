package com.intere.rcp.boggle.ui.providers;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.intere.rcp.boggle.core.model.PairedList;

/**
 * Content Provider for a structure of Computer Player Content.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class ComputerPlayerContentProvider implements IStructuredContentProvider {

    public Object[] getElements(Object inputElement) {
        
        if(inputElement instanceof PairedList) {
            return ((PairedList)inputElement).getList().toArray();
        }
        
        return null;
    }

    public void dispose() {
        // TODO Auto-generated method stub

    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        viewer.refresh();
    }

}
