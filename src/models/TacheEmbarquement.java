package models;

import java.util.Hashtable;


public class TacheEmbarquement extends TacheVol {
	
	private static Hashtable<Integer, TacheEmbarquement> lesTachesEmbarquement = new Hashtable<Integer, TacheEmbarquement>();
	
	public TacheEmbarquement(String libelle, Horaire debut, String numeroVol) {
		super(libelle, debut, numeroVol);
		setDuree(new Duree(20));
		setFin(debut.ajout(getDuree()));
		lesTachesEmbarquement.put(getIdTache(), this);
		
	}

	public static Hashtable<Integer, TacheEmbarquement> getLesTachesEmbarquement() {
		return lesTachesEmbarquement;
	}

}
