package com.intere.rcp.boggle.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

import com.intere.rcp.boggle.ui.perspectives.BoggleGamePerspective;

public class ActiveGamePerspectiveHandler extends AbstractHandler {
    
    /** The ID of this view.  */
    public static final String ID = "com.intere.rcp.boggle.ui.handlers.ActiveGamePerspectiveHandler";

    public Object execute(ExecutionEvent event) throws ExecutionException {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().setPerspective(PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId(BoggleGamePerspective.ID));

        return null;
    }

}
