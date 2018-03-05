
public class ReseptBlaa extends Resept {

	ReseptBlaa(Legemiddel legemiddel, Lege lege, int eiernummer, int reit) {
		super(legemiddel, lege, eiernummer, reit);
	}

	public int getPris() {
		return 0;
	}

	public void printInfo() {
		System.out.println(uniktNummer + ", blå, " + eiernummer + ", " + lege.getUniktNavn() + ", " + legemiddel.getUniktNummer() + ", " + reit);
	}	
}
