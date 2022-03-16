package beuth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
	//-------------------------------------------
	// Attribute
	private String name = "";
	private int matrikelnummer = 0;
	private String studiengang = "";
	private Map<String, String> faecherZuNoten = null;

	//-------------------------------------------
	// Konstruktor
	public Student(String datenZeile) throws FalscherStudiengangAusnahme {

		try {
			// Daten in Teile aufteilen
			String[] teile = datenZeile.split(",");

			// Die Teile in Attribute zuordnen bzw. bearbeiten
			name = teile[0];
			matrikelnummer = Integer.parseInt(teile[1]);
			studiengang = teile[2];

			// Wenn noetig, Ausnahme werfen
			if (!studiengang.equalsIgnoreCase("Medieninformatik") &&
					!studiengang.equalsIgnoreCase("Technische Informatik") &&
					!studiengang.equalsIgnoreCase("Druck- und Medientechnik") &&
					!studiengang.equalsIgnoreCase("Screen Based Media")) {
				FalscherStudiengangAusnahme e = new FalscherStudiengangAusnahme(studiengang + " gehoert nicht zum Fachbereich VI");
				throw e;
			}

			faecherZuNoten = new HashMap<String, String>();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			throw e;
		}
		catch (NumberFormatException e) {
			throw e;
		}
	}

	//-------------------------------------------
	// Methoden
	public int getMatrikelnummer() {
		return matrikelnummer;
	}

	public String getName() {
		return name;
	}

	public String getStudiengang() {
		return studiengang;
	}

	public void noteEintragen(String fach, String note) {
		faecherZuNoten.put(fach, note);
	}

	public void setMatrikelnummer(int matrikelnummer) {
		this.matrikelnummer = matrikelnummer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}

	public String toString() {
		String returnString = name
				+ " (" + matrikelnummer + "), "
				+ studiengang;
		return returnString;
	}

	public List<String> zeugnisZeilenAbrufen() {
		List<String> returnList = new ArrayList<String>();
		returnList.add(toString());

		// Faecher alphabetisch anordnen
		List<String> faecher = new ArrayList<String>(faecherZuNoten.keySet());
		Collections.sort(faecher);

		// Eine Zeile fuer jedes Fach erstellen:
		// Name des Faches <tab> Note
		for (String fach : faecher) {
			returnList.add(fach + "\t" + faecherZuNoten.get(fach));
		}

		return returnList;
	}

	public Map<String, String> getFaecherZuNoten() {
		return faecherZuNoten;
	}
}
