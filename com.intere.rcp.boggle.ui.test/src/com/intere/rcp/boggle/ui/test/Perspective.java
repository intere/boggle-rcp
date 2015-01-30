package com.intere.rcp.boggle.ui.test;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPlaceholderFolderLayout;

import com.intere.rcp.boggle.ui.views.GameView;
import com.intere.rcp.boggle.ui.views.LoginView;
import com.intere.rcp.boggle.ui.views.PlayerView;

public class Perspective implements IPerspectiveFactory {

	private static final String BOGGLE_VIEWS = "boggle.views";

    public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		
		IPlaceholderFolderLayout folder = layout.createPlaceholderFolder(BOGGLE_VIEWS, IPageLayout.TOP, 0.7F, editorArea);
		folder.addPlaceholder(View.ID);
		layout.getViewLayout(View.ID).setCloseable(true);
		layout.getViewLayout(View.ID).setMoveable(true);
		
		
        layout.addView(LoginView.ID, IPageLayout.TOP, 0.3F, editorArea);
		layout.addView(PlayerView.ID, IPageLayout.LEFT, 0.4F, editorArea);
		layout.addView(GameView.ID, IPageLayout.RIGHT, 0.6F, editorArea);		
		
	}

}
