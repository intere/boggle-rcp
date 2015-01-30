package com.intere.rcp.boggle.ui.test.handlers;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;

import com.intere.rcp.boggle.core.interfaces.IComputerPlayer;
import com.intere.rcp.boggle.core.managers.ComputerPlayerLoader;
import com.intere.rcp.boggle.core.model.descriptors.ComputerPlayerDescriptor;

/**
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class LoadComputerPlayerHandler extends AbstractHandler {

    public Object execute(ExecutionEvent event) throws ExecutionException {

        List<ComputerPlayerDescriptor> comps = ComputerPlayerLoader.getInstance().getComputerPlayerDescriptors();

        for (ComputerPlayerDescriptor desc : comps) {
            try {
                IComputerPlayer cp = desc.createComputerPlayer();
                cp.getPlayer().setUsername(desc.getName());
                cp.login();
            } catch (CoreException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // TODO Auto-generated method stub
        return null;
    }

}
