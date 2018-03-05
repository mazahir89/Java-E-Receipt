
public class EldsteForstReseptListe extends EnkelReseptListe {

	public boolean settInn(Resept r) {
		
		Node inn = new Node(r);

		if (tom()) {
			foerste = inn;
		}
		else {
			siste.neste = inn;
		}
		siste = inn;
		return true;
	}
}
