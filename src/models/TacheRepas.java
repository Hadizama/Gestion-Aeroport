package models;

import java.util.Hashtable;

import exceptions.InvalidTacheRepas;


public class TacheRepas extends Tache {
	
	private static Hashtable<Integer, Tache> lesTachesRepas = new Hashtable<Integer, Tache>();

	public TacheRepas(String libelle, Horaire debut) throws InvalidTacheRepas{
		super(libelle, debut);
		TrancheHoraire th = new TrancheHoraire(new Horaire(11, 30), new Horaire(14,00));
		if(!th.contient(debut)){
			throw new InvalidTacheRepas();
		}
		setDuree(new Duree(1, 0));
		setFin(debut.ajout(getDuree()));
	}

	public static Hashtable<Integer, Tache> getLesTachesRepas() {
		return lesTachesRepas;
	}

}
