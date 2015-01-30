package com.intere.rcp.boggle.ui.dialogs;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.ui.wizard.page.NewGamePage;

/**
 * The "New Game" wizard.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class NewGameWizard extends Wizard
{
    private Game game = new Game();
    
    /**
     * Default Constructor.
     */
    public NewGameWizard() {
        setGame(game);
        setForcePreviousAndNextButtons(true);
    }
    
    @Override
    public void addPages() {
        addPage(new NewGamePage(game));
    }
    
    @Override
    public IWizardPage getNextPage(IWizardPage page) {
        return super.getNextPage(page);
    }
    
    @Override
    public boolean canFinish() {
        return game.getTime()>0;
    }
    
    @Override
    public boolean performFinish() {
        return canFinish();
    }
    
    @Override
    public boolean performCancel() {
        game = null;
        return true;
    }
    
    public Game getGame() {
        return game;
    }
    
    public void setGame(Game game) {
        this.game = game;
    }    
}