package com.intere.rcp.boggle.ui.views;


import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.intere.rcp.boggle.core.event.IModelListener;
import com.intere.rcp.boggle.core.event.ModelEvent;
import com.intere.rcp.boggle.ui.controllers.GameManager;
import com.intere.rcp.boggle.ui.providers.GameContentProvider;
import com.intere.rcp.boggle.ui.providers.GameLabelProvider;

/**
 * This class provides the list of Games.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class GameView extends ViewPart implements IModelListener {
    
    public static final String ID = "com.intere.rcp.boggle.ui.views.GameView";

    /** The table viewer of games.  */
    private TableViewer viewer;
    
    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new GridLayout(1,true));
        viewer = new TableViewer(parent, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
        new TableColumn(viewer.getTable(), SWT.NONE).setText("Owner");
        new TableColumn(viewer.getTable(), SWT.NONE).setText("Game Type");
        new TableColumn(viewer.getTable(), SWT.NONE).setText("State");
        new TableColumn(viewer.getTable(), SWT.NONE).setText("Players");
        new TableColumn(viewer.getTable(), SWT.NONE).setText("Time");
        viewer.getTable().setHeaderVisible(true);
        
        viewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        for(TableColumn col : viewer.getTable().getColumns()) {
            col.setWidth(100);
        }
        
        viewer.setContentProvider(new GameContentProvider());
        viewer.setLabelProvider(new GameLabelProvider());
        viewer.setInput(GameManager.getInstance().getGameList());
        
        GameManager.getInstance().addModelListener(this);
    }

    @Override
    public void setFocus() {
        viewer.refresh();
    }

    public void onModelEvent(ModelEvent event) {
        if(event.getModel()==GameManager.getInstance().getGameList()) {
            if(PlatformUI.getWorkbench().getDisplay()!=null&&!PlatformUI.getWorkbench().getDisplay().isDisposed())
            {
                PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                    public void run() {
                        if(!viewer.getTable().isDisposed()) {
                            viewer.refresh();
                        }
                    }
                });
            }
        }        
    }

}
