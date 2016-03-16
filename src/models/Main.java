package models;

import java.io.IOException;
import java.util.Enumeration;

import vue.FenetreAccueil;
import controllers.MainController;


public class Main {

	public static void main(String[] args) {
		
		MainController mc = new MainController(new FenetreAccueil());
		
		
		AgentTempsPlein a = new AgentTempsPlein("sfsdfsdfds", "José", "Bové", 2);

		Tache t2 = new TacheAccueil("tache2", new Horaire(10, 30), new Horaire(10, 40));
		Tache t1 = new TacheAccueil("tache1", new Horaire(9, 10), new Horaire(10, 10));
		a.affecterTache(t2);
		a.affecterTache(t1);
						
//		a.affecterTache(TacheDebarquement.lesTachesDebarquement.get("IT4444"));
		
		a.afficherPlanningTache();

		
	}

}
