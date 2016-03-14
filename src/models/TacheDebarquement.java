package models;

import exceptions.InvalidTacheHoraire;

public class TacheDebarquement extends TacheVol {

	public TacheDebarquement(String libelle, Horaire debut, String numeroVol) {
		super(libelle, debut, numeroVol);
		setDuree(new Duree(20));
		setFin(debut.ajout(getDuree()));
	}

}
