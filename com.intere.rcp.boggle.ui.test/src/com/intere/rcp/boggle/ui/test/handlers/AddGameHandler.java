package com.intere.rcp.boggle.ui.test.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.intere.rcp.boggle.core.managers.DiceLoader;
import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.core.model.Game.GameState;
import com.intere.rcp.boggle.core.util.DiceFactory;
import com.intere.rcp.boggle.ui.controllers.GameManager;
import com.intere.rcp.boggle.ui.controllers.PlayerManager;

/**
 * Mock implementation that will add arbitrary games to the game list.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class AddGameHandler extends AbstractHandler {

    public static final String PARAM_COUNT = "com.intere.rcp.boggle.ui.test.handlers.AddGameHandlerCommand.count";

    public Object execute(ExecutionEvent event) throws ExecutionException {

        int count = 1;

        if (event.getParameter(PARAM_COUNT) != null) {
            try {
                count = Integer.parseInt(event.getParameter(PARAM_COUNT));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        
        for(int i=0;i<count;i++)
        {
            GameManager.getInstance().addGame(createRandomGame());
        }

        return null;
    }

    /**
     * This method creates you a random game.
     * 
     * @return
     */
    protected static Game createRandomGame() {
        Game game = new Game();

        game.setCreator(pickRandomPlayer());

        int count = (int) ((Math.random() * 3) + 1);

        for (int i = 0; i < count; i++) {
            Player p = pickRandomPlayer();
            if (!game.getPlayerList().contains(p)) {
                game.getPlayerList().add(p);
            }
        }

        game.setGameState(GameState.values()[(int) (Math.random() * GameState.values().length)]);
        // TODO set the Game Type (game)
        game.setTime((int) (Math.random() * 900));

        return game;
    }

    /**
     * Helper method - picks a random username from the user list.
     * 
     * @return
     */
    protected static Player pickRandomPlayer() {
        Player player = null;

        int index = (int) (Math.random() * PlayerManager.getInstance().getPlayerList().size());

        player = PlayerManager.getInstance().getPlayerList().get(index);

        return player;
    }

}
