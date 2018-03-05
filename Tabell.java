import java.util.Iterator;

class Tabell<T> implements AbstraktTabell<T> {
	
	private T[] alle;
	
	Tabell(int stoerrelse) {
		alle = (T[]) new Object[stoerrelse];
	}
	
	public boolean settInn(T objekt, int indeks) {
		
		resize(indeks + 1);

		if (tom(indeks)) {
			alle[indeks] = objekt;
			return true;
		}
		else {
			return false;
		}
	}
	
	public T finnObjekt(int indeks) {
		return alle[indeks];	
	}

	public boolean tom(int indeks) {
		return (alle[indeks] == null);
	}

	private void resize(int size) {

		if (alle.length >= size) {
			return;
		}

		T[] ny = (T[]) new Object[size];
		for (int i = 0 ; i < alle.length ; i++) {	
			ny[i] = alle[i];
		}
		alle = ny;

	}
	
	////////// Iterator //////////		

	public Iterator<T> iterator() {
		return new TabellIterator();	
	}
	
	class TabellIterator implements Iterator<T> {
		
		int indeks = 0;
		
		public boolean hasNext() {
			return (indeks < alle.length);
		}
		
		public T next() {
			return alle[indeks++];
		}
		
		public void remove() {}
	}
}
