
public class YngsteForstReseptListe extends EnkelReseptListe {

	public boolean settInn(Resept r) {
		
		Node inn = new Node(r);
		if (tom()) {
			siste = inn;
		}
		else {
			inn.neste = foerste;
		}
		foerste = inn;
		return true;
	}
}
