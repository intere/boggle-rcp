package com.intere.rcp.boggle.core.model.descriptors;

import org.eclipse.core.runtime.CoreException;

import com.intere.rcp.boggle.core.interfaces.IComputerPlayer;

/**
 * This class is the descriptor for a computer player.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class ComputerPlayerDescriptor extends AbstractDescriptorType {

    private static final String STRATEGY = "strategy";

    private static final String DIFFICULTY = "difficulty";

    private static final String NAME = "name";

    @Override
    public String getId() {
        return getName();
    }

    /**
     * Get the name of the computer player.
     * 
     * @return
     */
    public String getName() {
        return getStringAttribute(NAME);
    }

    /**
     * Get the difficulty of the computer player.
     * 
     * @return
     */
    public String getDifficulty() {
        return getStringAttribute(DIFFICULTY);
    }

    /**
     * Get the computer player's strategy.
     * 
     * @return
     */
    public String getStrategy() {
        return getStringAttribute(STRATEGY);
    }

    /**
     * This method creates an instance of the Computer Player for you.
     * 
     * @return
     * @throws CoreException
     */
    public IComputerPlayer createComputerPlayer() throws CoreException {
        return (IComputerPlayer) createClass("class");
    }

}
