/**
 * 
 */
package kodu2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author harri
 *
 */
public class TextAnalyzer {
	
	static HashMap<String,String> namesAndNumbers = new HashMap<String, String>();
	/**
	 * @param string
	 */
	String[] lines;
	String anonymized = "";
	public TextAnalyzer(String string) {
		lines = string.split("\n");
		for (int i = 0; i < lines.length;i++) {
			MatchNamesAndNumbers(lines[i]);
		}
	}
	public String anonymize() {
		return anonymized;
	}
	
	/**
	 * @param string
	 */
	private void MatchNamesAndNumbers(String string) {
		anonymized += string+"\n";
		Pattern namesAndNumbersPattern = Pattern.compile("[A-Z][a-z]* [A-Z][a-z]*|\\d{3,4}\\s\\d{3,4}|\\d{4,8}");
		int i = 0;
		Matcher names = null;
		ArrayList<String> info = new ArrayList<String>();
		
			names = namesAndNumbersPattern.matcher(string);
			while (names.find()) {
				if (names.group().matches("\\d+ \\d+")){
					info.add(names.group().replaceAll("\\s+",""));
					anonymized = anonymized.replace(names.group(), "<telefoninumber>");
				}else if(names.group().matches("\\d+")){
					info.add(names.group());
					anonymized = anonymized.replace(names.group(), "<telefoninumber>");
				}else {
					anonymized = anonymized.replace(names.group(0), "<nimi>");
					info.add(names.group());
				}
			}

		String[] abi = new String[ info.size() ];
		info.toArray(abi);
		for (i = 0;i<info.size();i = i+2) {
			namesAndNumbers.put(abi[i], abi[i+1]);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public  Map<String,String> getPhoneNumbers() {
		return namesAndNumbers;
	}

}
