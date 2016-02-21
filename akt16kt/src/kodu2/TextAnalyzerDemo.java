/**
 * 
 */
package kodu2;

/**
 * @author harri
 *
*/

public class TextAnalyzerDemo {
    public static void main(String[] args) {
        TextAnalyzer ta = new TextAnalyzer(
                  "Mina olen Kalle Kulbok ja mu telefoninumber on 5556 4272.\n"
                + "Mina olen Peeter Peet ja mu telefoninumber on 5234 567.\n"
                + "Mari Maasikas siin, mu number on 6723 3434.\n"
                + "Tere, olen Jaan Jubin numbriga 45631643.\n");
        TextAnalyzer ta1 = new TextAnalyzer("Mart Laar, tel: 42312345, e-mail:laar@laar.ee");
        String peetriNr = ta.getPhoneNumbers().get("Peeter Peet");
        String jaaniNr = ta.getPhoneNumbers().get("Jaan Jubin");
        System.out.println(peetriNr); // peab väljastama 5234567
        System.out.println(jaaniNr); // peab väljastama 45631643
        
        System.out.println(ta1.anonymize()); /* peab väljastama:

        Mina olen <nimi> ja mu telefoninumber on <telefoninumber>.
        Mina olen <nimi> ja mu telefoninumber on <telefoninumber>.
        <nimi> siin, mu number on <telefoninumber>.
        Tere, olen <nimi> numbriga <telefoninumber>.
 
        */
    }
}