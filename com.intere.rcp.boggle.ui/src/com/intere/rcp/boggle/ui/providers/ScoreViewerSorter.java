package com.intere.rcp.boggle.ui.providers;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import com.intere.rcp.boggle.core.model.Score;

public class ScoreViewerSorter extends ViewerSorter {
    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        
        if(e1 instanceof Score && e2 instanceof Score) {
            Score s1 = (Score)e1;
            Score s2 = (Score)e2;
            
            return s2.getScore()-s1.getScore();
        }
        
        return super.compare(viewer, e1, e2);
    }
}
