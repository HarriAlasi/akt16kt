package kodu2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;


public class RegexTest {

    @Test
    public void test00_demoCase() {
    	Regex r = Regex.repetition(
    			Regex.concatenation(
    					Regex.letter('a'),
    					Regex.alternation(
    							Regex.letter('b'),
    							Regex.epsilon()
    							)
    					)
    			);
    	
    	assertTrue(r.toString().equals("(a(b|ε))*"));
    	
    	assertTrue(r.matchesEmptyWord());
    	assertTrue(r.matchesInfinitelyManyWords());
    }

    @Test
    public void test01_toStringOperators() {
    	checkToString("ε", "ε");
        checkToString("a", "a");
        checkToString("a|b", "a|b", "(a|b)");
        checkToString("ab", "ab", "(ab)");
        checkToString("a*", "a*", "(a)*");
    }

    @Test
    public void test02_toStringSimpleCombinations() {
        checkToString("ε|a", "ε|a", "(ε|a)");
        checkToString("(a|b)*", "(a|b)*");
        checkToString("(a|(b|ε))*", "(a|(b|ε))*", "(a|b|ε)*");
        
    }

    @Test
    public void test03_toStringCombinations() {
        checkToString("(a*)*", "a*", "(a*)*", "((a)*)*");
        checkToString("a|b|ε", "a|b|ε", "(a|b)|ε", "(a|(b|ε))","((a|b)|ε)");
        checkToString("abc|b(xy|ε)", "abc|b(xy|ε)", "((a(bc))|(b((xy)|ε)))", "((ab)c)|(b((xy)|ε))");
    }

    @Test
    public void test04_matchesEmptyWordOperators() {
        checkMatchesEmpty("ε", true);
        checkMatchesEmpty("a", false);
        checkMatchesEmpty("ε|a", true);
        checkMatchesEmpty("εa", false);
        checkMatchesEmpty("a*", true);
    }

    @Test
    public void test05_matchesEmptyWordSimpleCombinations() {
        checkMatchesEmpty("a*|ε", true);
        checkMatchesEmpty("aε*|a", false);
        checkMatchesEmpty("(a|(b|ε))*", true);
    }


    @Test
    public void test06_matchesManyOperators() {
        checkMatchesMany("ε", false);
        checkMatchesMany("a", false);
        checkMatchesMany("a|b", false);
        checkMatchesMany("ab", false);
        checkMatchesMany("a*", true);
    }

    @Test
    public void test07_matchesManySimpleCombinations() {
        checkMatchesMany("aa*", true);
        checkMatchesMany("(a|b)*", true);
        checkMatchesMany("(a|(b|ε))*", true);
        checkMatchesMany("ab|a", false);
        checkMatchesMany("a(b|a)ε", false);
    }


    @Test
    public void test08_matchesManyEmptyStar() {
        checkMatchesMany("ε*", false);
        checkMatchesMany("(ε|ε)*", false);
        checkMatchesMany("ab|ε*", false);
        checkMatchesMany("(εε)*", false);
        checkMatchesMany("(ab|ε)*", true);
    }

//    // big bonus!
//    @Test
//    public void test09_simplify() {
//        checkToString("aa", "aa");
//        checkToString("aε", "a");
//        checkToString("(a*)*", "a*");
//        checkToString("(a*|a)", "a*");
//        checkToString("a|a", "a");
//        checkToString("aa*", "aa*");
//        checkToString("a|b|ε", "a|b|ε");
//    }


    private void checkToString(String originalRegex, String... expectedResults) {

        Regex originalTree = RegexParser.parse(originalRegex);
        String actualResult = originalTree.toString();

        // vaata, kas mõni match on täpne - sulud või mitte
        for (String expectedResult : expectedResults) {
            if (actualResult.equals(expectedResult)) {
                return;
            }
        }

        fail("Tulemuseks saadud '" + actualResult + "' pole oodatud kujul");

    }


    private void checkMatchesEmpty(String originalRegex, boolean expected) {

        Regex originalTree = RegexParser.parse(originalRegex);
        boolean actualResult = originalTree.matchesEmptyWord();

        assertEquals(originalRegex + " oodatud: " + expected, expected, actualResult);
    }


    private void checkMatchesMany(String originalRegex, boolean expected) {

        Regex originalTree = RegexParser.parse(originalRegex);
        boolean actualResult = originalTree.matchesInfinitelyManyWords();

        assertEquals(originalRegex + " oodatud: " + expected, expected, actualResult);
    }
}