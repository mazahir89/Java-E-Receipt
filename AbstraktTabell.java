import java.util.Iterator;

interface AbstraktTabell<T> extends Iterable<T> {
	
	public boolean settInn(T objekt, int indeks);
	public T finnObjekt(int indeks);
	
}
