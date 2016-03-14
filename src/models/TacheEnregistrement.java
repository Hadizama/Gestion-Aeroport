package models;

import exceptions.InvalidTacheHoraire;

public class TacheEnregistrement extends TacheVol {

	public TacheEnregistrement(String libelle, Horaire debut, String numeroVol) {
		super(libelle, debut, numeroVol);
		setDuree(new Duree(1, 45));
		setFin(debut.ajout(getDuree()));
	}

}
