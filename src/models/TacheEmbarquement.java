package models;

import exceptions.InvalidTacheHoraire;

public class TacheEmbarquement extends TacheVol {

	public TacheEmbarquement(String libelle, Horaire debut, String numeroVol) {
		super(libelle, debut, numeroVol);
		setDuree(new Duree(20));
		setFin(debut.ajout(getDuree()));
	}

}
