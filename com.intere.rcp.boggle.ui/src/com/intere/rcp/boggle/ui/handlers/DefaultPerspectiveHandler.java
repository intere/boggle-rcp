package com.intere.rcp.boggle.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

import com.intere.rcp.boggle.ui.perspectives.DefaultPerspective;

public class DefaultPerspectiveHandler extends AbstractHandler {
    
    /** The ID of this Handler.  */
    public static final String ID = "com.intere.rcp.boggle.ui.handlers.DefaultPerspectiveHandler";

    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().setPerspective(
                PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId(DefaultPerspective.ID));
        
        return null;
    }
    
    public static void enablePerspective() {
        Command cmd = getCommand();
        if(cmd!=null) {
            cmd.setEnabled(true);
        }
    }
    
    public static void disablePerspective() {
        Command cmd = getCommand();
        if(cmd!=null) {
            cmd.setEnabled(false);
        }
    }
    
    protected static final Command getCommand() {
        ICommandService svc = null;
        
        svc = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
        
        
        return svc.getCommand(ID);
    }

}
