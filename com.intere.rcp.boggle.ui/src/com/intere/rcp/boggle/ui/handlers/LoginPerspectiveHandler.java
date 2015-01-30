package com.intere.rcp.boggle.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

import com.intere.rcp.boggle.ui.perspectives.LoginPerspective;

public class LoginPerspectiveHandler extends AbstractHandler {

    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().setPerspective(
                PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId(LoginPerspective.ID));
        
        return null;
    }

}
