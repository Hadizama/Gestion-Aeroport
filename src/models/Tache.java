package models;

import java.util.Hashtable;

public abstract class Tache implements Comparable<Tache> {
	
	private int idTache;
	private static int dernierId = 1;
	private String libelle;
	private Horaire debut, fin;
	private Duree duree;
	
	public static Hashtable<Integer, Tache> lesTaches = new Hashtable<Integer, Tache>();
	
	public Tache(String libelle, Horaire debut){
		this.idTache = dernierId++;
		this.libelle = libelle;
		this.debut = debut;
		lesTaches.put(idTache, this);
	}

	public String getLibelle() {
		return libelle;
	}

	public Horaire getDebut() {
		return debut;
	}

	public Horaire getFin() {
		return fin;
	}

	public Duree getDuree() {
		return duree;
	}

	public void setFin(Horaire fin) {
		this.fin = fin;
	}

	public void setDuree(Duree duree) {
		this.duree = duree;
	}

	@Override
	public String toString() {
		return "- "+ libelle + "["+ debut +" - "+ fin +"] soit "+ duree;
	}
	
	@Override
    public int compareTo(Tache t) {
		return debut.compareTo(t.debut);
    }
	
}
