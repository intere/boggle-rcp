package com.intere.rcp.boggle.core.test;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;

import com.intere.rcp.boggle.core.managers.DiceLoader;
import com.intere.rcp.boggle.core.managers.NameManager;
import com.intere.rcp.boggle.core.model.BoggleDice;
import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.MockPlayer;
import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.core.model.ScoreSet;
import com.intere.rcp.boggle.core.model.ScoreSetTest;
import com.intere.rcp.boggle.core.model.descriptors.BoggleDiceDescriptor;

public class GameFactory {

    
    
    /**
     * Factory creation method - generates a game.
     * 
     * @return
     * @throws CoreException 
     * @throws IOException 
     */
    public static Game createGame() throws CoreException, IOException {
        
        return createGame(generateGameType());
        
    }
    
    /**
     * Factory creation method that lets you set the game type (dice).
     * @param dice
     * @return
     * @throws IOException 
     */
    public static Game createGame(BoggleDice dice) throws IOException
    {
        Game g = new Game();
        g.setCreator(createPlayer());
        generateGamePlayers(g);
        g.setGameType(dice);
        g.setTime(generateRandomNumberOneBased(180));
        g.setScoringSet(generateScoringSet());

        return g;
    }

    /**
     * Convenience method that generates a score set.
     * @return
     * @throws IOException 
     */
    protected static ScoreSet generateScoringSet() throws IOException {
        

        ScoreSet set = ScoreSet.fromBundleFile(ScoreSetTest.SCORE_SET);
        
        return set;
    }

    protected static BoggleDice generateGameType() throws CoreException {
        
        List<BoggleDiceDescriptor> descList = DiceLoader.getInstance().getDiceDescriptors();
        
        BoggleDiceDescriptor desc = descList.get(generateRandomNumberZeroBased(descList.size()));
        
        
        return desc.createDice();    
    }

    /**
     * This method is responsible for generating the players to be added to the
     * game.
     * 
     * @param g
     */
    protected static void generateGamePlayers(Game g) {
        int players = generateRandomNumberOneBased(10);

        for (int i = 0; i < players; i++) {
            Player player = createPlayer();

            g.getPlayers().add(player.getUsername(), player);
        }
    }
    
    

    /**
     * Convenience method that generates players for you.
     * 
     * @return
     */
    public static Player createPlayer() {
        Player p = new MockPlayer(NameManager.getInstance().getRandomName());
        p.setUsername(NameManager.getInstance().getRandomName());

        return p;
    }

    /**
     * Random number generator method. The lowest number this generates is 1 and
     * the highest number it will generate is the provided maxInt value.
     * 
     * @param maxInt
     * @return
     */
    public static int generateRandomNumberOneBased(int maxInt) {
        return generateRandomNumberZeroBased(maxInt) + 1;
    }

    /**
     * Random number generator method. The lowest number this generates is 0 and
     * the highest number it will generate is maxInt-1.
     * 
     * @param maxInt
     * @return
     */
    public static int generateRandomNumberZeroBased(int maxInt) {
        return (int) (Math.random() * (float) maxInt);
    }
}
