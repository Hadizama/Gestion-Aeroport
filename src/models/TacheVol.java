package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public abstract class TacheVol extends Tache {
	
	private String numeroVol;
	
	private static Hashtable<Integer, TacheVol> lesTachesVol = new Hashtable<Integer, TacheVol>();

	public TacheVol(String libelle, Horaire debut, String numeroVol) {
		super(libelle, debut);
		this.numeroVol = numeroVol;
		lesTachesVol.put(getIdTache(), this);
		
	}

	public String getNumeroVol() {
		return numeroVol;
	}

	public static Hashtable<Integer, TacheVol> getLesTachesVol() {
		return lesTachesVol;
	}	

	
}
