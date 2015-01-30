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
import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.ui.controllers.PlayerManager;
import com.intere.rcp.boggle.ui.providers.PlayerContentProvider;
import com.intere.rcp.boggle.ui.providers.PlayerLabelProvider;

/**
 * This class provides the list of Players.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class PlayerView extends ViewPart implements IModelListener {
    
    public static final String ID = "com.intere.rcp.boggle.ui.views.PlayerView";

    /** The table Viewer of Players. */
    private TableViewer viewer;

    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new GridLayout(1,true));
        
        viewer = new TableViewer(parent, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL );
        new TableColumn(viewer.getTable(), SWT.NONE).setText("Type");
        new TableColumn(viewer.getTable(), SWT.NONE).setText("Username");
        viewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true));

        viewer.setContentProvider(new PlayerContentProvider());
        viewer.setLabelProvider(new PlayerLabelProvider());        
        viewer.setInput(PlayerManager.getInstance().getPlayerList());
        
        PlayerManager.getInstance().addModelListener(this);
        updateTable();
    }

    @Override
    public void setFocus() {
        viewer.refresh();
    }
    
    /**
     * Model Listener.
     */
    public void onModelEvent(ModelEvent event) {
        if(event.getModel()==PlayerManager.getInstance().getPlayerList()) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    updateTable();
                }
            });
        }
    }
    
    private void updateTable() {
        if(!viewer.getTable().isDisposed())
        {
            int maxLength = 10;
            for(Player p : PlayerManager.getInstance().getPlayerList()) {
                maxLength= Math.max(maxLength, p.getUsername().length());
            }
            
            viewer.getTable().getColumn(0).setWidth(18);
            viewer.getTable().getColumn(1).setWidth(12 * maxLength);
            viewer.refresh();
        }
    }
}
