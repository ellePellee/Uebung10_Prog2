package beuth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class MainProgramm {
 //versuchGitHub

 staic String tryOut = "mal schauen ob ich jetzt etwas vergleichen kann";

	static Map<Integer, Student> matrikelZuStudent = new HashMap<Integer, Student>();

	public static void teil1() {
		try {
			List<String> zeilen = new ArrayList<String>();

			// Eingabe einrichten

			FileInputStream fis = new FileInputStream("ein/Studierende.txt");
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			// Datei-Inhalt einlesen
			String line = "";
			while ((line = br.readLine()) != null) {
				Student s = new Student(line);
				matrikelZuStudent.put(s.getMatrikelnummer(), s);
				zeilen.add(line);
			}
			// Aufraeumen
			br.close();
			isr.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void teil2() {
		try {
			List<String> zeilen = new ArrayList<String>();

			File file = new File("ein");
			File[] fileArray = file.listFiles();

			for (File fileName : fileArray) {

				if (fileName.getPath().equals("ein/Studierende.txt")) {
					continue;
				} else {
					System.out.println("ordner   " + fileName);
					// Eingabe einrichten
					FileInputStream fis = new FileInputStream(fileName);
					InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr);
					// 1. Zeile einlesen Name Fach
					String fach = br.readLine();
					String line = "";

					while ((line = br.readLine()) != null) {
						zeilen.add(line);
						for (String s : zeilen) {
							String[] tokens = s.split("\\t");
							int matrikelnr = Integer.parseInt(tokens[0]);
							String note = tokens[1];
							Student student = matrikelZuStudent.get(matrikelnr);

							if (student.getMatrikelnummer() == matrikelnr) {
								student.noteEintragen(fach, note);

							}
						}
					}
					// Aufraeumen
					br.close();
					isr.close();
					fis.close();
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void teil3() {
		System.out.println("----------------teil 3----------------- ");
		try {
			Set<Entry<Integer, Student>> entries = matrikelZuStudent.entrySet();

			// Ausgabe einrichten

			for (Entry<Integer, Student> entry : entries) {
				Student student = entry.getValue();
				String dateiName = "aus/" + student.getName() + ".txt";
				// Ausgabe einrichten
				FileOutputStream fos = new FileOutputStream(dateiName);
				OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
				BufferedWriter bw = new BufferedWriter(osw);

				if (student.getMatrikelnummer() == entry.getKey()) {
					String name = student.getName();
					List<String> zeugnisDaten = student.zeugnisZeilenAbrufen();

					for (String zeile : zeugnisDaten) {
						System.out.println(zeile);
						bw.write(zeile);
						bw.newLine();
					}
				}
				// Aufraeumen
				bw.close();
				osw.close();
				fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// Teil 1: ----------------------------------------
		teil1();
		// Teil 2: ----------------------------------------
		teil2();
		// Teil 3: ----------------------------------------
		teil3();
	}
}
