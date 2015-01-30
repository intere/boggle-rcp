package com.intere.rcp.boggle.ui.test.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.intere.rcp.boggle.ui.test.View;

public class ShowBoardHandler extends AbstractHandler {

    private static final String COM_INTERE_RCP_BOGGLE_UI_TEST_BOARD = "com.intere.rcp.boggle.ui.test.board";

    public Object execute(ExecutionEvent event) throws ExecutionException {

        String boardType = event.getParameter(COM_INTERE_RCP_BOGGLE_UI_TEST_BOARD);

        if (boardType != null) {
            try {
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(View.ID, boardType, IWorkbenchPage.VIEW_ACTIVATE);
            } catch (PartInitException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
