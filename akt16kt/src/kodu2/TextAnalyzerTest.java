package kodu2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TextAnalyzerTest {
	
	@Test
	public void test00_demoCase() {
		TextAnalyzer ta = new TextAnalyzer(
				  "Mina olen Kalle Kulbok ja mu telefoninumber on 5556 4272.\n"
				+ "Mina olen Peeter Peet ja mu telefoninumber on 5234 567.\n"
				+ "Mari Maasikas siin, mu number on 6723 3434.\n"
				+ "Tere, olen Jaan Jubin numbriga 45631643.\n");
		
		String peetriNr = ta.getPhoneNumbers().get("Peeter Peet");
		String jaaniNr = ta.getPhoneNumbers().get("Jaan Jubin");
		
		assertEquals("5234567", peetriNr);
		assertEquals("45631643", jaaniNr);
		assertEquals("Mina olen <nimi> ja mu telefoninumber on <telefoninumber>.\n" + 
				"Mina olen <nimi> ja mu telefoninumber on <telefoninumber>.\n" + 
				"<nimi> siin, mu number on <telefoninumber>.\n" + 
				"Tere, olen <nimi> numbriga <telefoninumber>.\n", 
				ta.anonymize());
	}

    @Test
    public void test01_getPhoneNumbers() {
        checkPhoneNumber("Mart Laar, tel: 42312345, e-mail:laar@laar.ee", "Mart Laar","42312345");
        checkPhoneNumber("Suvaline teks ja *+*asf,, Jaak Saar veel miskit +afV+++*., 1234 4444.", "Jaak Saar","12344444");
    }

    @Test
    public void test02_anonymize() {
        checkAnon("Mart Laar, tel: 42312345, e-mail:laar@laar.ee", "<nimi>, tel: <telefoninumber>, e-mail:laar@laar.ee");
        checkAnon("Suvaline teks ja *+*asf,, Jaak Saar veel miskit +afV+++*., 1234 4444.", "Suvaline teks ja *+*asf,, <nimi> veel miskit +afV+++*., <telefoninumber>.");
    }


	private void checkPhoneNumber(String input, String nimi, String expected) {
		TextAnalyzer ta = new TextAnalyzer(input);
        String actual = ta.getPhoneNumbers().get(nimi);

		assertEquals(actual + " pole õigel kujul", expected, actual);
	}

    private void checkAnon(String input, String expected) {
        TextAnalyzer ta = new TextAnalyzer(input);
        String actual = ta.anonymize();

        assertEquals(actual + " pole õigel kujul", expected, actual);
    }
}