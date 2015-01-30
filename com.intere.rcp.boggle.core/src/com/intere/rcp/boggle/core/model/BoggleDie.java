package com.intere.rcp.boggle.core.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This class represents a single Die.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
public class BoggleDie {
    
    @XmlAttribute(name="die")
    private String die;
    
    /**
     * Default Constructor.
     */
    public BoggleDie() {
        
    }
    
    public BoggleDie(String die)
    {
        setDie(die);
    }
    
    public char getSide(int index)
    {
        return die.charAt(index);
    }
    
    public void setDie(String die) {
        this.die = die;
    }
    
    public String getDie() {
        return die;
    }
}
