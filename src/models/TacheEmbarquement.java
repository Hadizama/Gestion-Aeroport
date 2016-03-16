package models;

import java.util.Hashtable;


public class TacheEmbarquement extends TacheVol {
	
	public static Hashtable<String, TacheEmbarquement> lesTachesEmbarquement = new Hashtable<String, TacheEmbarquement>();
	
	public TacheEmbarquement(String libelle, Horaire debut, String numeroVol) {
		super(libelle, debut, numeroVol);
		setDuree(new Duree(20));
		setFin(debut.ajout(getDuree()));
		lesTachesEmbarquement.put(numeroVol, this);
	}

}
