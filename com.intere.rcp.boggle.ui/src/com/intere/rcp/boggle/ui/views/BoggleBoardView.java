package com.intere.rcp.boggle.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.intere.rcp.boggle.core.managers.ServerDelegateService;
import com.intere.rcp.boggle.ui.components.BoggleCanvas;
import com.intere.rcp.boggle.ui.controllers.GameManager;
import com.intere.rcp.boggle.ui.controllers.PlayerManager;

/**
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class BoggleBoardView extends ViewPart {

    public static final String ID = "com.intere.rcp.boggle.ui.views.BoggleBoardView";

    /** The Boggle Canvas. */
    private BoggleCanvas canvas;

    /** The text that the user can type into */
    private Text entryText;

    /** The Game ID. */
    private String gameId;

    /** The "copy" buffer */
    private String copyBuffer;

    @Override
    public void createPartControl(Composite parent) {

        parent.setLayout(new GridLayout(1, false));

        canvas = new BoggleCanvas(parent, SWT.BORDER);
        canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        entryText = new Text(parent, SWT.BORDER);
        entryText.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));
        entryText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyEvent(e);
            }
        });
    }

    /**
     * This method handles key events for you.
     * 
     * @param e
     */
    protected void handleKeyEvent(KeyEvent e) {
        if (e.keyCode == SWT.CR) {
            guessWord();
        } else if ((e.stateMask & SWT.CTRL) != 0) {
            if (e.keyCode == 'r') {
                canvas.getController().rotate();
            } else if (e.keyCode == 'u') {
                copyBuffer = entryText.getText(0, entryText.getCaretPosition());
                entryText.setText(entryText.getText().substring(entryText.getCaretPosition()));
            } else {
                updateText();
            }
        } else {
            updateText();
        }
    }

    /**
     * Getter for the Game ID.
     * 
     * @return
     */
    public String getGameId() {
        return gameId;
    }

    /**
     * Sets the Game ID.
     * 
     * @param gameId
     */
    public void setGameId(String gameId) {
        this.gameId = gameId;

        if(gameId!=null) {
            entryText.setEnabled(true);
            canvas.getController().setBoard(GameManager.getInstance().getGame(gameId).getBoard());    
            canvas.redraw();
            canvas.update();
        } else {
            entryText.setEnabled(false);            
        }
    }

    @Override
    public void setFocus() {
        entryText.forceFocus();
    }

    /**
     * Guesses a word.
     */
    protected void guessWord() {
        ServerDelegateService.getInstance().guessWord(PlayerManager.getInstance().getPlayer().getPlayerId(), gameId, entryText.getText());
        entryText.setText("");
    }

    /**
     * Updates the text in the boggle controller.
     */
    protected void updateText() {
        canvas.getController().setWalkText(entryText.getText());
    }

    /**
     * Static method to get the instance of the Board View.
     * 
     * @return
     */
    public static BoggleBoardView getBoggleBoardView() {
        return (BoggleBoardView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ID);
    }
}
