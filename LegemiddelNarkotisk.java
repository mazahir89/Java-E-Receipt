
public class LegemiddelNarkotisk extends Legemiddel {

	protected int narkotisk;

	LegemiddelNarkotisk(String navn, int pris, int narkotisk) {
		super(navn, pris);
		this.narkotisk = narkotisk;
	}

	public int getNarkotisk() {
		return narkotisk;
	}

	public void printInfo() {}
	
	public int getVirkestoff() {
		return 0;
	}
}
