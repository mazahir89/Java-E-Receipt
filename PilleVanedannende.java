
public class PilleVanedannende extends LegemiddelVanedannende implements Piller {

	protected int antallPiller, stoffPerPille;

	PilleVanedannende(String navn, int pris, int vanedannende, int antallPiller, int stoffPerPille) {
		super(navn, pris, vanedannende);
		this.antallPiller = antallPiller;
		this.stoffPerPille = stoffPerPille;
	}

	public void printInfo() {
		System.out.println(uniktNummer + ", " + navn + ", pille, b, " + pris + ", " + antallPiller + ", " + stoffPerPille + ", " + vanedannende);
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
