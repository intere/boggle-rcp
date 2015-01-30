package com.intere.rcp.boggle.core.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;

import com.intere.rcp.boggle.core.test.GameFactory;

import static org.junit.Assert.*;


/**
 * Tests the marshalling/unmarshalling of the Game object.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 *
 */
public class GameTest {
    
    private JAXBContext context;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    
    
    @Before
    public void setUp() throws Exception
    {
        context = JAXBContext.newInstance(Game.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        unmarshaller = context.createUnmarshaller();
    }
    
    
    @Test
    public void testMarshalAndUnmarshal() throws Exception
    {
        Game expected = GameFactory.createGame();
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        marshaller.marshal(expected, out);
        
        System.out.println(out.toString());
        
        Game actual = (Game) unmarshaller.unmarshal(new ByteArrayInputStream(out.toByteArray()));
        
        assertNotNull(actual);
    }
    
}
