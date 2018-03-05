
public class MiksturNarkotisk extends LegemiddelNarkotisk implements Miksturer {

	protected int stoffPerVolum, volum;

	MiksturNarkotisk(String navn, int pris, int narkotisk, int volum, int stoffPerVolum) {
		super(navn, pris, narkotisk);
		this.volum = volum;
		this.stoffPerVolum = stoffPerVolum;
	}

	public void printInfo() {
		System.out.println(uniktNummer + ", " + navn + ", mikstur, a, " + pris + ", " + volum + ", " + stoffPerVolum + ", " + narkotisk);
	}

	public int getStoffPerVolum() {
		return stoffPerVolum;
	}

	public int getVolum() {
		return volum;
	}

	public int getVirkestoff() {
		return stoffPerVolum*volum;
	}
}
