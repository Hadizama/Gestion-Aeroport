package aeroport;

import java.util.Hashtable;

public class VolDepart extends Vol {
	
	public static Hashtable<String, VolDepart> lesVolsDepart = new Hashtable<String, VolDepart>();

	public VolDepart(String numeroVol, String provenance, Horaire horaire) {
		super(numeroVol, provenance, horaire);
		lesVolsDepart.put(numeroVol, this);
	}
}
