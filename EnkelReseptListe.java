
import java.util.Iterator;

abstract class EnkelReseptListe extends ItererbarListe<Resept> {

	public class UgyldigReseptnummer extends Exception {
		public int reseptnummer;
		UgyldigReseptnummer(int n) {
         reseptnummer = n;
      }
   }

	Node siste;	
	
	abstract public boolean settInn(Resept r);
	
	public Resept finnResept(int reseptnr) throws UgyldigReseptnummer {
		for (Resept r: this) {
			if (r.getUniktNummer() == reseptnr) {
				return r;
			}
		}
		throw new UgyldigReseptnummer(reseptnr);
	}
}












