package com.intere.rcp.boggle.server.util;

import java.util.ArrayList;
import java.util.List;

import com.intere.rcp.boggle.core.model.BoggleDice;
import com.intere.rcp.boggle.core.model.BoggleDie;

public class BoggleDiceRoller {
    private BoggleDice dice;

    public BoggleDiceRoller(BoggleDice dice) {
        this.dice = dice;
    }

    /**
     * This method rolls the dice.
     * @return 
     */
    public String roll() {
        BoggleDie array[] = dice.getDice();
        List<BoggleDie> list = new ArrayList<BoggleDie>();
        List<BoggleDie> random = new ArrayList<BoggleDie>();

        for (BoggleDie die : array) {
            list.add(die);
        }

        while (list.size() > 0) {
            int index = getRandomIndex(list);
            random.add(list.remove(index));
        }
        
        StringBuffer roll = new StringBuffer();
        for(BoggleDie die : random)
        {
            roll.append(rollDie(die));
        }
        
        return roll.toString();
    }

    /**
     * This method rolls the provided die.
     * 
     * @param die
     * @return
     */
    private char rollDie(BoggleDie die) {
        int index = (int) (6 * Math.random());
        return die.getSide(index);
    }

    /**
     * This method gives you a random index from the current list.
     * 
     * @param list
     * @return
     */
    private int getRandomIndex(List<BoggleDie> list) {
        return (int) (list.size() * Math.random());
    }
}
