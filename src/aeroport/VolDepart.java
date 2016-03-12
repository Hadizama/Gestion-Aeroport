package aeroport;

import java.util.Hashtable;

public class VolDepart extends Vol {
	
	public static Hashtable<String, VolDepart> lesVolsDepart = new Hashtable<String, VolDepart>();

	public VolDepart(String numeroVol, Horaire horaire, String provenance, Avion avion) {
		super(numeroVol, horaire, provenance, avion);
		lesVolsDepart.put(numeroVol, this);
	}
}
