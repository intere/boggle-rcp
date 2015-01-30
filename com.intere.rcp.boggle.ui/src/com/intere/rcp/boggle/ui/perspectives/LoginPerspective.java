package com.intere.rcp.boggle.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.intere.rcp.boggle.ui.views.LoginView;

public class LoginPerspective implements IPerspectiveFactory {
    
    /** The ID of this Perspective.  */
    public static final String ID = "com.intere.rcp.boggle.ui.perspectives.LoginPerspective";

    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(false);

        layout.addStandaloneView(LoginView.ID, false, IPageLayout.TOP, 1.0F, editorArea);
    }

}
