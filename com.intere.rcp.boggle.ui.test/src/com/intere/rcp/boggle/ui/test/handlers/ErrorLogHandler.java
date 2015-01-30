package com.intere.rcp.boggle.ui.test.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Opens the Error Log.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class ErrorLogHandler extends AbstractHandler {
    
    private static final String ERROR_LOG_ID = "org.eclipse.pde.runtime.LogView";

    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        try {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ERROR_LOG_ID);
        } catch (PartInitException e) {
            throw new ExecutionException("Error opening Error Log", e);
        }
        
        return null;
    }

}
