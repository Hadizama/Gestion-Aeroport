package aeroport;

import java.util.Hashtable;

public class AgentTempsPlein extends Agent {
	
	private final double nbHeure = 8;
	
	public static Hashtable<String, AgentTempsPlein> lesAgentsTempsPlein = new Hashtable<String, AgentTempsPlein>();

	public AgentTempsPlein(String matricule, String nom, String prenom, int code) {
		super(matricule, nom, prenom, code);
		lesAgentsTempsPlein.put(matricule, this);
	}

	@Override
	public Horaire getHoraire() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
