package com.intere.rcp.boggle.ui.wizard.page;


import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.intere.rcp.boggle.core.model.Game;

/**
 * This is the initial page for the New Game Wizard. You pick the game type;
 * single player or multi-player.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class NewGamePage extends AbstractNewGamePage {

    /**
     * Enumeration of the different types of games.
     */
    public enum GameType {
        SinglePlayer("Single Player", "Single Player Local Game", true), 
        MultiPlayerLocal("Multi Player (Local)", "Local Multiplayer game (computer opponents)", true), 
        MultiPlayerClientServer("Multi Player (Server)", "Server Multiplayer game (network game)", false);

        private String title;

        private String description;

        private boolean enabled;

        private GameType(String title, String description, boolean enabled) {
            this.title = title;
            this.description = description;
            this.enabled = enabled;
        }
    }
    
    private Button[] buttons;
    private IWizardPage nextPage;

    /**
     * Constructor that sets the page name.
     * @param game 
     * @param pageName
     */
    public NewGamePage(Game game) {
        super("Select Game Type", game);
        setDescription("Select the game type that you would like to create.");
    }

    /**
     * Builds the UI.
     */
    public void createControl(Composite parent) {
        
        Composite comp = new Composite(parent, SWT.NONE);
        comp.setLayout(new GridLayout(2, false));

        boolean selected = true;
        ArrayList<Button> buttons = new ArrayList<Button>();
        
        for (GameType type : GameType.values()) {
            Button b = new Button(comp, SWT.RADIO);
            b.setText(type.title);
            b.setEnabled(type.enabled);
            b.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    nextPage = null;
                    setPageComplete(isPageComplete());
                }
            });
            
            if(type.enabled && selected)
            {
                b.setSelection(true);
                selected = false;
            }
            
            Label lbl = new Label(comp, SWT.NONE);
            lbl.setText(type.description);
            lbl.setEnabled(type.enabled);
            
            buttons.add(b);
        }
        
        this.buttons = buttons.toArray(new Button[buttons.size()]);
        
        setControl(comp);
        setPageComplete(isPageComplete());        
    }
    
    @Override
    public IWizardPage getNextPage() {
        
        if(nextPage==null)
        {
            GameType type = getSelectedGameType();
            if(type!=null)
            {
                switch(type)
                {
                case SinglePlayer:
                {
                    nextPage = new SinglePlayerGamePage(getGame());
                    nextPage.setPreviousPage(this);
                    nextPage.setWizard(getWizard());
                    break;
                }
                
                case MultiPlayerLocal:
                {
                    nextPage = new LocalMultiplayerSelectPage(getGame());
                    nextPage.setPreviousPage(this);
                    nextPage.setWizard(getWizard());
                    break;
                }
                }
            }
        }
        
        
        return nextPage;        
    }
    
    @Override
    public boolean isPageComplete() {
        return getSelectedGameType()!=null;
    }
    
    /**
     * This method will give you the selected index.
     * @return
     */
    protected GameType getSelectedGameType()
    {
        GameType type = null;
        
        for(Button b : buttons)
        {
            for(GameType gt : GameType.values())
            {
                if(gt.title.equals(b.getText()) && b.getSelection())
                {
                    type = gt;
                    break;
                }
            }
        }
        
        return type;
    }

}
