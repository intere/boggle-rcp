package com.intere.rcp.boggle.core.managers;

import java.util.List;

import com.intere.rcp.boggle.core.interfaces.AbstractDiceLoader;
import com.intere.rcp.boggle.core.model.descriptors.BoggleDiceDescriptor;
import com.intere.rcp.boggle.core.util.AbstractExtensionPointLoader;

/**
 * This class is a singleton that is responsible for finding all registered
 * {@link AbstractDiceLoader}s.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class DiceLoader extends AbstractExtensionPointLoader<BoggleDiceDescriptor> {

    public static final String BOGGLE_DICE_EXT_PT = "com.intere.rcp.boggle.core.interfaces.BoggleDice";

    /**
     * The singleton instance variable. This is the instance of the DiceLoader
     * that everyone gets.
     */
    private static DiceLoader instance;
    
    /** private constructor - no external instantiation. */
    private DiceLoader() {
        loadExtensions(BoggleDiceDescriptor.class,BOGGLE_DICE_EXT_PT);
    }

    /**
     * Singleton accessor method.
     * 
     * @return The DiceLoader instance.
     */
    public static synchronized DiceLoader getInstance() {
        if (instance == null) {
            instance = new DiceLoader();
        }
        return instance;
    }
    
    public List<BoggleDiceDescriptor> getDiceDescriptors() {
        return getDescriptors().getList();
    }
    
}
