package aeroport;

import java.util.Hashtable;

public abstract class Vol {
	
	private String numeroVol, provenance;
	private Horaire horaire;
	
	private static Hashtable<String, Vol> lesVols = new Hashtable<String, Vol>();

	public Vol(String numeroVol, String provenance, Horaire horaire) {
		this.numeroVol = numeroVol;
		this.provenance = provenance;
		this.horaire = horaire;
		lesVols.put(numeroVol, this);
	}

}
