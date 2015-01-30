package com.intere.rcp.boggle.ui.wizard.page;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;

import com.intere.rcp.boggle.core.model.Game;

/**
 * Base class for a Page that is related to the "New Game" wizard.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public abstract class AbstractNewGamePage extends WizardPage {

    private Game game;

    /**
     * Constructor that sets the Page Name.
     * 
     * @param pageName
     */
    public AbstractNewGamePage(String pageName) {
        super(pageName);
    }

    /**
     * Constructor that sets the Page Name and game reference.
     * 
     * @param pageName
     * @param game
     */
    public AbstractNewGamePage(String pageName, Game game) {
        this(pageName);
        setGame(game);
    }

    /**
     * Constructor that sets the PageName, Title, and Title Image.
     * @param pageName
     * @param title
     * @param titleImage
     */
    public AbstractNewGamePage(String pageName, String title, ImageDescriptor titleImage) {
        super(pageName, title, titleImage);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
