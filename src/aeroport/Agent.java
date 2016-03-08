package aeroport;

import java.util.Date;
import java.util.Hashtable;

public abstract class Agent {

	private String matricule, nom, prenom;
	private int code;
	private Hashtable<Date, Tache> lesTaches;
	
	public static Hashtable<String, Agent> lesAgents = new Hashtable<String, Agent>();
	
	public Agent(String matricule, String nom, String prenom, int code) {
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.code = code;
		this.lesTaches = new Hashtable<Date, Tache>();
		lesAgents.put(matricule, this);
	}	
	
	public abstract Horaire getHoraire();
	
}