package models;

import exceptions.InvalidTacheHoraire;

public class TacheRepas extends Tache {

	public TacheRepas(String libelle, Horaire debut) {
		super(libelle, debut);
		setDuree(new Duree(1, 0));
		setFin(debut.ajout(getDuree()));
	}

}
