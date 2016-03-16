package models;

import java.util.Hashtable;


public class TacheEnregistrement extends TacheVol {
	
	public static Hashtable<String, TacheEnregistrement> lesTachesEnregistrement = new Hashtable<String, TacheEnregistrement>();

	public TacheEnregistrement(String libelle, Horaire debut, String numeroVol) {
		super(libelle, debut, numeroVol);
		setDuree(new Duree(1, 45));
		setFin(debut.ajout(getDuree()));
		lesTachesEnregistrement.put(numeroVol, this);
	}

}
