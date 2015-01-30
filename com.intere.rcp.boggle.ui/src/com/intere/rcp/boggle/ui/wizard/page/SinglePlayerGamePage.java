package com.intere.rcp.boggle.ui.wizard.page;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.intere.rcp.boggle.core.managers.DiceLoader;
import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.descriptors.BoggleDiceDescriptor;
import com.intere.rcp.boggle.ui.BoggleUIPlugin;

/**
 * This class will provide the user with a list of game boards to choose from.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class SinglePlayerGamePage extends AbstractNewGamePage {

    // Page Name
    private static final String PAGE_NAME = "Single Player Game";

    private ArrayList<Button> buttons = new ArrayList<Button>();

    private IWizardPage nextPage;

    /**
     * Constructor that sets the game reference.
     * 
     * @param game
     */
    public SinglePlayerGamePage(Game game) {
        super(PAGE_NAME, game);
    }

    /**
     * Builds the UI.
     */
    public void createControl(Composite parent) {

        Composite comp = new Composite(parent, SWT.NONE);

        comp.setLayout(new GridLayout(2, false));

        List<BoggleDiceDescriptor> descList = DiceLoader.getInstance().getDiceDescriptors();

        for (BoggleDiceDescriptor desc : descList) {
            final Button descOption = new Button(comp, SWT.RADIO);
            descOption.setText(desc.getName());
            descOption.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
            descOption.setData(desc);

            if (buttons.size() == 0) {
                descOption.setSelection(true);
                try {
                    handleSelection(descOption);
                } catch (CoreException ex) {
                    BoggleUIPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, BoggleUIPlugin.PLUGIN_ID, "Error setting default game", ex));
                }
            }

            buttons.add(descOption);

            descOption.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    try {
                        handleSelection(descOption);
                    } catch (CoreException ex) {
                        BoggleUIPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, BoggleUIPlugin.PLUGIN_ID, "Error loading Boggle Dice Descriptor", ex));
                    }
                }
            });

            Label lbl = new Label(comp, SWT.NONE);
            lbl.setText(desc.getDescription());
            lbl.setLayoutData(new GridData(SWT.TOP, SWT.RIGHT, false, false));
        }

        setControl(comp);

        setPageComplete(isPageComplete());
    }

    @Override
    public IWizardPage getNextPage() {

        if (nextPage == null) {
            nextPage = new FinalGameOptions(getGame());
            nextPage.setPreviousPage(this);
            nextPage.setWizard(getWizard());
        }

        return nextPage;
    }

    @Override
    public boolean canFlipToNextPage() {
        return isPageComplete();
    }

    @Override
    public boolean isPageComplete() {

        for (Button b : buttons) {
            if (b.getSelection()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Handles the selection of a button.
     * 
     * @param b
     * @throws CoreException
     */
    public void handleSelection(Button b) throws CoreException {
        if (getGame() == null) {
            throw new IllegalStateException("I don't have a game; something bad is aloof");
        }

        BoggleDiceDescriptor desc = (BoggleDiceDescriptor) b.getData();
        getGame().setGameType(desc.createDice());
        setPageComplete(true);
    }

}
