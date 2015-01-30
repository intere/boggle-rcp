package com.intere.rcp.boggle.core.util;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Validates a board rotation.
 *
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class BoardUtilsTest {

    /**
     * S F E G 
     * N M G O 
     * P S F N 
     * A R T Y
     */
    private static final String TEST_BOARD = "SNPAFMSREGFTGONY";
    // ORIGINAL TABLE:
    // S = (0,0), N = (0,1), P = (0,2), A = (0,3)
    // F = (1,0), M = (1,1), S = (1,2), R = (1,3)
    // E = (2,0), G = (2,1), F = (2,2), T = (2,3)
    // G = (3,0), O = (3,1), N = (3,2), Y = (3,3)

    /**
     * G O N Y
     * E G F T
     * F M S R
     * S N P A
     */
    private static final String ROTATE_ONCE = "GEFSOGMNNFSPYTRA";
    // ORIGINAL TABLE MAPPINGS:
    // G = (3,0), E = (2,0), F = (1,0), S = (0,0)
    // O = (3,1), G = (2,1), M = (1,1), N = (0,1)
    // N = (3,2), F = (2,2), S = (1,2), P = (0,2)
    // Y = (3,3), T = (2,3), R = (1,3), A = (0,3)
    
    @Test
    public void testRotateBoard() throws Exception
    {
        String test = BoardUtils.rotate(TEST_BOARD);
        
        Assert.assertEquals(ROTATE_ONCE, test);
    }
}
