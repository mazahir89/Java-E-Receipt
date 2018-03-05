
public class LegemiddelVanedannende extends Legemiddel {

	protected int vanedannende;

	LegemiddelVanedannende(String navn, int pris, int vanedannende) {
		super(navn, pris);
		this.vanedannende = vanedannende;
	}

	public int getVanedannende() {
		return vanedannende;
	}

	public void printInfo() {}
	
	public int getVirkestoff() {
		return 0;
	}
}
