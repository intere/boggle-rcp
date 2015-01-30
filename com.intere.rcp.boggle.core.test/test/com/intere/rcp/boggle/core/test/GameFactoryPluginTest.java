package com.intere.rcp.boggle.core.test;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.intere.rcp.boggle.core.model.Game;


/**
 * PluginTest Ensures that we create a valid Game.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class GameFactoryPluginTest {
    
    private Game generated;
    
    private JAXBContext context;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    
    
    @Test
    public void testGenerateGame() throws Exception
    {
        generated = GameFactory.createGame();
        
        assertNotNull(generated);
        assertNotNull(generated.getCreator());
        assertNotNull(generated.getCreator().getUsername());
        
        String xml = marshall();
        
        System.out.println(xml);
    }
    
    
    /**
     * Helper method that will marshall the game object into an XML String.
     * @return
     * @throws JAXBException
     */
    protected String marshall() throws JAXBException
    {
        if(context==null)
        {
            context = JAXBContext.newInstance(Game.class);            
        }
        
        if(marshaller==null)
        {
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        }
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        marshaller.marshal(generated, out);
        
        return out.toString();
    }
}
