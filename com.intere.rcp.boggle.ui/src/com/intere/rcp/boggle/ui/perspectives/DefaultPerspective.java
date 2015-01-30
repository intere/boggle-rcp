package com.intere.rcp.boggle.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPlaceholderFolderLayout;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.intere.rcp.boggle.ui.BoggleUIPlugin;
import com.intere.rcp.boggle.ui.views.GameView;
import com.intere.rcp.boggle.ui.views.LoginView;
import com.intere.rcp.boggle.ui.views.PlayerView;

/**
 * The Default Perspective.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class DefaultPerspective implements IPerspectiveFactory  {
    
    /** The ID of this Perspective.  */
    public static final String ID = "com.intere.rcp.boggle.ui.perspectives.DefaultPerspective";

    public void createInitialLayout(IPageLayout layout) {
        
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(false);
        layout.setFixed(true);

        IPlaceholderFolderLayout topFolder = layout.createPlaceholderFolder("top", IPageLayout.TOP, 0.7F, editorArea);
        
        layout.addStandaloneView(PlayerView.ID, true, IPageLayout.LEFT, 0.3F, "top");
        layout.addStandaloneView(GameView.ID, true, IPageLayout.RIGHT, 0.5F, "top");        
    }
}
