
public class MiksturVanedannende extends LegemiddelVanedannende implements Miksturer {

	protected int stoffPerVolum, volum;

	MiksturVanedannende(String navn, int pris, int vanedannende, int volum, int stoffPerVolum) {
		super(navn, pris, vanedannende);
		this.volum = volum;
		this.stoffPerVolum = stoffPerVolum;
	}

	public void printInfo() {
		System.out.println(uniktNummer + ", " + navn + ", mikstur, b, " + pris + ", " + volum + ", " + stoffPerVolum + ", " + vanedannende);
	}

	public int getStoffPerVolum() {
		return stoffPerVolum;
	}

	public int getVolum() {
		return volum;
	}

	public int getVirkestoff() {
		return volum*stoffPerVolum;
	}
}
