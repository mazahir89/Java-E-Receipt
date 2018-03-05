
public class PilleNarkotisk extends LegemiddelNarkotisk implements Piller {

	protected int antallPiller, stoffPerPille;

	PilleNarkotisk(String navn, int pris, int narkotisk, int antallPiller, int stoffPerPille) {
		super(navn, pris, narkotisk);
		this.antallPiller = antallPiller;
		this.stoffPerPille = stoffPerPille;
	}

	public void printInfo() {
		System.out.println(uniktNummer + ", " + navn + ", pille, a, " + pris + ", " + antallPiller + ", " + stoffPerPille + ", " + narkotisk);
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
