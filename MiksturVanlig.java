
public class MiksturVanlig extends Legemiddel implements Miksturer {

	protected int stoffPerVolum, volum;

	MiksturVanlig(String navn, int pris, int volum, int stoffPerVolum) {
		super(navn, pris);
		this.volum = volum;
		this.stoffPerVolum = stoffPerVolum;
	}

	public void printInfo() {
		System.out.println(uniktNummer + ", " + navn + ", mikstur, c, " + pris + ", " + volum + ", " + stoffPerVolum);
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
