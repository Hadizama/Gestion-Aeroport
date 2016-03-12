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
	public TrancheHoraire getHoraire() {
		TrancheHoraire th = null;
		switch (getCode()) {
		case 1:
			th = new TrancheHoraire(new Horaire(9, 00), new Horaire(17, 00));
			break;
		case 2:
			th = new TrancheHoraire(new Horaire(6, 00), new Horaire(14, 00));
			break;
		case 3:
			th = new TrancheHoraire(new Horaire(13, 30), new Horaire(21, 30));
			break;
		default:
			break;
		}
		return th;
	}

	@Override
	public void affecterTache(Tache t) {
		getLesTaches().put(t.getLibelle(), t);
		
	}	
	

}
