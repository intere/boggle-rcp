package com.intere.rcp.boggle.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.intere.rcp.boggle.core.AbstractBasePlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class BoggleUIPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "com.intere.rcp.boggle.ui";

    // The shared instance
    private static BoggleUIPlugin plugin;

    private DelegatePlugin delegate = new DelegatePlugin();

    /**
     * The constructor
     */
    public BoggleUIPlugin() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static BoggleUIPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    /**
     * These methods will log an error message for you.
     * 
     * @param message
     */
    public void logErrorMessage(String message) {
        delegate.logErrorMessage(message);
    }

    /**
     * This method will log an error message and throwable for you.
     * 
     * @param message
     * @param t
     */
    public void logErrorMessage(String message, Throwable t) {
        delegate.logErrorMessage(message, t);
    }

    /**
     * This little inner class is just used to delegate the important error
     * logging methods to.
     */
    private static class DelegatePlugin extends AbstractBasePlugin {
        @Override
        public String getPluginId() {
            return PLUGIN_ID;
        }
    }
}
