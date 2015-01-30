package com.intere.rcp.boggle.core.interfaces;

import com.intere.rcp.boggle.core.model.descriptors.BoggleDiceDescriptor;

/**
 * This class is the base class behind the Boggle Dice Extension Point.
 * Implementers must implement the methods listed below; however I don't care
 * how it's done.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public abstract class AbstractDiceLoader {

    private BoggleDiceDescriptor diceDescriptor;

    /**
     * Default constructor.
     */
    public AbstractDiceLoader() {
        super();
    }

    /**
     * Constructor that sets the {@link BoggleDiceDescriptor} reference.
     * 
     * @param diceDescriptor
     */
    public AbstractDiceLoader(BoggleDiceDescriptor diceDescriptor) {
        setDiceDescriptor(diceDescriptor);
    }

    /**
     * This method would handle the loading of the dice (for instance from a
     * file, a web server, etc). This method will be called prior to a call to
     * {@link #getDice()}.
     */
    public abstract void loadDice();

    /**
     * This method will return the dice as a single string. A call to
     * {@link #loadDice()} will be made prior to a call to this method. This
     * allows you to do whatever loading needs to be done to load these dice.
     * 
     * @return
     */
    public abstract String getDice();

    public BoggleDiceDescriptor getDiceDescriptor() {
        return diceDescriptor;
    }

    public void setDiceDescriptor(BoggleDiceDescriptor diceDescriptor) {
        this.diceDescriptor = diceDescriptor;
    }
}
