package models;

import java.util.Hashtable;

public class VolArrivee extends Vol {
	
	public static Hashtable<String, VolArrivee> lesVolsArrivee = new Hashtable<String, VolArrivee>();

	public VolArrivee(String numeroVol, Horaire horaire, String provenance, Avion avion) {
		super(numeroVol, horaire, provenance, avion);
		lesVolsArrivee.put(numeroVol, this);
	}

}
