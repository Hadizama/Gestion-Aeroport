package aeroport;

import java.util.Hashtable;

public class Agent {

	private String matricule, nom, prenom;
	private int code;
	
	public static Hashtable<String, Agent> lesAgents = new Hashtable<String, Agent>();
	
	public Agent(String matricule, String nom, String prenom, int code) {
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.code = code;
		lesAgents.put(matricule, this);
	}	
	
}