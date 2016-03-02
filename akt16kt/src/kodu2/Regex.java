/**
 * 
 */
package kodu2;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Regex {
	public static Regex regexFinal = new Regex();
	/**
	 * @param string
	 */
	static Regex regex;
	static String letter;
	static String EPS = "\u03B5";
	static String regex1 = "";
	private static Regex regexEPS;
	private static Regex regexLetter;
	private static String alt1;
	private static String alt2;
	static ArrayList<String> letters = new ArrayList<String>();
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
		letters.add(c+"");
		//System.out.println(c);
		//System.out.println(letters);
		return regexLetter;
	}

	/**
	 * @return
	 */
	public static Regex epsilon() {
		letters.add(EPS);
		//System.out.println(""\u03B5"");
		return regexEPS;
	}


	/**
	 * @return
	 */
	public boolean matchesEmptyWord() {
		boolean val = false;
		//System.out.println(regexFinal.toString()+" on regexFinal");
		if (regexFinal.toString().length()==2 && regexFinal.toString().endsWith("*")|| 
			regexFinal.toString().endsWith(")*") && regexFinal.toString().startsWith("(")||
			regexFinal.toString().equals(EPS) ||
			regexFinal.toString().startsWith(EPS+"|") || 
			regexFinal.toString().endsWith("|"+EPS))
		{
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
	static int i;
	public static Regex alternation(Regex c, Regex d) {
		if (c == regexLetter || c == regexEPS){
			//System.out.println(c);
			alt1 = letters.get(letters.indexOf(c+""));
			}
		if (d == regexLetter || d == regexEPS){
			i = letters.indexOf(d);
			alt2 = letters.get(i);	
		}
		
		regex1 = "("+alt1+"|"+alt2+")";
	//	System.out.println("Praegune regex peale alterationit" + regex1);
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
		//System.out.println("Praegune regex peale concatenationit" + regex1);
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
		//System.out.println("Praegune regex peale repetitionit" + regex1);
		return regexFinal;
	}
	/**
	 * @return
	 */
	public String toString() {
	return regex1;
	}
	/*public String toString(String string) {
	return string;
	}*/
}
