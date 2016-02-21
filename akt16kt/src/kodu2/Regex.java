/**
 * 
 */
package kodu2;
public class Regex {
	public static Regex regexFinal = new Regex();
	/**
	 * @param string
	 */
	static Regex regex;
	static String regex1 = "";


	/**
	 * @param args
	 */
	public Regex() {

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	/**
	 * @param c
	 * @return 
	 * @return
	 */
	public static  Regex letter(char c) {
		regex1 = ""+c;
		return regexFinal;
	}

	/**
	 * @return
	 */
	public static Regex epsilon() {
		regex1 = "ε";
		return regexFinal;
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
	public static Regex alternation(Object c, Object d) {
		regex1 = "("+c+"|"+d+")";
		System.out.println("Praegune regex peale alterationit" + regex1);
		return regexFinal;
	}

	/**
	 * @param c
	 * @param d
	 * @return
	 */
	public static Regex concatenation(Object c, Object d) {
		// TODO Auto-generated method stub
		regex1 = ""+c+d;
		System.out.println("Praegune regex peale concatenationit" + regex1);
		return regexFinal;
	}

	/**
	 * @param object
	 * @param concatenation
	 * @return
	 */
	public static Regex repetition(Object object) {
		// TODO Auto-generated method stub
		regex1 = "("+regex1+")*";
		System.out.println("Praegune regex peale repetitionit" + regex1);
		return regexFinal;
	}
	/**
	 * @return
	 */
	public String toString() {
	return regex1;
	}

}
