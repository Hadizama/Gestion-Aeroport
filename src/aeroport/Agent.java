package aeroport;

import java.util.Date;
import java.util.Hashtable;

public abstract class Agent {

	private String matricule, nom, prenom;
	private int code;
	private Hashtable<String, Tache> lesTaches;
	
	public static Hashtable<String, Agent> lesAgents = new Hashtable<String, Agent>();
	
	public Agent(String matricule, String nom, String prenom, int code) {
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.code = code;
		this.lesTaches = new Hashtable<String, Tache>();
		lesAgents.put(matricule, this);
	}	
	
	public abstract TrancheHoraire getHoraire();
	
	public abstract void affecterTache(Tache t);
	
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
	
	public void resetTache(){
		this.lesTaches.clear();
	}
	
	
	
}