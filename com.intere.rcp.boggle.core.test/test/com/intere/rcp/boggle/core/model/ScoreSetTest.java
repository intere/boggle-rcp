package com.intere.rcp.boggle.core.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class is a JUnit Plugin test. It tests the ScoreSet functionality.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class ScoreSetTest {

    public static final String SCORE_SET = "/resources/score-scheme-1.props";

    public static final String[] TEST_WORDS = { "alpha", "bravo", "zulu", "discombobulate", "zenophobe", "fart", "tingle", "juniper", "assimilate" };

    @Test
    public void testFromBundleFile() throws Exception {

        ScoreSet set = ScoreSet.fromBundleFile(SCORE_SET);

        assertNotNull(set);

        for (char i = 'a'; i <= 'z'; i++) {
            assertTrue(set.scoreLetter(i) > 0);
            System.out.println(i + ": " + set.scoreLetter(i));
        }
    }

    @Test
    public void testScoreWords() throws Exception {
        ScoreSet set = ScoreSet.fromBundleFile(SCORE_SET);

        System.out.println("Scoring.......................");

        for (String word : TEST_WORDS) {
            int score = set.scoreWord(word);
            assertTrue(score > 0);

            System.out.println(word + ": " + score);
        }
    }

}
