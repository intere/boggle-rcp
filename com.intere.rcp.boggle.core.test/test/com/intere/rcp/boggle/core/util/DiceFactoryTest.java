package com.intere.rcp.boggle.core.util;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.intere.rcp.boggle.core.model.BoggleDice;
import com.intere.rcp.boggle.core.model.BoggleDie;
import com.intere.rcp.boggle.core.util.DiceFactory.Board;

public class DiceFactoryTest {

    @Test
    public void testCreateGameBoard() throws Exception {        
        checkDice(Board.NewFourByFour);
        checkDice(Board.OldFourByFour);
        checkDice(Board.FiveByFive);       
    }    
    
    /**
     * Checks the board for you.
     * @param dice
     * @throws IOException 
     */
    protected void checkDice(Board board) throws IOException
    {
        BoggleDice dice = DiceFactory.createGameBoard(board);
        
        Assert.assertNotNull(board.name() + " wsa null", dice);
        Assert.assertTrue(dice.getDiceCount()>0);
        Assert.assertTrue(dice.getBoardSize()==Math.sqrt(dice.getDiceCount()));
        
        for(BoggleDie die : dice.getDice())
        {
            Assert.assertNotNull(die);
            for(int i=0;i<6;i++)
            {
                Assert.assertNotNull(die.getSide(i));
            }
        }
    }

}
