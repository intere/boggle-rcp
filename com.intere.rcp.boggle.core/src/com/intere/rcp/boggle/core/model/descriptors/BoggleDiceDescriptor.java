package com.intere.rcp.boggle.core.model.descriptors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.intere.rcp.boggle.core.interfaces.AbstractDiceLoader;
import com.intere.rcp.boggle.core.model.BoggleDice;
import com.intere.rcp.boggle.core.util.DiceFactory;

/**
 * This class is the metadata behind the {@link AbstractDiceLoader}. When you
 * register an {@link AbstractDiceLoader} with the Boggle Dice Extension Point,
 * one of these classes will be created via the DiceLoader class for you.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class BoggleDiceDescriptor extends AbstractDescriptorType {
    private static final String NAME = "name";

    private static final String SIZE = "size";

    private static final String DESCRIPTION = "description";

    private static final String FILENAME = "filename";

    private static final String CLASS = "class";

    /**
     * Default Constructor.
     */
    public BoggleDiceDescriptor() {
        super();
    }

    /**
     * Constructor that creates the {@link BoggleDiceDescriptor} for you using
     * the provided parameters.
     * 
     * @param name
     * @param description
     * @param filename
     * @param size
     */
    public BoggleDiceDescriptor(IConfigurationElement handle) {
        super(handle);
    }

    @Override
    public String getId() {
        return getName();
    }

    public String getName() {
        return getStringAttribute(NAME);
    }

    public int getSize() {
        return Integer.valueOf(getStringAttribute(SIZE));
    }

    public String getDescription() {
        return getStringAttribute(DESCRIPTION);
    }

    public String getFilename() {
        return getStringAttribute(FILENAME);
    }

    /**
     * This method will provide you back the Descriptor's
     * {@link AbstractDiceLoader} (the concrete that the extension point
     * referenced).
     * 
     * @return
     * @throws CoreException
     */
    public AbstractDiceLoader createLoader() throws CoreException {
        return (AbstractDiceLoader) createClass(CLASS);
    }

    /**
     * This method creates the {@link BoggleDice} using the
     * {@link AbstractDiceLoader}
     * 
     * @return
     * @throws CoreException
     */
    public BoggleDice createDice() throws CoreException {
        AbstractDiceLoader loader = createLoader();
        loader.setDiceDescriptor(this);
        loader.loadDice();

        BoggleDice dice = DiceFactory.createGameBoard(new StringBuffer(loader.getDice()));

        return dice;
    }
}
