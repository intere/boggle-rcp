package com.intere.rcp.boggle.ui.components;

import java.awt.Point;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.intere.rcp.boggle.core.model.Position;
import com.intere.rcp.boggle.core.util.BoardUtils;
import com.intere.rcp.boggle.ui.controllers.BoggleCanvasController;

/**
 * This class is the canvas that renders the boggle board.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class BoggleCanvas extends Canvas {

    /** The controller behind the view. */
    private BoggleCanvasController controller;
    
    /** The Graphics Canvas to be drawn on.  */
    private GC gc;

    /**
     * SWT Style constructor.
     * 
     * @param parent
     * @param style
     */
    public BoggleCanvas(Composite parent, int style) {
        super(parent, style);

        controller = new BoggleCanvasController(this);

        addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                gc = e.gc;
                Color oldColor = e.gc.getForeground();
                Font oldFont = e.gc.getFont();

                e.gc.setForeground(new Color(getDisplay(), 0, 0, 0));
                e.gc.setFont(new Font(getDisplay(), "Tahoma", Math.min(getSize().y, getSize().x) / 8, SWT.NORMAL));

                drawBoard(e.gc);
                drawWalk(e.gc);

                e.gc.setForeground(oldColor);
                e.gc.setFont(oldFont);
            }
        });
    }

    /**
     * This method draws the walk if it exists.
     * 
     * @param gc
     */
    protected void drawWalk(GC gc) {
        
        if(controller.getBoard()!=null) {
            // Setup parameters:
            Color prevColor = gc.getForeground();
            Color walkColor = new Color(getDisplay(), 0, 255, 0);        
            int wStep = getSize().x / controller.getBoardSize();
            int hStep = getSize().y / controller.getBoardSize();
            
            if(controller.getWalk()!=null)
            {
                gc.setForeground(walkColor);
                
                Position last = null;
                
                for(Position curr : controller.getWalk())
                {
                    if(last!=null)
                    {
                        int startX = last.getI() * wStep + (wStep/2);
                        int startY = last.getJ() * hStep + (hStep/2);
                        int endX = curr.getI() * wStep + (wStep/2);
                        int endY = curr.getJ() * hStep + (hStep/2);
                        
                        gc.drawLine(startX, startY, endX, endY);
                    }
                    
                    last = curr;
                }
                
                gc.setForeground(prevColor);
            }
        }
    }

    /**
     * This method draws the boggle board for you (no matter what the size).
     * 
     * @param gc
     */
    protected void drawBoard(GC gc) {
        
        if(controller.getBoard()!=null) {        
            int wStep = getSize().x / controller.getBoardSize();
            int hStep = getSize().y / controller.getBoardSize();
            Color prevColor = gc.getForeground();
            Color walkColor = new Color(getDisplay(), 230, 100, 100);
    
            if (controller.getBoard() != null) {
                for (int i = 0; i < controller.getBoardSize(); i++) {
                    for (int j = 0; j < controller.getBoardSize(); j++) {
                        String text = String.valueOf(BoardUtils.getCharAt(controller.getBoard(), i, j));
                        if (text.equals("Q")) {
                            text = "QU";
                        }
    
                        Rectangle r = new Rectangle(i * wStep, j * hStep, wStep, hStep);
                        Point p = findDrawLocation(r, text, gc);
    
                        if (highlight(i, j)) {
                            gc.setForeground(walkColor);
                        }
    
                        gc.setClipping(r);
                        gc.drawString(text, (int) p.getX(), (int) p.getY());
                        gc.setForeground(prevColor);
                        gc.drawRectangle(r);   
                        gc.setClipping((Rectangle)null);
                    }
                }
            }
        }
    }

    /**
     * This method finds you the "perfect" draw location for the given String on
     * the Graphics, given the bounds to work with. It will attempt to "center"
     * the string in the middle of the provided rectangle.
     * 
     * @param bounds
     *            The bounds to center the String within.
     * @param text
     *            The text to be drawn.
     * @param g
     *            The GC to draw on.
     * @return The Point at which to draw the Text.
     */
    public Point findDrawLocation(Rectangle bounds, String text, GC g) {
        Point p = new Point();
        int w = (int) (bounds.width - g.getFontMetrics().getAverageCharWidth() * text.length()) / 2;
        int h = (int) (0 - (g.getFontMetrics().getHeight() / 1.5));

        p.setLocation(bounds.x + w, (bounds.y + bounds.height/2) + h);
        return p;
    }

    /**
     * This method tells you if you need to highlight the character.
     * 
     * @param i
     * @param j
     * @return
     */
    private boolean highlight(int i, int j) {
        if (controller.getWalk() != null) {
            for (Position p : controller.getWalk()) {
                if (p.getI() == i && p.getJ() == j) {
                    return true;
                }
            }
        }
        return false;
    }    

    /**
     * Convenience method to get the board String.
     * 
     * @return
     */
    public String getBoardString() {
        return controller.getBoard();
    }

    public BoggleCanvasController getController() {
        return controller;
    }

    public GC getGc() {
        return gc;
    }
}
