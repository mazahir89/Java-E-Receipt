
public abstract class Legemiddel {

	protected String navn;
	protected int uniktNummer;
	protected int pris;
	protected static int nesteUnikeNummer = 0;	

	Legemiddel(String navn, int pris) {
		this.navn = navn;
		this.pris = pris;
		uniktNummer = nesteUnikeNummer;
		nesteUnikeNummer++;
	}

	public static void reset() {
		nesteUnikeNummer = 0;
	}

	// get-Methods below:

	public String getNavn() {
		return navn;
	}

	public int getUniktNummer() {
		return uniktNummer;	
	}

	public int getPris() {
		return pris;
	}

	abstract public void printInfo();
	abstract public int getVirkestoff();
}


