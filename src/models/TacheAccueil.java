package models;


public class TacheAccueil extends Tache {

	public TacheAccueil(String libelle, Horaire debut, Horaire fin) {
		super(libelle, debut);
		setFin(fin);
		setDuree(new Duree(fin.horaireEnMinutes() - debut.horaireEnMinutes()));
	}

}
