package com.intere.rcp.boggle.ui.wizard.page;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import com.intere.rcp.boggle.core.model.Game;

/**
 * The final options for the Game.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class FinalGameOptions extends AbstractNewGamePage {

    private Spinner spinnerTime;
    private Spinner spinnerPlayers;

    public FinalGameOptions(Game game) {
        super("Final Game Options", game);
    }

    /**
     * This method builds the UI.
     */
    public void createControl(Composite parent) {

        Composite comp = new Composite(parent, SWT.NONE);
        comp.setLayout(new GridLayout(2, false));

        new Label(comp, SWT.NONE).setText("Time (sec)");
        spinnerTime = new Spinner(comp, SWT.NONE);
        spinnerTime.setValues(180, 15, 3600, 0, 1, 10);
        spinnerTime.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        spinnerTime.setToolTipText("The time for the entire game");
        
        new Label(comp, SWT.NONE).setText("Max players");
        spinnerPlayers = new Spinner(comp, SWT.NONE);
        spinnerPlayers.setValues(1, 0, 8, 0, 1, 2);
        spinnerPlayers.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        spinnerPlayers.setToolTipText("The maximum number of players in the game (including you)");
        spinnerPlayers.setEnabled(false);

        setControl(comp);
        
        spinnerTime.addModifyListener(new ModifyListener() {            
            public void modifyText(ModifyEvent e) {
                getGame().setTime(spinnerTime.getSelection());
            }
        });
        
        spinnerPlayers.addModifyListener(new ModifyListener() {            
            public void modifyText(ModifyEvent e) {
                
            }
        });
        
        getGame().setTime(spinnerTime.getSelection());
    }

    @Override
    public boolean isPageComplete() {
        return canFlipToNextPage();
    }

    @Override
    public boolean canFlipToNextPage() {
        return true;
    }

}
