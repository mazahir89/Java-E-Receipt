
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

class OrdrestyrtProgram {

   Helsedata hdata = new Helsedata();

	String getString(String prompt) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.print(prompt + " ");
		String s = sc.nextLine();		
		return s;
	}

	int getInt(String prompt)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print(prompt + " ");
		int i = sc.nextInt();
		return i;
	}

	String printMeny()
	{
		PrintStream o = java.lang.System.out;
		o.println("=======================================================");
		o.println("                       Helsedata                       ");
		o.println("=======================================================");
		o.println(" a - Lese alle data fra fil.");
		o.println(" b - Skrive alle data til fil.");		 
		o.println(" c - Skrive ut alle data.");
		o.println(" d - Legg til nytt legemiddel.");
		o.println(" e - Legg til ny lege.");	
		o.println(" f - Legg til ny person.");
		o.println(" g - Legg til nytt resept.");
		o.println(" h - Hente legemiddelet fra en resept.");
		o.println(" i - Statistikk.");
		o.println("");
		o.println(" x - Exit.");
		o.println("");

		return getString(">");
	}

	String printStatistikk()
	{
		PrintStream o = java.lang.System.out;
		o.println("========== Statistikk ==========");
		o.println(" a - Vanedannende resepter.");
		o.println(" b - En gitt person.");		 
		o.println(" c - En gitt lege.");
		o.println(" d - Medisinsk misbruk av narkotika.");
		return getString("Hva slags statistikk:");
	}

	void utfoere(String s) {

		if (s.equals("a")) {
			String svar = getString("filnavn:");
			hdata.lesFraFil(svar);
		}		
		else if (s.equals("b")) {
			String svar = getString("filnavn:");
			hdata.skrivTilFil(svar);
		}
		else if (s.equals("c")) {
			hdata.printUt();
		}
		else if (s.equals("d")) {
			try	
			{	
				String navn = getString("navn:");
				String form = getString("mikstur/pille:");
				if ( !(form.equals("mikstur") || form.equals("pille")) ) {
					System.out.println("ugyldig svar!");
					return;
				}
				String type = getString("a/b/c:");
				if ( !(type.equals("a") || type.equals("b") || type.equals("c")) ) {
					System.out.println("ugyldig svar!");
					return;
				}
				int pris = getInt("pris:");
				int mengde = getInt("mengde:");
				int virkestoff = getInt("stoffPerMengde:");
				int styrke = getInt("styrke:");
				if (form.equals("mikstur") && type.equals("a")) {
					hdata.leggTilMiksturNarkotisk(navn, pris, styrke, mengde, virkestoff);					
				}
				else if (form.equals("mikstur") && type.equals("b")) {
					hdata.leggTilMiksturVanedannende(navn, pris, styrke, mengde, virkestoff);
				}
				else if (form.equals("mikstur") && type.equals("c")) {
					hdata.leggTilMiksturVanlig(navn, pris, mengde, virkestoff);
				}
				else if (form.equals("pille") && type.equals("a")) {
					hdata.leggTilPilleNarkotisk(navn, pris, styrke, mengde, virkestoff);
				}
				else if (form.equals("pille") && type.equals("b")) {
					hdata.leggTilPilleVanedannende(navn, pris, styrke, mengde, virkestoff);
				}
				else {
					hdata.leggTilPilleVanlig(navn, pris, mengde, virkestoff);
				}
			}
			catch (Exception e) {
				System.out.println("ugyldig svar!");
			}
		}
		else if (s.equals("e")) {
			String uniktNavn = getString("unikt navn:");
			String avtalenummer = getString("avtalenummer:");
			hdata.leggTilLege(null, uniktNavn, avtalenummer);
		}
		else if (s.equals("f")) {
			String navn = getString("navn:");
			String foedselsnummer = getString("foedselsnummer:");
			String adresse = getString("adresse:");
			String postnummer = getString("postnummer:");
			hdata.leggTilPerson(navn, foedselsnummer, adresse, postnummer);
		}
		else if (s.equals("g")) {
			try
			{
				int pris = 0;
				String farge = getString("blå/hvit:");
				if ( !(farge.equals("blå") || farge.equals("hvit")) ) {
					System.out.println("ugyldig farge!");
					return;				
				}
				else if (farge.equals("hvit")) {
					pris = getInt("pris:");
				}

				int personNummer = getInt("nummeret til personen:");
				Person p = hdata.hentPerson(personNummer);
				if (p == null) {
					System.out.println("finner ikke en person med nummer: " + personNummer);
					return;
				}

				String legeNavn = getString("legens navn:");
				Lege l = hdata.hentLege(legeNavn);
				if (l == null) {
					System.out.println("finner ikke en lege med navn: " + legeNavn);
					return;
				}

				int legemiddelNummer = getInt("nummeret til legemiddel:");
				Legemiddel lm = hdata.hentLegemiddel(legemiddelNummer);			
				if (lm == null) {
					System.out.println("finner ikke ett legemiddel med nummer: " + legemiddelNummer);
					return;
				}

				int reit = getInt("reit:");
				if (farge.equals("blå")) {
					hdata.leggTilReseptBlaa(lm, l, personNummer, reit);
					p.mottaResept(hdata.hentYngsteResept());
					l.skrivResept(hdata.hentYngsteResept());
				}
				else {
					hdata.leggTilReseptHvit(lm, l, personNummer, reit, pris);
					p.mottaResept(hdata.hentYngsteResept());
					l.skrivResept(hdata.hentYngsteResept());
				}
			}
			catch (Exception e) {
				System.out.println("ugyldig svar!");
			}	
		}	
		else if (s.equals("h")) {
			if (hdata.tom()) {
				System.out.println("alt er tom!");
				return;
			}
			int uniktNummer = getInt("unikt nummer til personen:");
			Person p = hdata.hentPerson(uniktNummer);
			if (p == null) {
				System.out.println("finner ikke personen med nummer: " + uniktNummer);
				return;
			}
			if (p.harIngenResept()) {
				System.out.println("personen har ingen resepter!");
				return;
			}
			p.printResepter();
			int reseptNummer = getInt("nummeret til resepten:");
			Resept r = hdata.hentResept(reseptNummer);
			if (r.gyldig()) {
				System.out.println("");
				System.out.println("kan brukes " + (r.getReit() - 1) + " ganger til!");
				System.out.println("prisen som må betales: " + r.getPris());
				System.out.println("legens navn: " + r.getLege().getUniktNavn());
				System.out.println("personens navn: " + p.getNavn());
				System.out.println("\n(nr, navn, form, type, pris, mengde, stoff/mengde, styrke)");
				Legemiddel lm = r.hentLegemiddel();
				lm.printInfo();
				if (lm instanceof MiksturNarkotisk) {
					MiksturNarkotisk mn = (MiksturNarkotisk) lm;
					System.out.println("total virkestoff: " + mn.getVirkestoff() + "");
				}
				else if (lm instanceof MiksturVanedannende) {
					MiksturVanedannende mv = (MiksturVanedannende) lm;
					System.out.println("total virkestoff: " + mv.getVirkestoff() + "");
				}
				else if (lm instanceof MiksturVanlig) {
					MiksturVanlig mvanlig = (MiksturVanlig) lm;
					System.out.println("total virkestoff: " + mvanlig.getVirkestoff() + "");
				}
				else if (lm instanceof PilleNarkotisk) {
					PilleNarkotisk pn = (PilleNarkotisk) lm;
					System.out.println("total virkestoff: " + pn.getVirkestoff() + "");
				}
				else if (lm instanceof PilleVanedannende) {
					PilleVanedannende pv = (PilleVanedannende) lm;
					System.out.println("total virkestoff: " + pv.getVirkestoff() + "");
				}
				else {
					PilleVanlig pvanlig = (PilleVanlig) lm;
					System.out.println("total virkestoff: " + pvanlig.getVirkestoff() + "");
				}	
				
			}
			else {
				System.out.println("resepten er ikke gyldig!");
				return;
			}
			
		}
		else if (s.equals("i")) {
			String svar = printStatistikk();
			if (svar.equals("a")) {
				hdata.statistikk1();			
			}
			else if (svar.equals("b")) {
				int personnr = getInt("unikt nummer til personen:");
				hdata.statistikk2(personnr);				
			}
			else if (svar.equals("c")) {
				String legenavn = getString("navnet til legen:");
				hdata.statistikk3(legenavn);
			}
			else if (svar.equals("d")) {
				hdata.statistikk4();
			}
			else {
				System.out.println("ugyldig svar!");
				return;
			}
		}
		else if (s.equals("x")) {
			System.exit(-1);
		}
		else {
			System.out.println("ugyldig svar!");
		}
	}

	public static void main(String[] args) {

		OrdrestyrtProgram p = new OrdrestyrtProgram();				
		String s = p.printMeny();
		
		while (true) {
			p.utfoere(s);
			s = p.getString(">");
		}

	}
}



