package models;

import java.util.Hashtable;


public class TacheDebarquement extends TacheVol {
	
	private static Hashtable<Integer, TacheDebarquement> lesTachesDebarquement = new Hashtable<Integer, TacheDebarquement>();

	public TacheDebarquement(String libelle, Horaire debut, String numeroVol) {
		super(libelle, debut, numeroVol);
		setDuree(new Duree(20));
		setFin(debut.ajout(getDuree()));
		lesTachesDebarquement.put(getIdTache(), this);
		
	}

	public static Hashtable<Integer, TacheDebarquement> getLesTachesDebarquement() {
		return lesTachesDebarquement;
	}

}
