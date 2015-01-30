package com.intere.rcp.boggle.ui.components;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;

/**
 * This class is responsible for providing the "Rotation" animation for the board.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class RotationAnimation {
    
    
    /**
     * This method will perform the rotation for you.
     * @param canvas
     */
    public static void rotateBoggleCanvas(BoggleCanvas canvas) {
        Image snapshot = new Image(PlatformUI.getWorkbench().getDisplay(), canvas.getBounds());
        canvas.getGc().copyArea(snapshot, 0, 0);
        
        int scaleX = (int)(canvas.getBounds().x * 0.9);
        int scaleY = (int)(canvas.getBounds().y * 0.9);
        
        snapshot.getImageData().scaledTo(scaleX, scaleY);
        
        rotateBoggleCanvas(canvas, snapshot, 0);
    }

    /**
     * The Slave method that will perform the "rotation" of the canvas.
     * @param canvas
     * @param snapshot
     * @param angle
     */
    private static void rotateBoggleCanvas(final BoggleCanvas canvas, final Image snapshot, int angle) {
        
        if(angle>=90) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    canvas.redraw();
                    canvas.update();
                }
            });
        } else {
            
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    int buffX = (canvas.getBounds().x - snapshot.getBounds().x)/2;
                    int buffY = (canvas.getBounds().y - snapshot.getBounds().y)/2;
                    canvas.getGc().drawImage(snapshot, buffX, buffY);
                }
            });
            
            Thread.currentThread();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            rotateBoggleCanvas(canvas, snapshot, angle+5);
        }
    }
    
    
    
    
}
