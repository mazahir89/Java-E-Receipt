
public abstract class Resept {

	protected int uniktNummer;
	protected static int nesteUnikeNummer = 0;
	
	protected Legemiddel legemiddel;
	protected Lege lege; 
	protected int eiernummer;
	protected int reit;

	Resept(Legemiddel legemiddel, Lege lege, int eiernummer, int reit) {
		this.legemiddel = legemiddel;
		this.lege = lege;
		this.eiernummer = eiernummer;
		this.reit = reit;
		uniktNummer = nesteUnikeNummer;
		nesteUnikeNummer++;
	}

	public static void reset() {
		nesteUnikeNummer = 0;
	}

	abstract public void printInfo();

	public boolean gyldig() {
		return (reit > 0);
	}

	public Legemiddel hentLegemiddel() {
		reit--;
		return legemiddel;
	}

	// get-Methods below:

	public Legemiddel getLegemiddel() {
		return legemiddel;
	}

	public Lege getLege() {
		return lege;
	}

	public int getEiernummer() {
		return eiernummer;
	}

	public int getReit() {
		return reit;
	}

	public int getUniktNummer() {
		return uniktNummer;
	}

	abstract public int getPris();
}














