package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;

public abstract class Tache implements Comparable<Tache> {
	
	private int idTache;
	private static int dernierId = 1;
	private String libelle;
	private Horaire debut, fin;
	private Duree duree;
	private Agent agent;
	
	private static Hashtable<Integer, Tache> lesTachesNonAffectees = new Hashtable<Integer, Tache>();
	private static Hashtable<Integer, Tache> lesTaches = new Hashtable<Integer, Tache>();
	
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

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public void setFin(Horaire fin) {
		this.fin = fin;
	}

	public void setDuree(Duree duree) {
		this.duree = duree;
	}

	public static Hashtable<Integer, Tache> getLesTaches() {
		return lesTaches;
	}
	
	public int getIdTache() {
		return idTache;
	}
	
	// Trier la liste des tâches en fonction de l'horaire de début
	public static ArrayList<Tache> trier(Hashtable h){
		ArrayList<Tache> liste = new ArrayList<Tache>(h.values());
		Collections.sort(liste);
		return liste;
	}
	
	public static Hashtable<Integer, Tache> listeTachesNonAffectees(){
		return lesTachesNonAffectees;
	}

	@Override
	public String toString() {
		return " - "+ libelle + " ["+ debut +" - "+ fin +"] soit "+ duree + "\n";
	}
	
	// Permet de comprarer deux tâches
	@Override
    public int compareTo(Tache t) {
		return debut.compareTo(t.debut);
    }
	
}
