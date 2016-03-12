package aeroport;

import java.util.Hashtable;

public abstract class Vol {
	
	private String numeroVol, provenance;
	private Horaire horaire;
	private Avion avion;
	
	private static Hashtable<String, Vol> lesVols = new Hashtable<String, Vol>();

	public Vol(String numeroVol, Horaire horaire, String provenance, Avion avion) {
		this.numeroVol = numeroVol;
		this.provenance = provenance;
		this.horaire = horaire;
		this.avion = avion;
		lesVols.put(numeroVol, this);
	}

	public String getNumeroVol() {
		return numeroVol;
	}

	public String getProvenance() {
		return provenance;
	}

	public Horaire getHoraire() {
		return horaire;
	}

	public Avion getAvion() {
		return avion;
	}
		
	

}
