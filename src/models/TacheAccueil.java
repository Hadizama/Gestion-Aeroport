package models;

import java.util.Hashtable;


public class TacheAccueil extends Tache {
	
	private static Hashtable<Integer, Tache> lesTachesAccueil = new Hashtable<Integer, Tache>();

	public TacheAccueil(String libelle, Horaire debut, Horaire fin) {
		super(libelle, debut);
		setFin(fin);
		setDuree(new Duree(fin.horaireEnMinutes() - debut.horaireEnMinutes()));
	}

	public static Hashtable<Integer, Tache> getLesTachesAccueil() {
		return lesTachesAccueil;
	}

}
