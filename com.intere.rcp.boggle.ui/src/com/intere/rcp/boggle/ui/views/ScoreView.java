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
import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.ui.controllers.GameManager;
import com.intere.rcp.boggle.ui.providers.ScoreContentProvider;
import com.intere.rcp.boggle.ui.providers.ScoreLabelProvider;
import com.intere.rcp.boggle.ui.providers.ScoreViewerSorter;

/**
 * The view that shows you the scores.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class ScoreView extends ViewPart implements IModelListener {

    /** The ID of this view. */
    public static final String ID = "com.intere.rcp.boggle.ui.views.ScoreView";

    public enum Column {
        Type("", 20), User("User", 100), Score("Score", 100);

        private String title;

        private int minSize;

        private Column(String title, int minSize) {
            this.title = title;
            this.minSize = minSize;
        }

        public String getTitle() {
            return title;
        }

        public int getMinSize() {
            return minSize;
        }

        /**
         * Static convenience method to get a Column from a provided title.
         * 
         * @param title
         * @return
         */
        public static Column fromTitle(String title) {
            for (Column c : Column.values()) {
                if (c.getTitle().equals(title)) {
                    return c;
                }
            }

            return null;
        }
    }

    /** The game model. */
    private Game game;

    /** The table that shows the scores. */
    private TableViewer viewer;

    @Override
    public void createPartControl(Composite parent) {

        viewer = new TableViewer(parent, SWT.BORDER);

        for (Column c : Column.values()) {
            TableColumn col = new TableColumn(viewer.getTable(), SWT.NONE);
            col.setText(c.getTitle());
            col.setWidth(c.getMinSize());
        }

        viewer.setContentProvider(new ScoreContentProvider());
        viewer.setLabelProvider(new ScoreLabelProvider());
        viewer.setSorter(new ScoreViewerSorter());
        viewer.getTable().setHeaderVisible(true);

        parent.setLayout(new GridLayout(1, true));
        viewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        GameManager.getInstance().addModelListener(this);
    }

    @Override
    public void setFocus() {
        // TODO Auto-generated method stub
    }

    public void onModelEvent(ModelEvent event) {
        if (event.getModel() == game) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    if (!viewer.getTable().isDisposed()) {
                        viewer.refresh();
                        expandColumnSizes();
                    }
                }
            });
        }
    }

    /**
     * Sets the Game ID.
     * 
     * @param gameId
     */
    public void setGameId(String gameId) {
        game = GameManager.getInstance().getGame(gameId);
        if(viewer!=null)
        {
            viewer.setInput(game);
        }
    }

    /**
     * This method will expand the column sizes to the "bare" minimum.
     */
    protected void expandColumnSizes() {
        for (TableColumn tc : viewer.getTable().getColumns()) {
            Column col = Column.fromTitle(tc.getText());

            switch (col) {
            case Score:
            case Type: {
                if (tc.getWidth() < col.getMinSize()) {
                    tc.setWidth(col.getMinSize());
                }
                break;
            }

            case User: {
                int max = Math.max(getLongestUsername(), col.getMinSize());

                if (tc.getWidth() < max) {
                    tc.setWidth(max);
                }

                break;
            }
            }
        }
    }

    /**
     * Gives you the length longest username.
     * 
     * @return
     */
    protected int getLongestUsername() {
        int maxlen = 0;
        for (Player p : game.getPlayerList()) {
            int ulen = p.getUsername().length();
            if (ulen > maxlen) {
                maxlen = ulen;
            }
        }

        return maxlen * 7;
    }

}
