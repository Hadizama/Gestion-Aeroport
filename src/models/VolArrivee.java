package models;

import java.util.Hashtable;

public class VolArrivee extends Vol {
	
	private static Hashtable<String, VolArrivee> lesVolsArrivee = new Hashtable<String, VolArrivee>();

	public VolArrivee(String numeroVol, Horaire horaire, String provenance, Avion avion) {
		super(numeroVol, horaire, provenance, avion);
		lesVolsArrivee.put(numeroVol, this);
		// Cr�ation automatique de la t�che de d�barquement associ�e au vol (sans l'affecter � un agent)
		TacheDebarquement t = new TacheDebarquement("D�barquement du Vol :" + numeroVol + ".", horaire.retrait(new Duree(5)), numeroVol);
		getLesTaches().put(t.getIdTache(), t);
	}

	public static Hashtable<String, VolArrivee> getLesVolsArrivee() {
		return lesVolsArrivee;
	}

}
