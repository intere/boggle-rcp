package com.intere.rcp.boggle.ui.providers;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.intere.rcp.boggle.core.model.Observable;
import com.intere.rcp.boggle.core.model.ScoreSet;
import com.intere.rcp.boggle.ui.controllers.PlayerManager;

/**
 * Label provider for the word list.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class WordListLabelProvider extends Observable<ILabelProviderListener> implements ITableLabelProvider {

    private ScoreSet set;
    
    /**
     * Default constructor.
     */
    public WordListLabelProvider() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Constructor that sets the Scoring Set.
     * @param set
     */
    public WordListLabelProvider(ScoreSet set) {
        this.set = set;
    }
    
    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    public String getColumnText(Object element, int columnIndex) {
        
        if(element instanceof String)
        {
            switch(columnIndex)
            {
            case 0:
            {
                return (String)element;
            }
            
            case 1:
            {
                if(set==null) {
                    return String.valueOf(PlayerManager.getInstance().getPlayer().getWordScore((String)element));
                } else {
                    return String.valueOf(set.scoreWord(element.toString()));
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

}
