package com.intere.rcp.boggle.ui.dialogs;

import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import com.intere.rcp.boggle.core.model.Game;

/**
 * This class provides the Dialog functionality.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class NewGameDialog extends WizardDialog implements IPageChangedListener {

    public NewGameDialog(Shell parentShell) {
        super(parentShell, new NewGameWizard());
        setTitle("New Game");
        
        addPageChangedListener(this);
    }

    /**
     * Gives you the game reference.
     * 
     * @return
     */
    public Game getGame() {
        return ((NewGameWizard) getWizard()).getGame();
    }

    /**
     * Handles the page changing.
     */
    public void pageChanged(PageChangedEvent event) {
        updateButtons();
    }    
}
