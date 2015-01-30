package com.intere.rcp.boggle.ui.test.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.intere.rcp.boggle.ui.views.GameView;

public class GameListViewHandler extends AbstractHandler {

    public Object execute(ExecutionEvent event) throws ExecutionException {
        try {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(GameView.ID);
        } catch (PartInitException e) {
            e.printStackTrace();
        }
        return null;
    }

}
