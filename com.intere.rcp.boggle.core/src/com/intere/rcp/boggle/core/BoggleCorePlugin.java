package com.intere.rcp.boggle.core;

import org.osgi.framework.BundleContext;

import com.intere.rcp.boggle.core.managers.NameManager;
import com.intere.rcp.boggle.core.managers.SpellCheckService;

/**
 * The activator class controls the plug-in life cycle
 */
public class BoggleCorePlugin extends AbstractBasePlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "com.intere.rcp.boggle.core";

    // The shared instance
    private static BoggleCorePlugin plugin;

    /**
     * The constructor
     */
    public BoggleCorePlugin() {
    }

    @Override
    public String getPluginId() {
        return PLUGIN_ID;
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

        SpellCheckService.getInstance();
        NameManager.getInstance();
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
    public static BoggleCorePlugin getDefault() {
        return plugin;
    }

}
