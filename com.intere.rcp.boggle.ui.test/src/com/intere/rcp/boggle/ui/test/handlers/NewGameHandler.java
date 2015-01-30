package com.intere.rcp.boggle.ui.test.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import com.intere.rcp.boggle.core.managers.NameManager;
import com.intere.rcp.boggle.core.managers.ServerDelegateService;
import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.ui.controllers.PlayerManager;
import com.intere.rcp.boggle.ui.dialogs.NewGameDialog;

/**
 * Handler for the "New Game" dialog.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class NewGameHandler extends AbstractHandler {

    public Object execute(ExecutionEvent event) throws ExecutionException {

        if (PlayerManager.getInstance().isLoggedIn()) {

            NewGameDialog dlg = new NewGameDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
            dlg.open();

            Game game = dlg.getGame();
            
            if(game!=null) {
                
                for(Player p : game.getPlayerList()) {
                    if(p.isComputer()) {
                        PlayerManager.getInstance().addPlayer(p);
                    }
                }
                
                game.setCreator(PlayerManager.getInstance().getPlayer());
                game.getPlayerList().add(PlayerManager.getInstance().getPlayer());
    
                if (game != null) {
                    ServerDelegateService.getInstance().createGame(PlayerManager.getInstance().getPlayer().getPlayerId(), game);
                }
            }
        } else {
            MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", "You must be logged in to start a game");
        }

        return null;
    }

}
