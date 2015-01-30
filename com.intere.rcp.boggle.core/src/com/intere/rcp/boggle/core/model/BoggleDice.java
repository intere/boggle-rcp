package com.intere.rcp.boggle.core.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * This class represents the Boggle Dice Model.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class BoggleDice {

    @XmlElementWrapper(name="dice")
    @XmlElement(name="die")
    private List<BoggleDie> dice = new ArrayList<BoggleDie>();

    /**
     * Default Constructor.
     */
    public BoggleDice() {
        // does nothing
    }

    /**
     * Constructor that sets the dice.
     * 
     * @param dice
     */
    public BoggleDice(String[] dice) {
        setDice(dice);
    }

    /**
     * Get the size of the board.
     * 
     * @return
     */
    public int getBoardSize() {
        return (int) Math.sqrt(getDiceCount());
    }

    /**
     * Get the number of dice.
     * 
     * @return
     */
    public int getDiceCount() {
        return dice.size();
    }

    /**
     * Get the dice.
     * 
     * @return
     */
    public BoggleDie[] getDice() {
        return dice.toArray(new BoggleDie[getDiceCount()]);
    }

    /**
     * Setter for the Dice.
     * 
     * @param dice
     */
    public void setDice(String[] dice) {
        this.dice.clear();
        for (String die : dice) {
            this.dice.add(new BoggleDie(die));
        }
    }

}
