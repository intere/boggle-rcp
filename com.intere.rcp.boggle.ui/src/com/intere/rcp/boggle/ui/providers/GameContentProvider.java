package com.intere.rcp.boggle.ui.providers;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.intere.rcp.boggle.core.model.Game;

public class GameContentProvider implements IStructuredContentProvider {

    public void dispose() {
        // TODO Auto-generated method stub

    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if(newInput!=null) {
            viewer.refresh();
        }
    }

    public Object[] getElements(Object inputElement) {
        if(inputElement instanceof List) {
            List list = (List)inputElement;
            if(list.size()>0 && list.get(0) instanceof Game) {
                return list.toArray();
            }
        }
        return new Object[] {};
    }

}
