
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

class Helsedata {

	private int indeksPerson = 0;
	private int indeksLegemiddel = 0;

	private EldsteForstReseptListe resepter = new EldsteForstReseptListe();	
	private SortertEnkelListe<Lege> leger = new SortertEnkelListe();
	private Tabell<Person> personer = new Tabell(30);
	private Tabell<Legemiddel> legemidler = new Tabell(30);

	private void reset() {

		Person.reset();			
		Legemiddel.reset();
		Resept.reset();

		resepter = new EldsteForstReseptListe();	
		leger = new SortertEnkelListe();
		personer = new Tabell(20);
		legemidler = new Tabell(20);
	}

	///////////////////////// lesInn-metoder /////////////////////////

	private void lesInnPersoner(Scanner s) {		

		while (s.hasNextLine()) {
			String linje = s.nextLine();
			if (!linje.isEmpty()) {
				String[] data = linje.split(", ");
				Person p = new Person(data[1], data[2], data[3], data[4]);
				personer.settInn(p, indeksPerson++);
			}
			else {
				break;
			}
		}
		//return s;
	}

	private void lesInnLegemidler(Scanner s) {
		int indeks = 0;
		while (s.hasNextLine()) {
			String linje = s.nextLine();
			if (!linje.isEmpty()) {
				String[] data = linje.split(", ");
				String navn = data[1];
				String form = data[2];
				String type = data[3];
				int pris = Integer.parseInt(data[4]);
				int mengde = Integer.parseInt(data[5]);
				int virkestoff = Integer.parseInt(data[6]);

				if (type.equals("a") && form.equals("mikstur")) {
					int styrke = Integer.parseInt(data[7]);
					MiksturNarkotisk lm = new MiksturNarkotisk(navn, pris, styrke, mengde, virkestoff);
					legemidler.settInn(lm, indeksLegemiddel++);
				}
				else if (type.equals("a") && form.equals("pille")) {
					int styrke = Integer.parseInt(data[7]);
					PilleNarkotisk lm = new PilleNarkotisk(navn, pris, styrke, mengde, virkestoff);
					legemidler.settInn(lm, indeksLegemiddel++);	
				}
				else if (type.equals("b") && form.equals("mikstur")) {
					int styrke = Integer.parseInt(data[7]);
					MiksturVanedannende lm = new MiksturVanedannende(navn, pris, styrke, mengde, virkestoff);
					legemidler.settInn(lm, indeksLegemiddel++);
				}
				else if (type.equals("b") && form.equals("pille")) {
					int styrke = Integer.parseInt(data[7]);
					PilleVanedannende lm = new PilleVanedannende(navn, pris, styrke, mengde, virkestoff);
					legemidler.settInn(lm, indeksLegemiddel++);
				}
				else if (type.equals("c") && form.equals("mikstur")) {
					MiksturVanlig lm = new MiksturVanlig(navn, pris, mengde, virkestoff);
					legemidler.settInn(lm, indeksLegemiddel++);
				}
				else {
					PilleVanlig lm = new PilleVanlig(navn, pris, mengde, virkestoff);
					legemidler.settInn(lm, indeksLegemiddel++);
				}
			}
			else {
				break;
			}			
		}
		//return s;
	}

	private void lesInnLeger(Scanner s) {

		while (s.hasNextLine()) {
			String linje = s.nextLine();
			if (!linje.isEmpty()) {
				String[] data = linje.split(", ");
				Lege l = new Lege(null, data[0], data[1]);
				leger.settInn(l);
			}
			else {
				break;
			}
		}
		//return s;
	}

	private void lesInnResepter(Scanner s) {
		while (s.hasNextLine()) {
			String linje = s.nextLine();
			if (!linje.isEmpty()) {
				String[] data = linje.split(", ");
				Person p = hentPerson(Integer.parseInt(data[2]));
				Lege l = hentLege(data[3]);
				Legemiddel lm = hentLegemiddel(Integer.parseInt(data[4]));
				if (data[1].equals("blå")) {
					ReseptBlaa rb = new ReseptBlaa(lm, l, p.getUniktNummer(), Integer.parseInt(data[5]));
					resepter.settInn(rb);
					p.mottaResept(rb);
					l.skrivResept(rb);
				}
				else {
					ReseptHvit rh = new ReseptHvit(lm, l, p.getUniktNummer(), Integer.parseInt(data[5]), lm.getPris());
					resepter.settInn(rh);
					p.mottaResept(rh);
					l.skrivResept(rh);					
				}
			}
			else {
				break;
			}
		}
		//return s;
	}

