/**
 * 
 */
package kodu1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author harri
 *
 */
public class AKTKi {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static int i;
	public static String out = "";
	static String[] reaOsad;
	static String[] avaldiseOsad;
	static HashMap<String,Integer> muutujad = new HashMap<String, Integer>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		loeFaili(br);
		br.close();
	}

		public static void loeFaili(BufferedReader br) throws Exception {
			String rida;

			while ((rida = br.readLine()) != null) {
				if(rida.length() > 0) {
					rida.trim();
					if (rida.startsWith("#")) {
						//System.out.println("Kommentaaririda");
					}else if (rida.startsWith("print")) {
						System.out.println(avaldiseLahutamine(rida.replace("print", "")));
					}else if (rida.contains("=")) {
						reaOsad = rida.split("=");
						//System.out.println(Arrays.asList(reaOsad));
						//System.out.println("Muutuja "+reaOsad[0]);
						if (reaOsad[0].trim().length()!=1 || reaOsad.toString().matches("^[a-zA-Z]*$")) {
							System.out.println("Viga, muutuja pikkus on "+reaOsad[0].length()+" "+ reaOsad[0]);
							throw new Exception("Muutujanimi on liiga pikk ja/või ei koosne lubatud sümbolist. Lubatud sümbolid on a-z ja A-Z ning muutujanimi ei tohi olla pikem kui 1 sümbol");
						}
						muutujad.put(reaOsad[0].trim(), avaldiseLahutamine(reaOsad[1].trim()));	
						//System.out.println("muutuja " +reaOsad[0].trim()+" väärtus on "+muutujad.get(reaOsad[0]));
					}else {
						throw new Exception("Tundmatu käsk");
					}
				}
			}
		}

		
	public static int avaldiseLahutamine(String rida){
		avaldiseOsad = rida.trim().split("(?<=[-+])|(?=[-+])|[#]");
		String[] trimmitud = new String[avaldiseOsad.length];
		for (i = 0;i<avaldiseOsad.length;i++) {
			try {
				Integer.parseInt(avaldiseOsad[i].trim());
				trimmitud[i] = avaldiseOsad[i].trim();

			}catch(NumberFormatException e){
				if (avaldiseOsad[i].trim().matches("[+-]")) {
					trimmitud[i] = avaldiseOsad[i].trim();
					//System.out.println(trimmitud[i]);
				}else{
					try {
						trimmitud[i] = String.valueOf(muutujad.get(avaldiseOsad[i].trim()));
					}catch (NumberFormatException f){
						trimmitud[i] = String.valueOf(muutujad.get(trimmitud[i]));
					}
				}
			}
			//System.out.println(avaldiseOsad[i].trim());
		}
		
		//System.out.println(Arrays.asList(trimmitud));
	
			int result = Integer.parseInt(trimmitud[0]);
			//System.out.println("Catch 87"+trimmitud[0]);
			i=1;
			
		//System.out.println(Arrays.asList(trimmitud));
		
		while(i<trimmitud.length) {
			try {
				result += Integer.parseInt(trimmitud[i]);
			}catch (Exception e){
				//System.out.println("Catch 95"+trimmitud[i]);
				if (trimmitud[i].contentEquals("+")) {
					result += Integer.parseInt(trimmitud[i+1]);
				}else if (trimmitud[i].contentEquals("-")){
					//System.out.println(trimmitud[i+1]+" on praegu i+1 koht");
					result -= Integer.parseInt(trimmitud[i+1]);
					//System.out.println("Praegune tulemus"+result);
				}
				else {//kommentaar
					
				}
				i++;
			}
				i++;
			}
		//System.out.println(result);
		return result;
	}
}
