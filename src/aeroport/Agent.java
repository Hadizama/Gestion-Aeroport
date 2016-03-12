package aeroport;

import java.util.Date;
import java.util.Hashtable;

public abstract class Agent {

	private String matricule, nom, prenom;
	private long code;
	private Hashtable<Duree, Tache> lesTaches;
	
	public static Hashtable<String, Agent> lesAgents = new Hashtable<String, Agent>();
	
	public Agent(String matricule, String nom, String prenom, long code) {
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.code = code;
		this.lesTaches = new Hashtable<Duree, Tache>();
		lesAgents.put(matricule, this);
	}	
	
	public abstract Horaire getHoraire();
	
}