	///////////////////////// Tom? /////////////////////////

	public boolean tom() {
		int i = 0;
		for (Person p : personer) {
			if (p != null) {
				i++;
			}
		}
		for (Legemiddel lm : legemidler) {
			if (lm != null) {
				i++;
			}
		}
		for (Lege l : leger) {
			i++;
		}
		return (i == 0);
	}

	///////////////////////// Inneholder /////////////////////////

	public boolean inneholderPerson(int nummer) {
		for (Person p : personer) {
			if (p != null) {
				if (p.getUniktNummer() == nummer) {return true;}
			}
		}
		return false;
	}

	public boolean inneholderLegemiddel(int nummer) {
		for (Legemiddel lm : legemidler) {
			if (lm != null) {
				if (lm.getUniktNummer() == nummer) {return true;}
			}
		}
		return false;	
	}

	public boolean inneholderLege(String uniktNavn) {
		for (Lege l : leger) {
			if (l.getUniktNavn().equals(uniktNavn)) {
				return true;
			}
		}
		return false;
	}

	///////////////////////// Les fra fil /////////////////////////

	public void lesFraFil(String filnavn) {
		try {
			File inn = new File(filnavn);
			Scanner sc = new Scanner(inn);
			reset();			
			while (sc.hasNextLine()) {
				String linje = sc.nextLine();
				String[] ord = linje.split(" ");
				if (ord[0].equals("#") && ord[1].equals("Personer")) {
					lesInnPersoner(sc);
				}
				else if (ord[0].equals("#") && ord[1].equals("Legemidler")) {
					lesInnLegemidler(sc);
				}
				else if (ord[0].equals("#") && ord[1].equals("Leger")) {
					lesInnLeger(sc);
				}
				else if (ord[0].equals("#") && ord[1].equals("Resepter")) {
					lesInnResepter(sc);
				}	
			}
			sc.close();
			System.out.println("leste fra filen: " + filnavn);
		}
		catch (FileNotFoundException e) {
			System.out.println("filen finnes ikke!");
			//System.exit(-1);	
		}
	}

	///////////////////////// Skriv til fil /////////////////////////	

	public void skrivTilFil(String filnavn) {
		try {
			PrintWriter ut = new PrintWriter(filnavn);

			// skriv ut personer			
			ut.println("# Personer");
			for (Person p : personer) {
				if (p != null) {
					ut.println(p.getUniktNummer() + ", " + p.getNavn() + ", " + p.getFoedselsnummer() + ", " + p.getAdresse() + ", " + p.getPostnummer());
				}
			}

			// skriv ut legemidler
			ut.println("\n# Legermidler");
			for (Legemiddel lm : legemidler) {
				if (lm instanceof MiksturNarkotisk) {
					MiksturNarkotisk mn = (MiksturNarkotisk) lm;
					ut.println(mn.getUniktNummer() + ", " + mn.getNavn() + ", mikstur, a" + ", " + mn.getPris() + ", " + mn.getVolum() + ", " + mn.getStoffPerVolum() + ", " + mn.getNarkotisk()); 
				}
				else if (lm instanceof MiksturVanedannende) {
					MiksturVanedannende mv = (MiksturVanedannende) lm;
					ut.println(mv.getUniktNummer() + ", " + mv.getNavn() + ", mikstur, b" + ", " + mv.getPris() + ", " + mv.getVolum() + ", " + mv.getStoffPerVolum() + ", " + mv.getVanedannende());
				}
				else if (lm instanceof MiksturVanlig) {
					MiksturVanlig m = (MiksturVanlig) lm;
					ut.println(m.getUniktNummer() + ", " + m.getNavn() + ", mikstur, c" + ", " + m.getPris() + ", " + m.getVolum() + ", " + m.getStoffPerVolum());
				}
				else if (lm instanceof PilleNarkotisk) {
					PilleNarkotisk pn = (PilleNarkotisk) lm;
					ut.println(pn.getUniktNummer() + ", " + pn.getNavn() + ", pille, a" + ", " + pn.getPris() + ", " + pn.getAntallPiller() + ", " + pn.getStoffPerPille() + ", " + pn.getNarkotisk());
				}
				else if (lm instanceof PilleVanedannende) {
					PilleVanedannende pv = (PilleVanedannende) lm;
					ut.println(pv.getUniktNummer() + ", " + pv.getNavn() + ", pille, b" + ", " + pv.getPris() + ", " + pv.getAntallPiller() + ", " + pv.getStoffPerPille() + ", " + pv.getVanedannende());
				}
				else if (lm instanceof PilleVanlig) {
					PilleVanlig p = (PilleVanlig) lm;
					ut.println(p.getUniktNummer() + ", " + p.getNavn() + ", pille, c" + ", " + p.getPris() + ", " + p.getAntallPiller() + ", " + p.getStoffPerPille());
				}
			}

			// skriv ut leger
			ut.println("\n# Leger");
			for (Lege l : leger) {
				ut.println(l.getUniktNavn() + ", " + l.getAvtalenummer());
			}

			// skriv ut resepter
			ut.println("\n# Resepter");
			for (Resept r : resepter) {
				if (r instanceof ReseptBlaa) {
					ut.println(r.getUniktNummer() + ", Blå" + ", " + r.getEiernummer() + ", " + r.getLege().getUniktNavn() + ", " + r.getLegemiddel().getUniktNummer() + ", " + r.getReit());
				}
				else {
					ut.println(r.getUniktNummer() + ", Hvit, " + r.getEiernummer() + ", " + r.getLege().getUniktNavn() + ", " + r.getLegemiddel().getUniktNummer() + ", " + r.getReit());
				}
			}
			ut.println("\n# Slutt");
			ut.close();
			System.out.println("skrev til filen: " + filnavn);
		}
		catch (FileNotFoundException e) {
			System.out.println("ugyldig filnavn!");
			//System.exit(-1);
		}
	}

