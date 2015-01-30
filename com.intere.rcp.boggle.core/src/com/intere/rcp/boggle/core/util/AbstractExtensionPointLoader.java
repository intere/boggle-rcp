package com.intere.rcp.boggle.core.util;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

import com.intere.rcp.boggle.core.BoggleCorePlugin;
import com.intere.rcp.boggle.core.model.PairedList;
import com.intere.rcp.boggle.core.model.descriptors.AbstractDescriptorType;

/**
 * This class serves as a base class for a class that wants to load extension
 * points dynamically.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 * 
 * @param <DescriptorType>
 */
public abstract class AbstractExtensionPointLoader<DescriptorType extends AbstractDescriptorType> {

    /** The data structure that holds the descriptors. */
    private PairedList<String, DescriptorType> descriptors = new PairedList<String, DescriptorType>();

    /**
     * Helper method that will load the extension.
     * 
     * @param clazz
     * @param extId
     */
    public void loadExtensions(Class<DescriptorType> clazz, String extId) {
        IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(extId);

        IConfigurationElement elements[] = extensionPoint.getConfigurationElements();

        for (IConfigurationElement elem : elements) {
            try {
                DescriptorType inst = clazz.newInstance();
                inst.setHandle(elem);
                descriptors.add(inst.getId(), inst);

            } catch (InstantiationException e) {
                BoggleCorePlugin.getDefault().logErrorMessage("Error instantiating class: " + clazz.getName(), e);
            } catch (IllegalAccessException e) {
                BoggleCorePlugin.getDefault().logErrorMessage("Illegal Access trying to instantiate class: " + clazz.getName(), e);
            }
        }
    }

    public PairedList<String, DescriptorType> getDescriptors() {
        return descriptors;
    }
}
