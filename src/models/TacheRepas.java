package models;

import java.util.Hashtable;

import exceptions.InvalidTacheRepas;


public class TacheRepas extends Tache {
	
	private static Hashtable<Integer, Tache> lesTachesRepas = new Hashtable<Integer, Tache>();

	public TacheRepas(String libelle, Horaire debut){
		super(libelle, debut);
		setDuree(new Duree(1, 0));
		setFin(debut.ajout(getDuree()));
		lesTachesRepas.put(getIdTache(),this);
		
	}

	public static Hashtable<Integer, Tache> getLesTachesRepas() {
		return lesTachesRepas;
	}

}
