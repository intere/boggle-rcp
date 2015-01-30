package com.intere.rcp.boggle.ui.test;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.intere.rcp.boggle.core.model.BoggleDice;
import com.intere.rcp.boggle.core.util.DiceFactory;
import com.intere.rcp.boggle.core.util.DiceFactory.Board;
import com.intere.rcp.boggle.server.util.BoggleDiceRoller;
import com.intere.rcp.boggle.ui.components.BoggleCanvas;

public class View extends ViewPart {

    private static final String SEVEN = "SEVEN";

    private static final String OLD4X4 = "OLD4";

    private static final String NEW4X4 = "NEW4";

    private static final String FIVE = "FIVE";

    private static final String SIX = "SIX";

    public static final String ID = "com.intere.rcp.boggle.ui.test.view";

    /** The Boggle Canvas. */
    private BoggleCanvas canvas;

    /** The Dice that are chosen for this canvas. */
    private BoggleDice dice;

    /** The Dice Roller for the dice. */
    private BoggleDiceRoller roller;

    /**
     * This is a callback that will allow us to create the viewer and initialize
     * it.
     */
    public void createPartControl(Composite parent) {
        parent.setLayout(new GridLayout(1, true));

        String type = getViewSite().getSecondaryId();

        if (type == null || type.length() == 0) {
            new Label(parent, SWT.NONE).setText("I don't know what type of board to use");
        } else {

            try {
                if (type.equals(OLD4X4)) {
                    dice = DiceFactory.createGameBoard(Board.OldFourByFour);
                } else if (type.equals(NEW4X4)) {
                    dice = DiceFactory.createGameBoard(Board.NewFourByFour);
                } else if (type.equals(FIVE)) {
                    dice = DiceFactory.createGameBoard(Board.FiveByFive);
                } else if (type.equals(SIX)) {
                    dice = DiceFactory.createGameBoard(Board.SixBySix);
                } else if (type.equals(SEVEN)) {
                    
                    InputStream in = Activator.getDefault().getBundle().getResource("/resources/7x7.txt").openStream();
                    dice = DiceFactory.createGameBoard(in);
                    in.close();
                }

                roller = new BoggleDiceRoller(dice);

                canvas = new BoggleCanvas(parent, SWT.BORDER);
                canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
                canvas.getController().setBoard(roller.roll());
                System.out.println("Board: " + canvas.getController().getBoard());

            } catch (IOException ex) {
                new Label(parent, SWT.NONE).setText("I was unable to read the dice file for the board");
                Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Error reading Boggle Dice File", ex));
            }
        }
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {

    }
}
