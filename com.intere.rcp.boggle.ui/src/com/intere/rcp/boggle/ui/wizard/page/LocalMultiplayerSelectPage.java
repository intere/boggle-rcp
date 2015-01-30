package com.intere.rcp.boggle.ui.wizard.page;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TableColumn;

import com.intere.rcp.boggle.core.interfaces.IComputerPlayer;
import com.intere.rcp.boggle.core.managers.ComputerPlayerLoader;
import com.intere.rcp.boggle.core.managers.NameManager;
import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.core.model.descriptors.ComputerPlayerDescriptor;
import com.intere.rcp.boggle.ui.BoggleUIPlugin;
import com.intere.rcp.boggle.ui.providers.ComputerPlayerContentProvider;
import com.intere.rcp.boggle.ui.providers.ComputerPlayerLabelProvider;
import com.intere.rcp.boggle.ui.providers.PlayerContentProvider;
import com.intere.rcp.boggle.ui.providers.PlayerLabelProvider;

/**
 * This page allows the user to select the Local Multiplayer options (computer
 * player).
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class LocalMultiplayerSelectPage extends AbstractNewGamePage {

    /** The available computer players.  */
    private TableViewer selectPlayers;

    /** The Selected Computer Players.  */
    private TableViewer computerPlayers;

    /** The next page in the wizard.  */
    private IWizardPage nextPage;

    /**
     * Constructor that sets the game.
     * 
     * @param game
     */
    public LocalMultiplayerSelectPage(Game game) {
        super("Select Computer Players", game);
        setDescription("Select Computer Player(s)");
    }

    /**
     * Creates the UI.
     */
    public void createControl(Composite parent) {

        Composite comp = new Composite(parent, SWT.NONE);
        comp.setLayout(new GridLayout(3, false));

        Group left = new Group(comp, SWT.NONE);
        left.setText("Select Computer Players");
        left.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        left.setLayout(new GridLayout(1, false));
        selectPlayers = new TableViewer(left, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
        selectPlayers.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        ComputerPlayerLabelProvider.addColumns(selectPlayers);
        selectPlayers.setContentProvider(new ComputerPlayerContentProvider());
        selectPlayers.setLabelProvider(new ComputerPlayerLabelProvider());
        selectPlayers.setInput(ComputerPlayerLoader.getInstance().getDescriptors());

        Composite middle = new Composite(comp, SWT.NONE);
        middle.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));
        middle.setLayout(new GridLayout(1, false));

        Button add = new Button(middle, SWT.PUSH);
        add.setText(">>");
        add.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                addSelectedPlayers();
            }
        });

        Button remove = new Button(middle, SWT.PUSH);
        remove.setText("<<");
        remove.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                removeSelectedPlayers();
            }
        });

        Group right = new Group(comp, SWT.NONE);
        right.setText("Computer Player Opponents");
        right.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        right.setLayout(new GridLayout(1, false));
        computerPlayers = new TableViewer(right, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
        new TableColumn(computerPlayers.getTable(), SWT.NONE).setText("Type");
        new TableColumn(computerPlayers.getTable(), SWT.NONE).setText("Username");
        computerPlayers.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        computerPlayers.setContentProvider(new PlayerContentProvider());
        computerPlayers.setLabelProvider(new PlayerLabelProvider());
        computerPlayers.setInput(getGame());

        computerPlayers.getTable().getColumn(0).setWidth(18);
        computerPlayers.getTable().getColumn(1).setWidth(100);

        setControl(comp);
    }

    @Override
    public boolean canFlipToNextPage() {
        return getGame().getPlayerList().size() > 0;
    }

    @Override
    public IWizardPage getNextPage() {
        if (nextPage == null) {
            nextPage = new SinglePlayerGamePage(getGame());
            nextPage.setPreviousPage(this);
            nextPage.setWizard(getWizard());
        }

        return nextPage;
    }

    /**
     * Adds the selected players to the list.
     */
    protected void addSelectedPlayers() {
        IStructuredSelection sel = (IStructuredSelection) selectPlayers.getSelection();

        List<Object> selList = sel.toList();

        for (Object o : selList) {
            if (o instanceof ComputerPlayerDescriptor) {
                ComputerPlayerDescriptor desc = (ComputerPlayerDescriptor) o;

                try {
                    IComputerPlayer player = desc.createComputerPlayer();
                    player.getPlayer().setComputer(true);
                    player.getPlayer().setUsername(NameManager.getInstance().getRandomName() + " (" + desc.getName() + ")");

                    getGame().getPlayers().add(player.getPlayer().getPlayerId(), player.getPlayer());
                    computerPlayers.refresh();
                    setPageComplete(canFlipToNextPage());

                } catch (CoreException ex) {
                    BoggleUIPlugin.getDefault().logErrorMessage("Error trying to create computer player: " + desc.getName(), ex);
                }
            }
        }
    }
    
    /**
     * Removes the selected players from the list.
     */
    protected void removeSelectedPlayers() {
        IStructuredSelection sel = (IStructuredSelection)computerPlayers.getSelection();
        
        List<Object> selList = sel.toList();

        for (Object o : selList) {
            Player cp = (Player)o;
            getGame().getPlayers().remove(cp.getPlayerId());
        }
        
        computerPlayers.refresh();
    }

}
