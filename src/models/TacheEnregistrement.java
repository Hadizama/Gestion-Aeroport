package models;

import java.util.Hashtable;


public class TacheEnregistrement extends TacheVol {
	
	private static Hashtable<Integer, TacheEnregistrement> lesTachesEnregistrement = new Hashtable<Integer, TacheEnregistrement>();

	public TacheEnregistrement(String libelle, Horaire debut, String numeroVol) {
		super(libelle, debut, numeroVol);
		setDuree(new Duree(1, 15));
		setFin(debut.ajout(getDuree()));
		lesTachesEnregistrement.put(getIdTache(), this);
	}

	public static Hashtable<Integer, TacheEnregistrement> getLesTachesEnregistrement() {
		return lesTachesEnregistrement;
	}

}
