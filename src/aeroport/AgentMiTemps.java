package aeroport;

import java.util.Hashtable;

public class AgentMiTemps extends Agent {

	private final double nbHeure = 3.3;
	
	public static Hashtable<String, AgentMiTemps> lesAgentsMiTemps = new Hashtable<String, AgentMiTemps>();

	public AgentMiTemps(String matricule, String nom, String prenom, int code) {
		super(matricule, nom, prenom, code);
		lesAgentsMiTemps.put(matricule, this);
	}

	@Override
	public Horaire getHoraire() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
