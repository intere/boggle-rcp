package com.intere.rcp.boggle.core.managers;

import java.util.List;

import com.intere.rcp.boggle.core.model.descriptors.ComputerPlayerDescriptor;
import com.intere.rcp.boggle.core.util.AbstractExtensionPointLoader;

/**
 * This class is the loader for the Computer Player.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class ComputerPlayerLoader extends AbstractExtensionPointLoader<ComputerPlayerDescriptor> {
    private static final String COMPUTER_PLAYER_EXT_PT = "com.intere.rcp.boggle.core.interfaces.ComputerPlayer";

    /** The singleton instance variable.  This is the instance of the ComputerPlayerLoader that everyone gets.  */
    private static ComputerPlayerLoader instance;

    /** private constructor - no external instantiation.  */
    private ComputerPlayerLoader() {
        loadExtensions(ComputerPlayerDescriptor.class, COMPUTER_PLAYER_EXT_PT);
    }

    /**
     * Singleton accessor method.
     * @return The ComputerPlayerLoader instance.
     */
    public static synchronized ComputerPlayerLoader getInstance() {
        if (instance == null) {
            instance = new ComputerPlayerLoader();
        }
        return instance;
    }
    
    public List<ComputerPlayerDescriptor> getComputerPlayerDescriptors() {
        return getDescriptors().getList();
    }
}
