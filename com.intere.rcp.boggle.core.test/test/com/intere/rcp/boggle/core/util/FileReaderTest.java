package com.intere.rcp.boggle.core.util;

import org.junit.Assert;
import org.junit.Test;

public class FileReaderTest {

    @Test
    public void testReadFile() throws Exception
    {
        String filename="/resources/5x5.txt";
        
        StringBuffer buff = FileReader.readBundleFile(filename);
        
        Assert.assertNotNull(buff);
        Assert.assertTrue(buff.length()>0);
    }

}
