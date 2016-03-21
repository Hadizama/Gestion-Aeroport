package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public abstract class Agent {

	private String matricule, nom, prenom;
	private int code;
	private Hashtable<String, Tache> lesTaches;
	
	private static Hashtable<String, Agent> lesAgents = new Hashtable<String, Agent>();
	
	public Agent(String matricule, String nom, String prenom, int code) {
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.code = code;
		this.lesTaches = new Hashtable<String, Tache>();
		lesAgents.put(matricule, this);
	}	
	
	public abstract TrancheHoraire getHoraire();
	public abstract Duree getNbHeure();
	public abstract void setNbHeure(Duree nbHeure);
	
	public boolean estDisponible(Horaire debut, Horaire fin){
		TrancheHoraire thAgent = getHoraire();
		TrancheHoraire thTache = new TrancheHoraire(debut, fin);
		boolean res = false;
		if(thTache.dansTrancheHoraire(thAgent)){
			Enumeration<Tache> liste = lesTaches.elements();
			res = true;
			while(liste.hasMoreElements()){
				Tache t = (Tache)liste.nextElement();
				if(thTache.contient(t.getDebut()) || thTache.contient(t.getFin()))
					res = false;
			}
		}
		return res;
	}
	
	private boolean resteTempsTravail(Tache t){
		if(getNbHeure().compareTo(t.getDuree()) > 0)
			return true;
		else
			return false;
	}
	
	public boolean affecterTache(Tache t) {
		if(estDisponible(t.getDebut(), t.getFin()) && resteTempsTravail(t)){
			getLesTaches().put(t.getLibelle(), t);
			System.out.println("Ajouté !");
			return true;
		}
		else{
			System.out.println("Raté !");
			return false;
		}		
	}
	
	public String getMatricule() {
		return matricule;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getCode() {
		return code;
	}

	public Hashtable<String, Tache> getLesTaches() {
		return lesTaches;
	}
	
	
	public static Hashtable<String, Agent> getLesAgents() {
		return lesAgents;
	}

	public void resetTache(){
		this.lesTaches.clear();
	}
	
	public String toString(){
		String res = this.matricule +" - "+ this.nom + this.prenom + " - "+ getHoraire() + "\nListe des tâches affectées :\n";
		Enumeration<Tache> liste = lesTaches.elements();
		while(liste.hasMoreElements())
			res += "\t"+ (Tache)liste.nextElement() +"\n";
		return res;
	}
	
	public void afficherPlanningTache(){
		ArrayList<Tache> liste = new ArrayList<Tache>(lesTaches.values());
		Collections.sort(liste);
		for (Tache tache : liste) {
			System.out.println(tache);
		}
	}
	
	
	
	
}