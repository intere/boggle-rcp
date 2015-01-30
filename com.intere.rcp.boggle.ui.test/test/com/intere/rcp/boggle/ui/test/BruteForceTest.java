package com.intere.rcp.boggle.ui.test;

import java.io.InputStream;
import java.util.Date;

import org.junit.Test;

import com.intere.rcp.boggle.core.model.BoggleDice;
import com.intere.rcp.boggle.core.util.BoardUtils;
import com.intere.rcp.boggle.core.util.DiceFactory;
import com.intere.rcp.boggle.core.util.FileReader;
import com.intere.rcp.boggle.server.util.BoggleDiceRoller;

public class BruteForceTest {
	
	/** The Dice that are chosen for this canvas. */
    private BoggleDice dice;

    /** The Dice Roller for the dice. */
    private BoggleDiceRoller roller;

    
    @Test
    public void testBruteForce() throws Exception {
    	InputStream in = Activator.getDefault().getBundle().getResource("/resources/7x7.txt").openStream();
        dice = DiceFactory.createGameBoard(in);
        in.close();
        
        String board = new BoggleDiceRoller(dice).roll();
        
        System.out.println("reading dictionary");
        in = Activator.getDefault().getBundle().getResource("/resources/eng_com.dic").openStream();
        String[] elems = FileReader.readStream(in).toString().split("\n");
        in.close();
        
        System.out.println("Starting");
        int count = 0;
        Date start = new Date();
        for(String word : elems)
        {
        	if(BoardUtils.getPath(board, word).size()>0) {
        		++count;
        	}        	
        }
        Date stop = new Date();
        
        System.out.println("It took " + (stop.getTime()-start.getTime()) + " ms");
        System.out.println("Complete: " + count + " words found (out of " + elems.length + " words)");
    }
}
