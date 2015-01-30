package com.intere.rcp.boggle.ui.providers;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableColumn;

import com.intere.rcp.boggle.core.model.Observable;
import com.intere.rcp.boggle.core.model.descriptors.ComputerPlayerDescriptor;

/**
 * The Label provider for the Computer Player Descriptors.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class ComputerPlayerLabelProvider extends Observable<ILabelProviderListener> implements ITableLabelProvider {

    public enum Column {
        Name,
        Difficulty,
        Strategy
    }

    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    public String getColumnText(Object element, int columnIndex) {

        if (element instanceof ComputerPlayerDescriptor) {
            ComputerPlayerDescriptor desc = (ComputerPlayerDescriptor) element;
            
            if(columnIndex<Column.values().length)
            {
                switch(Column.values()[columnIndex]) {
                case Name: {
                    return desc.getName();
                }
                
                case Difficulty: {
                    return desc.getDifficulty();
                }
                
                case Strategy: {
                    return desc.getStrategy();
                }
                }
            }
        }

        return null;
    }

    public void addListener(ILabelProviderListener listener) {
        super.addListener(listener);
    }

    public void dispose() {
        
    }

    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    public void removeListener(ILabelProviderListener listener) {
        super.removeListener(listener);
    }
    
    public static void addColumns(TableViewer viewer) {
        for(Column col : Column.values()) {
            TableColumn c = new TableColumn(viewer.getTable(), SWT.NONE);
            c.setText(col.name());
            c.setWidth(100);
        }
    }

}
