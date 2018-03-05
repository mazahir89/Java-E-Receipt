
import java.util.Iterator;

public class ItererbarListe<T> implements Iterable<T> {

	Node foerste;		
	
	protected class Node {
		
		Node neste;
		T objekt;
		
		Node(T objekt) {
			this.objekt = objekt;
		}
	}

	final public boolean tom() {
		return (foerste == null);
	}

	////////// Iterator //////////
	
	final public Iterator<T> iterator() {
		return new MinIterator();
	}
	
	public class MinIterator implements Iterator<T> {
		
		Node n = foerste;
		
		public boolean hasNext() {
			
			return (n != null);
		}
	
		public T next() {
			
			T t = n.objekt;
			n = n.neste;		
			return t;
		}
		
		public void remove() {}
	}
}
