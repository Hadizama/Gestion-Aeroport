package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public abstract class Vol implements Comparable<Vol> {
	
	private String numeroVol, provenance;
	private Horaire horaire;
	private Avion avion;
	
	private Hashtable<Integer, TacheVol> lesTaches = new Hashtable<Integer, TacheVol>();
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

	public void setHoraire(Horaire horaire) {
		this.horaire = horaire;
	}

	public Avion getAvion() {
		return avion;
	}

	public static Hashtable<String, Vol> getLesVols() {
		return lesVols;
	}

	public Hashtable<Integer, TacheVol> getLesTaches() {
		return lesTaches;
	}

	@Override
	public String toString() {
		return "Vol numéro "+numeroVol+" en provenance de "+provenance+" pour "+horaire+" avec un "+avion;
	}
	
	public static ArrayList<Vol> trier(Hashtable<String, Vol> h){
		ArrayList<Vol> liste = new ArrayList<Vol>(h.values());
		Collections.sort(liste);
		return liste;
	}
	
	@Override
	public int compareTo(Vol v){    	
		return getHoraire().compareTo(v.getHoraire());
    }

}
