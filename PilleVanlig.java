
public class PilleVanlig extends Legemiddel implements Piller {

	protected int antallPiller, stoffPerPille;

	PilleVanlig(String navn, int pris, int antallPiller, int stoffPerPille) {
		super(navn, pris);
		this.antallPiller = antallPiller;
		this.stoffPerPille = stoffPerPille;
	}

	public void printInfo() {
		System.out.println(uniktNummer + ", " + navn + ", pille, c, " + pris + ", " + antallPiller + ", " + stoffPerPille);
	}

	public int getAntallPiller() {
		return antallPiller;
	}

	public int getStoffPerPille() {
		return stoffPerPille;
	}

	public int getVirkestoff() {
		return antallPiller*stoffPerPille;
	}
}
