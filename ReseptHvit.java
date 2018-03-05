
public class ReseptHvit extends Resept {

	protected int pris;

	ReseptHvit(Legemiddel legemiddel, Lege lege, int eiernummer, int reit, int pris) {
		super(legemiddel, lege, eiernummer, reit);
		this.pris = pris;
	}	
	
	public int getPris() {
		return pris;
	}

	public void printInfo() {
		System.out.println(uniktNummer + ", hvit, " + eiernummer + ", " + lege.getUniktNavn() + ", " + legemiddel.getUniktNummer() + ", " + reit);
	}
}
