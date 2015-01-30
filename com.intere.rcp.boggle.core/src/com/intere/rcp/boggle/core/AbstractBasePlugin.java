package com.intere.rcp.boggle.core;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

/**
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public abstract class AbstractBasePlugin extends Plugin {

    public abstract String getPluginId();

    /**
     * Logs an error message without a throwable for you.
     * @param message
     */
    public void logErrorMessage(String message) {
        logErrorMessage(message, null);
    }
    
    /**
     * Logs an error message with a throwable (for stack trace) for you.
     * 
     * @param message
     * @param t
     */
    public void logErrorMessage(String message, Throwable t) {
        if (t != null) {
            getLog().log(new Status(IStatus.ERROR, getPluginId(), message, t));
        } else {
            getLog().log(new Status(IStatus.ERROR, getPluginId(), message));
        }
    }
}
