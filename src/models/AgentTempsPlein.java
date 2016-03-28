package models;

import java.util.Calendar;
import java.util.Hashtable;

public class AgentTempsPlein extends Agent {
	
	private Duree nbHeure;
	
	private static Hashtable<String, AgentTempsPlein> lesAgentsTempsPlein = new Hashtable<String, AgentTempsPlein>();

	public AgentTempsPlein(String matricule, String nom, String prenom, int code) {
		super(matricule, nom, prenom, code);
		this.nbHeure = new Duree(8, 0);
		lesAgentsTempsPlein.put(matricule, this);
	}

	@Override	
	public TrancheHoraire getHoraire() {
		TrancheHoraire[] liste = {
									new TrancheHoraire(new Horaire(9, 00), new Horaire(17, 00)),
									new TrancheHoraire(new Horaire(6, 00), new Horaire(14, 00)),
									new TrancheHoraire(new Horaire(13, 30), new Horaire(21, 30))
								};
		Calendar c = Calendar.getInstance();
		int semaine = c.get(c.WEEK_OF_YEAR) % 3;
		
		TrancheHoraire th = null;
		switch (getCode()) {
		case 1:
			th = liste[semaine];
			break;
		case 2:
			th = liste[(semaine+1)%3];
			break;
		case 3:
			th = liste[(semaine+2)%3];
			break;
		default:
			break;
		}
		return th;
	}

	public Duree getNbHeure() {
		return this.nbHeure;
	}

	public void setNbHeure(Duree nbHeure) {
		this.nbHeure = nbHeure;
	}

	public static Hashtable<String, AgentTempsPlein> getLesAgentsTempsPlein() {
		return lesAgentsTempsPlein;
	}	
	
	

}
