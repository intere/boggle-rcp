package com.intere.rcp.boggle.core.model.descriptors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

/**
 * Base type for a Descriptor type.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 * 
 */
public abstract class AbstractDescriptorType {

    /** The handle to the real descriptor. */
    private IConfigurationElement handle;

    public AbstractDescriptorType() {

    }

    /**
     * Constructor that sets the Descriptor handle.
     * 
     * @param handle
     */
    public AbstractDescriptorType(IConfigurationElement handle) {
        this.handle = handle;
    }

    /**
     * This method gives you the ID of this type.
     * 
     * @return
     */
    public abstract String getId();

    /**
     * protected accessor for the handle.
     * 
     * @return
     */
    public IConfigurationElement getHandle() {
        return handle;
    }

    /**
     * Setter for the handle.
     * 
     * @param handle
     */
    public void setHandle(IConfigurationElement handle) {
        this.handle = handle;
    }

    /**
     * Gives you back a string attribute from the descriptor.
     * 
     * @param name
     * @return
     */
    protected String getStringAttribute(String name) {
        return getHandle().getAttribute(name);
    }

    /**
     * Creates an executable extension from the provided property name.
     * 
     * @param name
     * @return
     * @throws CoreException
     */
    protected Object createClass(String name) throws CoreException {
        return getHandle().createExecutableExtension(name);
    }
}