	///////////////////////// Skriv ut /////////////////////////

	public void printUt() {

		if (tom()) {
			System.out.println("alt er tom!");
			return;
		}

		System.out.println("\n# PERSONER");
		for (Person p : personer) {
			if (p != null) {
				p.printInfo();
			}
		}

		System.out.println("\n# LEGEMIDLER");
		for (Legemiddel lm : legemidler) {
			if (lm != null) {
				lm.printInfo();
			}
		}

		System.out.println("\n# LEGER");
		for (Lege l : leger) {
			l.printInfo();
		}

		System.out.println("\n# RESEPTER");
		for (Resept r : resepter) {
			r.printInfo();
		}
		System.out.println("");
	}

	///////////////////////// Legg til /////////////////////////

	public void leggTilMiksturNarkotisk(String navn, int pris, int narkotisk, int volum, int stoffPerVolum) {
		MiksturNarkotisk mn = new MiksturNarkotisk(navn, pris, narkotisk, volum, stoffPerVolum);
		legemidler.settInn(mn, indeksLegemiddel++);
		System.out.println("setter inn ett legemiddel.");
	}

	public void leggTilMiksturVanedannende(String navn, int pris, int vanedannende, int volum, int stoffPerVolum) {
		MiksturVanedannende mv = new MiksturVanedannende(navn, pris, vanedannende, volum, stoffPerVolum);
		legemidler.settInn(mv, indeksLegemiddel++);
		System.out.println("setter inn ett legemiddel.");
	}

	public void leggTilMiksturVanlig(String navn, int pris, int volum, int stoffPerVolum) {
		MiksturVanlig m = new MiksturVanlig(navn, pris, volum, stoffPerVolum);
		legemidler.settInn(m, indeksLegemiddel++);
		System.out.println("setter inn ett legemiddel.");
	}

	public void leggTilPilleNarkotisk(String navn, int pris, int narkotisk, int antallPiller, int stoffPerPille) {
		PilleNarkotisk pn = new PilleNarkotisk(navn, pris, narkotisk, antallPiller, stoffPerPille);
		legemidler.settInn(pn, indeksLegemiddel++);
		System.out.println("setter inn ett legemiddel.");
	}

	public void leggTilPilleVanedannende(String navn, int pris, int vanedannende, int antallPiller, int stoffPerPille) {
		PilleVanedannende pv = new PilleVanedannende(navn, pris, vanedannende, antallPiller, stoffPerPille);
		legemidler.settInn(pv, indeksLegemiddel++);
		System.out.println("setter inn ett legemiddel.");
	}

	public void leggTilPilleVanlig(String navn, int pris, int antallPiller, int stoffPerPille) {
		PilleVanlig p = new PilleVanlig(navn, pris, antallPiller, stoffPerPille);
		legemidler.settInn(p, indeksLegemiddel++);
		System.out.println("setter inn ett legemiddel.");
	}

