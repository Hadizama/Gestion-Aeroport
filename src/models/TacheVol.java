package models;

import java.util.Hashtable;

public class TacheVol extends Tache {
	
	private String numeroVol;
	
	private static Hashtable<String, TacheVol> lesTachesVol = new Hashtable<String, TacheVol>();

	public TacheVol(String libelle, Horaire debut, String numeroVol) {
		super(libelle, debut);
		this.numeroVol = numeroVol;
		lesTachesVol.put(numeroVol, this);
	}

	public String getNumeroVol() {
		return numeroVol;
	}

	public static Hashtable<String, TacheVol> getLesTachesVol() {
		return lesTachesVol;
	}	

}
