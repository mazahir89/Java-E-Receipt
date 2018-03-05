import java.util.Iterator;

class SortertEnkelListe<T extends ComparableLik> extends ItererbarListe<T> implements AbstraktSortertEnkelListe<T> {
	
	public boolean settInn(T t) {
				
		// Node med objektet som skal settes inn.
		Node inn = new Node(t);
		
		Node forrige = null;
		Node n = foerste;
		
		while (n != null) {
	
			if (t.compareTo(n.objekt) <= 0) {
				settInnBak(forrige, inn);
				return true;
			}
	      forrige = n;
			n = n.neste;
		}
		settInnBak(forrige, inn);
		return true;
	}

	private void settInnBak(Node posisjon, Node n) {
		if (posisjon == null) {
			n.neste = foerste;
			foerste = n;
		}
		else {
			n.neste = posisjon.neste; 
			posisjon.neste = n;
 		}
   }

	public T finnElement(String noekkel) {
		for (T t : this) {
			if (t.samme(noekkel)) {
				return t;
			}
		}
		return null;
	}

	public boolean inneholder(String s) {
		return (finnElement(s) != null);
	}
}