	public void leggTilLege(String navn, String uniktNavn, String avtalenummer) {
		Lege l = new Lege(navn, uniktNavn, avtalenummer);
		leger.settInn(l);
		System.out.println("setter inn en lege.");
	}

	public void leggTilPerson(String navn, String foedselsnummer, String adresse, String postnummer) {
		Person p = new Person(navn, foedselsnummer, adresse, postnummer);
		personer.settInn(p, indeksPerson++);
		System.out.println("setter inn en person.");
	}

	public void leggTilReseptBlaa(Legemiddel legemiddel, Lege lege, int eiernummer, int reit) {
		ReseptBlaa rb = new ReseptBlaa(legemiddel, lege, eiernummer, reit);
		resepter.settInn(rb);
		System.out.println("setter inn en resept.");		
	}

	public void leggTilReseptHvit(Legemiddel legemiddel, Lege lege, int eiernummer, int reit, int pris) {
		ReseptHvit rh = new ReseptHvit(legemiddel, lege, eiernummer, reit, pris);
		resepter.settInn(rh);
		System.out.println("setter inn en resept.");			
	}

	///////////////////////// Hent /////////////////////////

	public Person hentPerson(int uniktNummer) {
		for (Person p : personer) {
			if (p != null) {
				if (p.getUniktNummer() == uniktNummer) {return p;}
			}
		}
		return null;
	}		


	public Legemiddel hentLegemiddel(int uniktNummer) {
		for (Legemiddel lm : legemidler) {
			if (lm != null) {
				if (lm.getUniktNummer() == uniktNummer) {return lm;}
			}
		}
		return null;
	}

	public Lege hentLege(String uniktNavn) {
		for (Lege l : leger) {
			if (l.getUniktNavn().equals(uniktNavn)) {
				return l;
			}
		}
		return null;
	}

	public Resept hentResept(int uniktNummer) {
		for (Resept r : resepter) {
			if (r.getUniktNummer() == uniktNummer) {
				return r;
			}
		}
		return null;
	}

	public Resept hentYngsteResept() {
		Resept resept = null;
		for (Resept r : resepter) {
			resept = r;
		}
		return resept;
	}

	///////////////////////// Statistikk /////////////////////////

	public void statistikk1() {
		int antall = 0;
		int antallOslo = 0;
		for (Resept r : resepter) {
			if (r.getLegemiddel() instanceof LegemiddelVanedannende) {
				Person p = hentPerson(r.getEiernummer());
				String postnr = p.getPostnummer();
				String[] siffer = postnr.split("");
				if (siffer[1].equals("0")) {
					antallOslo++;
				}
				antall++;
			}
		}
		System.out.println("Totalt vanedannende resepter: " + antall);
		System.out.println("Total vanedannende resepter skrevet for personer som er bosatt i Oslo: " + antallOslo);
	}

	public void statistikk2(int personnr) {
		Person p = hentPerson(personnr);
		if (p == null) {
			System.out.println("personen finens ikke!");
			return;
		}
		p.statistikk2();
	}

	public void statistikk3(String legenavn) {
		Lege l = hentLege(legenavn);
		if (l == null) {
			System.out.println("legen finnes ikke!");
		}
		l.statistikk3();		
	}

	public void statistikk4() {

		int antallLeger = 0;
		int antallNarkotiskResepter = 0;

		for (Resept r : resepter) {
			if (r.getLegemiddel() instanceof LegemiddelNarkotisk) {
				antallNarkotiskResepter++;
			}		
		}

		System.out.println("Leger som skrev ut minst en narkotisk resept");
		for (Lege l : leger) {
			antallLeger++;
			if (l.harNarkotiskResept()) {
				l.printInfo();
			}	
			else if(!l.harNarkotiskResept()){
				System.out.println("legen har ikke skrevet ut narkotisk resept!");
			}
		}
		double svar = (double)antallNarkotiskResepter/(double)antallLeger;
		System.out.println("(narkotiske resepter) / (antall leger): " + svar);

		int antallPersoner = 0;
		int antnarkpers = 0;

		System.out.println("\npersoner som har narkotiske legemidler\n");		
		
		for (Person p : personer) {
			if (p != null) {
				antallPersoner++;
				if (p.harNarkotiskResept()) {
					antnarkpers++;
					p.printInfo();
				}
			}
		}
		double svar2 = (double)antnarkpers/(double)antallPersoner;
		System.out.println("Antallet per person: " + svar2);
	}
}
















