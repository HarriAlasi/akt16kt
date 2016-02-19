/**
 * 
 */
package kodu2;

/**
 * @author harri
 *
 */
import java.util.regex.Pattern;
public class Regex {
	public static Regex regexFinal = new Regex();
	/**
	 * @param string
	 */
	Pattern regex;
	static String regex1 = "";


	/**
	 * @param args
	 */
	public Regex() {

		regex = Pattern.compile("");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	/**
	 * @param c
	 * @return 
	 * @return
	 */
	public static  char letter(char c) {
		return c;
	}

	/**
	 * @return
	 */
	public static char epsilon() {
		// TODO Auto-generated method stub
		return 'ε';
	}


	/**
	 * @return
	 */
	public boolean matchesEmptyWord() {
		boolean val = false;
		if (regexFinal.toString().contains("*")) {
			val = true;
		}
		return val;
	}

	/**
	 * @return
	 */
	public boolean matchesInfinitelyManyWords() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @param c
	 * @param epsilon
	 * @return
	 */
	public static Object alternation(Object c, Object d) {
		regex1 = "("+c+"|"+d+")";
		return Pattern.compile("("+c+"|"+d+")");
	}

	/**
	 * @param c
	 * @param d
	 * @return
	 */
	public static Object concatenation(Object c, Object d) {
		// TODO Auto-generated method stub
		regex1 = ""+c+d;
		return Pattern.compile(""+c+d);
	}

	/**
	 * @param object
	 * @param concatenation
	 * @return
	 */
	public static Regex repetition(Object object) {
		// TODO Auto-generated method stub
		regex1 = "("+regex1+")*";
		return regexFinal;
	}
	/**
	 * @return
	 */
	public String toString() {
	return regex1;
	}

}
