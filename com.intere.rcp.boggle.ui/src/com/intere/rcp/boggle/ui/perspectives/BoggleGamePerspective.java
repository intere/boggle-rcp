package com.intere.rcp.boggle.ui.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPlaceholderFolderLayout;

import com.intere.rcp.boggle.ui.views.BoggleBoardView;
import com.intere.rcp.boggle.ui.views.ScoreView;
import com.intere.rcp.boggle.ui.views.StatView;
import com.intere.rcp.boggle.ui.views.TimerView;
import com.intere.rcp.boggle.ui.views.WordListView;

/**
 * The Boggle Game perspective.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class BoggleGamePerspective implements IPerspectiveFactory {

    /** The ID of this Perspective. */
    public static final String ID = "com.intere.rcp.boggle.ui.perspectives.BoggleGamePerspective";

    // Other static IDs
    private static final String LEFT_SIDE = ID + ".left";

    private static final String RIGHT_SIDE = ID + ".right";

    private static final String TOP_RIGHT = RIGHT_SIDE + ".top";

    private static final String BOTTOM_RIGHT = RIGHT_SIDE + ".bottom";

    /** The layout. */
    private IPageLayout layout;

    /**
     * Lays out the perspective
     */
    public void createInitialLayout(IPageLayout layout) {

        this.layout = layout;

        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(false);
        layout.setFixed(true);

        layout.addView(WordListView.ID, IPageLayout.LEFT, 0.2F, editorArea);

        IPlaceholderFolderLayout left = layout.createPlaceholderFolder(LEFT_SIDE, IPageLayout.LEFT, 0.65F, editorArea);
        left.addPlaceholder(BoggleBoardView.ID);
        left.addPlaceholder(StatView.ID);

        IFolderLayout right = layout.createFolder(RIGHT_SIDE, IPageLayout.RIGHT, 0.15F, editorArea);
        IPlaceholderFolderLayout topRight = layout.createPlaceholderFolder(TOP_RIGHT, IPageLayout.TOP, 0.5F, RIGHT_SIDE);
        topRight.addPlaceholder(ScoreView.ID);

        IPlaceholderFolderLayout bottomRight = layout.createPlaceholderFolder(BOTTOM_RIGHT, IPageLayout.BOTTOM, 0.5F, RIGHT_SIDE);
        bottomRight.addPlaceholder(TimerView.ID);
    }

    /**
     * Getter for the layout.
     * 
     * @return
     */
    public IPageLayout getLayout() {
        return layout;
    }

}
