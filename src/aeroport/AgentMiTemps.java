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
	public TrancheHoraire getHoraire() {
		TrancheHoraire th = null;
		switch (getCode()) {
		case 1:
			th = new TrancheHoraire(new Horaire(9, 00), new Horaire(12, 30));
			break;
		case 2:
			th = new TrancheHoraire(new Horaire(5, 30), new Horaire(9, 00));
			break;
		case 3:
			th = new TrancheHoraire(new Horaire(20, 00), new Horaire(23, 30));
			break;
		default:
			break;
		}
		return th;
	}

	@Override
	public void affecterTache(Tache t) {
		// TODO Auto-generated method stub
		
	}
	

}
