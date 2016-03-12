package aeroport;

import java.util.Hashtable;

public class VolArrivee extends Vol {
	
	public static Hashtable<String, VolArrivee> lesVolsArrivee = new Hashtable<String, VolArrivee>();

	public VolArrivee(String numeroVol, String provenance, Horaire horaire) {
		super(numeroVol, provenance, horaire);
		lesVolsArrivee.put(numeroVol, this);
	}

}
