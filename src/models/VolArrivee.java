package models;

import java.util.Hashtable;

public class VolArrivee extends Vol {
	
	private static Hashtable<String, VolArrivee> lesVolsArrivee = new Hashtable<String, VolArrivee>();

	public VolArrivee(String numeroVol, Horaire horaire, String provenance, Avion avion) {
		super(numeroVol, horaire, provenance, avion);
		lesVolsArrivee.put(numeroVol, this);
		new TacheDebarquement("Débarquement du Vol :" + numeroVol + ".", horaire.retrait(new Duree(5)), numeroVol);
	}

	public static Hashtable<String, VolArrivee> getLesVolsArrivee() {
		return lesVolsArrivee;
	}

}
