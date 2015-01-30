package com.intere.rcp.boggle.server;

import org.osgi.framework.BundleContext;

import com.intere.rcp.boggle.core.AbstractBasePlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class BoggleServerPlugin extends AbstractBasePlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.intere.rcp.boggle.server";

	// The shared instance
	private static BoggleServerPlugin plugin;
	
	/**
	 * The constructor
	 */
	public BoggleServerPlugin() {
	}
	
	@Override
	public String getPluginId() {
	    return PLUGIN_ID;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
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
	public static BoggleServerPlugin getDefault() {
		return plugin;
	}

}
