import java.util.Iterator;

public interface AbstraktSortertEnkelListe<T extends ComparableLik> extends Iterable<T> {
	
	public boolean settInn(T element);
	public T finnElement(String noekkel);

}
