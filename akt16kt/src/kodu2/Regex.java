/**
 * 
 */
package kodu2;

/**
 * @author harri
 *
 */
public class Regex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static Regex regexFinal;

	/**
	 * @param c
	 * @return
	 */
	public static Object letter(Object c) {
		// TODO Auto-generated method stub
		return c;
	}

	/**
	 * @return
	 */
	public static Object epsilon() {
		// TODO Auto-generated method stub
		return 'ε';
	}


	/**
	 * @return
	 */
	public boolean matchesEmptyWord() {
		boolean isEmpty = false;
		return isEmpty;
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
		return "("+c+"|"+d;
	}

	/**
	 * @param c
	 * @param d
	 * @return
	 */
	public static Object concatenation(Object c, Object d) {
		// TODO Auto-generated method stub
		return "("+regexFinal+")*";
	}

	/**
	 * @param object
	 * @param concatenation
	 * @return
	 */
	public static Regex repetition(Object object) {
		// TODO Auto-generated method stub
		return regexFinal;
	}
	public String toString() {
	return regexFinal.toString();
	}

}
