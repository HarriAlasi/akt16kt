/**
 * 
 */
package kodu2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.*;

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
	String anonymized;
	public TextAnalyzer(String string) {
		lines = string.split(System.getProperty("line.separator"));
		for (int i = 0; i < lines.length;i++) {
			MatchNamesAndNumbers(lines[i]);
		}
	}
	public Object anonymize() {
			System.out.println(anonymized);
		return null;
	}

	/**
	 * @param string
	 */
	private void MatchNamesAndNumbers(String string) {
		anonymized = string;
		Pattern namesAndNumbersPattern = Pattern.compile("[A-Z][a-z]* [A-Z][a-z]*|\\d{3,4}\\s\\d{3,4}|\\d{4,8}");
		int i = 0;
		Matcher names = null;
		ArrayList<String> info = new ArrayList<String>();
		//System.out.println(info);
		String[] lines;
		lines = string.split("\n");
		//names = lines.split("\\s[a-z]*");
		for (i = 0;i<lines.length;i++) {
			lines[i] = lines[i].replace(".", "");
			names = namesAndNumbersPattern.matcher(lines[i]);
			while (names.find()) {
				//System.out.println(names.group());
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
				//System.out.println(Arrays.asList(info));
			}

		}
		String[] abi = new String[ info.size() ];
		info.toArray(abi);
		for (i = 0;i<info.size();i = i+2) {
			namesAndNumbers.put(abi[i], abi[i+1]);
		}
		//System.out.println(namesAndNumbers);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return 
	 * @return
	 */
	public  Map<String,String> getPhoneNumbers() {
		return namesAndNumbers;
	}

}
