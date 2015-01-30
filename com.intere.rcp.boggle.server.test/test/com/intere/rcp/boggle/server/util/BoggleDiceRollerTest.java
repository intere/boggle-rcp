package com.intere.rcp.boggle.server.util;

import org.junit.Assert;
import org.junit.Test;

import com.intere.rcp.boggle.core.model.BoggleDice;
import com.intere.rcp.boggle.core.util.DiceFactory;
import com.intere.rcp.boggle.core.util.DiceFactory.Board;

public class BoggleDiceRollerTest {

    @Test
    public void testRoll() throws Exception
    {
        
        for(Board b : Board.values())
        {
            BoggleDice dice = DiceFactory.createGameBoard(b);
            BoggleDiceRoller roller = new BoggleDiceRoller(dice);
            
            String roll1 = roller.roll();
            String roll2 = roller.roll();
            
            Assert.assertNotNull(roll1);
            Assert.assertNotNull(roll2);
            Assert.assertTrue(!roll1.equals(roll2));
            
            System.out.println("Roll 1: " + roll1);
            System.out.println("Roll 2: " + roll2);
        }
        
    }

}
