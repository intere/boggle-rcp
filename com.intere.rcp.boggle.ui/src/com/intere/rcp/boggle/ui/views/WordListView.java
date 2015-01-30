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
import com.intere.rcp.boggle.ui.controllers.PlayerManager;
import com.intere.rcp.boggle.ui.providers.WordListLabelProvider;
import com.intere.rcp.boggle.ui.providers.WordTableContentProvider;
import com.intere.rcp.boggle.ui.providers.WordTableViewSorter;

/**
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class WordListView extends ViewPart implements IModelListener {

    /** The ID of this view. */
    public static final String ID = "com.intere.rcp.boggle.ui.views.WordListView";

    private TableViewer viewer;
    
    private boolean registered;

    @Override
    public void createPartControl(Composite parent) {

        parent.setLayout(new GridLayout(1, false));
        viewer = new TableViewer(parent, SWT.BORDER);

        TableColumn col = new TableColumn(viewer.getTable(), SWT.NONE);
        col.setText("Word");
        col.setWidth(100);

        col = new TableColumn(viewer.getTable(), SWT.NONE);
        col.setText("Score");
        col.setWidth(100);
        viewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        viewer.setContentProvider(new WordTableContentProvider());
        viewer.setLabelProvider(new WordListLabelProvider());
        viewer.setSorter(new WordTableViewSorter());

        if (PlayerManager.getInstance().getPlayer() != null) {
            viewer.setInput(PlayerManager.getInstance().getPlayer());
            PlayerManager.getInstance().getPlayer().getWordList().addListener(this);
            registered = true;
        }
    }

    @Override
    public void setFocus() {
        if (!registered && PlayerManager.getInstance().getPlayer() != null) {
            viewer.setInput(PlayerManager.getInstance().getPlayer());
            PlayerManager.getInstance().getPlayer().getWordList().addListener(this);
            registered = true;
            System.out.println("Registered");
        }
    }

    public void onModelEvent(ModelEvent event) {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                if (!viewer.getTable().isDisposed()) {
                    viewer.refresh();
                }
            }
        });
    }

}
