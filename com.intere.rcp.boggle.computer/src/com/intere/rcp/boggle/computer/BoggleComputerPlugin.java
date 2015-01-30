package com.intere.rcp.boggle.computer;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import com.intere.rcp.boggle.core.managers.NameManager;

/**
 * The activator class controls the plug-in life cycle
 */
public class BoggleComputerPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.intere.rcp.boggle.computer";

	// The shared instance
	private static BoggleComputerPlugin plugin;
	
	/**
	 * The constructor
	 */
	public BoggleComputerPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		NameManager.getInstance();
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
	public static BoggleComputerPlugin getDefault() {
		return plugin;
	}

}
