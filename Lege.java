
class Lege implements Avtale, ComparableLik<Lege> {

	protected String navn, uniktNavn, avtalenummer;
	protected EldsteForstReseptListe resepter;

	Lege(String navn, String uniktNavn, String avtalenummer) {
		this.navn = navn;
		this.uniktNavn = uniktNavn;
		this.avtalenummer = avtalenummer;
		this.resepter = new EldsteForstReseptListe();
	}

	public void skrivResept(Resept r) {
		resepter.settInn(r);
	}

	public void printInfo() {
		System.out.println(uniktNavn + ", " + avtalenummer);
	}
	
	public boolean harNarkotiskResept() {
		for (Resept r : resepter) {
			if (r.getLegemiddel() instanceof LegemiddelNarkotisk) {
				return true;
			}
		}
		return false;
	}
	
	public boolean samme(String navn) {
		return ((this.uniktNavn).equals(navn));
	}

	public String getAvtalenummer() {
		return avtalenummer;
	}

	public int compareTo(Lege l) {
		return ((this.uniktNavn).compareTo(l.uniktNavn));
	}

	public String getUniktNavn() {
		return uniktNavn;
	}

	public String getNavn() {
		return navn;
	}
	
	/// Statistikk oblig 7 ///
	
	public void statistikk3() {
		int antallResepter = 0;
		int totaleVirkestoff = 0;
		int fraPiller = 0;
		int fraMikstur = 0;
		System.out.println("Alle miksturer fra legen: \n");
		for (Resept r : resepter) {
			Legemiddel lm = r.getLegemiddel();
			totaleVirkestoff += lm.getVirkestoff();
			if (lm instanceof Miksturer) {
				fraMikstur += lm.getVirkestoff();
				r.printInfo();
			}
			else {
				fraPiller += lm.getVirkestoff();
			}
		}
		System.out.println("totale virkestoff: " + totaleVirkestoff);
		System.out.println("fra piller: " + fraPiller);
		System.out.println("fra miksturer: " + fraMikstur);		
	}
	
}
