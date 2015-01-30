package com.intere.rcp.boggle.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.intere.rcp.boggle.core.event.IModelListener;
import com.intere.rcp.boggle.core.event.ModelEvent;
import com.intere.rcp.boggle.ui.controllers.GameManager;

/**
 * This class is the view that provides the game time. It will count the time
 * left for you (assuming events come in that tell it to update).
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class TimerView extends ViewPart implements IModelListener {

    /** The ID of this view. */
    public static final String ID = "com.intere.rcp.boggle.ui.views.TimerView";

    private TimerCanvas canvas;

    private String gameId;

    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new GridLayout(1, false));

        canvas = new TimerCanvas(parent, SWT.NONE);
        canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        GameManager.getInstance().addModelListener(this);
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    @Override
    public void setFocus() {
        // TODO Auto-generated method stub

    }

    /**
     * Event handler for model events (time to redraw the canvas - because the
     * time was updated).
     */
    public void onModelEvent(ModelEvent event) {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                if (!canvas.isDisposed()) {
                    canvas.redraw();
                    canvas.update();
                }
            }
        });
    }

    /**
     * Static accessor method that queries the PlatformUI for this view.
     * 
     * @return
     */
    public static TimerView getTimerView() {
        return (TimerView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ID);
    }

    /**
     * Inner class - this draws the time on the canvas.
     */
    private class TimerCanvas extends Canvas {
        public TimerCanvas(Composite parent, int style) {
            super(parent, style);
            addPaintListener(new PaintListener() {
                public void paintControl(PaintEvent e) {
                    if (gameId != null) {
                        Color oldColor = e.gc.getForeground();
                        Font oldFont = e.gc.getFont();

                        e.gc.setForeground(new Color(getDisplay(), 0, 0, 0));

                        if (GameManager.getInstance().getGame(gameId).getTime() > 0) {
                            e.gc.setFont(new Font(getDisplay(), "Tahoma", Math.min(getSize().y, getSize().x) / 3, SWT.NORMAL));
                        } else {
                            e.gc.setFont(new Font(getDisplay(), "Tahoma", Math.min(getSize().y, getSize().x) / 6, SWT.NORMAL));
                        }
                        e.gc.drawString(formatTime(), 5, 5);

                        e.gc.setForeground(oldColor);
                        e.gc.setFont(oldFont);
                    }
                }
            });
        }
    }

    /**
     * Pads the provided value so that it is the number of (provided) places. It
     * will just prefix the value with 0s.
     * 
     * @param value
     * @param places
     * @return
     */
    protected final String pad(int value, int places) {
        StringBuffer buff = new StringBuffer(String.valueOf(value));

        while (buff.length() < places) {
            buff = new StringBuffer("0").append(buff);
        }

        return buff.toString();
    }

    /**
     * Format the time (integer representation of seconds) as a MM:SS format.
     * 
     * @return
     */
    protected final String formatTime() {
        int gameTime = GameManager.getInstance().getGame(gameId).getTime();

        if (gameTime > 0) {
            int minutes = gameTime / 60;
            int seconds = gameTime % 60;

            return new StringBuffer(String.valueOf(pad(minutes, 2))).append(":").append(pad(seconds, 2)).toString();
        } else {
            return "GAME OVER";
        }
    }

}
