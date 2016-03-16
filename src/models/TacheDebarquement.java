package models;

import java.util.Hashtable;


public class TacheDebarquement extends TacheVol {
	
	public static Hashtable<String, TacheDebarquement> lesTachesDebarquement = new Hashtable<String, TacheDebarquement>();

	public TacheDebarquement(String libelle, Horaire debut, String numeroVol) {
		super(libelle, debut, numeroVol);
		setDuree(new Duree(20));
		setFin(debut.ajout(getDuree()));
		lesTachesDebarquement.put(numeroVol, this);
	}

}
