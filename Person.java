public class Person {

	protected String navn, foedselsnummer, adresse, postnummer;
	protected YngsteForstReseptListe resepter;	
	protected static int nesteUnikeNummer = 0;
	protected int uniktNummer;

	Person(String navn, String foedselnummer, String adresse, String postnummer) {	
			this.navn = navn;
			this.foedselsnummer = foedselnummer;
			this.adresse = adresse;
			this.postnummer = postnummer;
			this.resepter = new YngsteForstReseptListe(); 
			uniktNummer = nesteUnikeNummer;
			nesteUnikeNummer++;
	}

	public boolean harIngenResept() {
		return resepter.tom();
	}
	
	public boolean harNarkotiskResept() {
		for (Resept r : resepter) {
			if (r.getLegemiddel() instanceof LegemiddelNarkotisk) {
				return true;
			}
		}
		return false;
	}

	public void printInfo() {
		System.out.println(uniktNummer + ", " + navn + ", " + foedselsnummer + ", " + adresse + ", " + postnummer);
	}

	public void printResepter() {
		if (harIngenResept()) {
			System.out.println("personen har ingen resepter!");
		}
		System.out.println("\n PERSONENS RESEPTER \n");
		for (Resept r : resepter) {
			if (r instanceof ReseptBlaa) {
				System.out.println( "nummer " + r.getUniktNummer() + ": Legemiddel: " + r.getLegemiddel().getNavn() + " reit: " + r.getReit() + " (farge: blÃ¥)");
			}			
			else {
				System.out.println( "nummer " + r.getUniktNummer() + ": Legemiddel: " + r.getLegemiddel().getNavn() + " reit: " + r.getReit() + " (farge: hvit)");
			}
		}
		System.out.println("");
	}

	public static void reset() {
		nesteUnikeNummer = 0;
	}

	public void mottaResept(Resept r) {
		resepter.settInn(r);
	}

	// get-Methods:

	public String getNavn() {
		return navn;
	}

	public String getFoedselsnummer() {
		return foedselsnummer;
	}

	public String getAdresse() {
		return adresse;
	}

	public String getPostnummer() {
		return postnummer;
	}

	public int getUniktNummer() {
		return uniktNummer;
	}
	
	/// Statistikk oblig 7 ///
	
	public void statistikk2() {
		if (harIngenResept()) {
			System.out.println("personen har ingen resepter!");
			return;
		}
		int antallBlaaResept = 0;
		for (Resept r : resepter) {
			if (r instanceof ReseptBlaa) {
			r.printInfo();
			antallBlaaResept++;
			}
		}
		if (antallBlaaResept == 0) {
			System.out.println("personen har ingen blå resepter!");
		}
	}
	
}
