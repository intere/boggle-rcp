package com.intere.rcp.boggle.core.util;

import java.io.IOException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.intere.rcp.boggle.core.BoggleCorePlugin;
import com.intere.rcp.boggle.core.interfaces.AbstractDiceLoader;

/**
 * Dice Loader that loads the dice from a file (in this plugin).
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class FileBasedDiceLoader extends AbstractDiceLoader {

    private StringBuffer diceText;

    public String getDice() {
        return diceText.toString();
    }

    /**
     * This method will load the dice (specified by the filename in the dice
     * descriptor private member data).
     * 
     * @see FileReader#readBundleFile(String)
     * @see #getDiceDescriptor()
     * 
     */
    public void loadDice() {
        try {
            diceText = FileReader.readBundleFile(getDiceDescriptor().getFilename());
        } catch (IOException e) {
            BoggleCorePlugin.getDefault().logErrorMessage("Error loading dice from file: " + getDiceDescriptor().getFilename(), e);
        }
    }

}
