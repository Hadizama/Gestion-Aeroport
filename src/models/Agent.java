package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import controllers.MainController;

public abstract class Agent implements Comparable<Agent>{

	private String matricule, nom, prenom;
	private int code;
	private Hashtable<Integer, Tache> lesTaches;
	private boolean absent;
	
	private static Hashtable<String, Agent> lesAgents = new Hashtable<String, Agent>();
	
	public Agent(String matricule, String nom, String prenom, int code) {
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.code = code;
		this.absent = false;
		this.lesTaches = new Hashtable<Integer, Tache>();
		lesAgents.put(matricule, this);
	}	
	
	public abstract TrancheHoraire getHoraire();
	public abstract Duree getNbHeure();
	public abstract void setNbHeure(Duree nbHeure);
	
	// Retourne True si l'agent est disponible pour la tranche horaire renseignée, sinon faux
	public boolean estDisponible(Horaire debut, Horaire fin){
		TrancheHoraire thAgent = getHoraire();
		TrancheHoraire thTache = new TrancheHoraire(debut, fin);
		boolean res = false;
		if(thTache.dansTrancheHoraire(thAgent)){
			Enumeration<Tache> liste = lesTaches.elements();
			res = true;
			while(liste.hasMoreElements()){
				Tache t = (Tache)liste.nextElement();
				TrancheHoraire th = new TrancheHoraire(t.getDebut(), t.getFin());
				if(thTache.intersection(th) != null)
					res = false;
			}
		}
		return res;
	}
	
	// Vérifie l'agent a encore du temps de travail
	private boolean resteTempsTravail(Tache t){
		if(getNbHeure().compareTo(t.getDuree()) >= 0)
			return true;
		else
			return false;
	}
	
	// Affecter une tâche à un agent si ce dernier à la possibilité de réaliser cette tâche
	public boolean affecterTache(Tache t) {
		// Vérification de la disponibilité, du temps de travail restant et de son etat d'absence
		if(estDisponible(t.getDebut(), t.getFin()) && resteTempsTravail(t) && !isAbsent()){
			getLesTaches().put(t.getIdTache(), t);
			// Mise à jour du temps de travail
			setNbHeure(getNbHeure().retirer(t.getDuree()));
			Tache.listeTachesNonAffectees().remove(t.getIdTache());
			// Affectation de l'agent à la tache
			t.setAgent(this);
			return true;
		}
		else{
			// Ajout de la tâche à la liste des tâches non affectées
			Tache.listeTachesNonAffectees().put(t.getIdTache(), t);
			return false;
		}		
	}
	
	// Affectation des tâches accueil pour "remplir" les temps libre (>= 30min) de l'agent
	public void affectationTachesAccueil(){
		ArrayList<Tache> taches = Tache.trier(lesTaches);
		Duree free;
		Horaire debut, fin;
		if(taches.size() == 0){
			affecterTache(new TacheAccueil("Accueil", getHoraire().getDebutTrancheHoraire(), getHoraire().getFinTrancheHoraire()));
		}
		else{
			// Debut de journée
			free = taches.get(0).getDebut().retrait(getHoraire().getDebutTrancheHoraire());
			if(free.dureeEnMinutes() >= 30)
				affecterTache(new TacheAccueil("Accueil", getHoraire().getDebutTrancheHoraire(), taches.get(0).getDebut()));
			
			// Entre les tâches affectées
			for (int i = 1; i < taches.size()-1; i++) {
				free = taches.get(i).getDebut().retrait(taches.get(i-1).getFin());
				if(free.dureeEnMinutes() >= 30)
					affecterTache(new TacheAccueil("Accueil", taches.get(i-1).getFin(), taches.get(i).getDebut()));
			}
			
			// Fin de journée
			free = getHoraire().getFinTrancheHoraire().retrait(taches.get(taches.size()-1).getFin());
			if(free.dureeEnMinutes() >= 30)
				affecterTache(new TacheAccueil("Accueil", taches.get(taches.size()-1).getFin(), getHoraire().getFinTrancheHoraire()));	
			
		}
	}
	
	// Méthode statique permettant d'affecter les tâches "non affectés" aux différents agent
	// Utilisé lors de l'absence d'un agent pour reaffecter l'ensemble de ses tâches à d'autres agents
	public static void reaffecterTache(){
		ArrayList<Tache> ltna = Tache.trier(Tache.listeTachesNonAffectees());
		ArrayList<Agent> agents = Agent.trier(lesAgents);
		// Parcours des tâches non affectées
		for(Tache t: ltna){
			boolean affecte = false;
			int i = 0;
			// Si un agent a du temps libre pour réaliser l'une des tâches
			while(!affecte && i<agents.size()){
				if(!agents.get(i).isAbsent())
					affecte = agents.get(i).affecterTache(t);
				i++;
			}
			i = 0;
			// Remplacement de tache Accueil afin de réaliser l'une des tâches non affectées
			while(i<TacheAccueil.getLesTachesAccueil().size() && !affecte){
				Tache ta = Tache.trier(TacheAccueil.getLesTachesAccueil()).get(i);
				TrancheHoraire th = new TrancheHoraire(ta.getDebut(), ta.getFin());
				if((new TrancheHoraire(t.getDebut(), t.getFin()).dansTrancheHoraire(th)) && ta.getAgent() != null){
					Agent a = ta.getAgent();
					a.desaffecterTache(ta);
					affecte = a.affecterTache(t);
					if(affecte){
						a.affectationTachesAccueil();
					}
				}				
				i++;
			}
			
		}
	}
	
	// Désaffecter une tâche à un agent
	public boolean desaffecterTache(Tache t){
		if(lesTaches.containsKey(t.getIdTache())){
			setNbHeure(getNbHeure().ajout(t.getDuree()));
			lesTaches.remove(t.getIdTache());
			t.setAgent(null);
			return true;
		}
		return false;
	}
	
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

	public Hashtable<Integer, Tache> getLesTaches() {
		return lesTaches;
	}	
	
	public static Hashtable<String, Agent> getLesAgents() {
		return lesAgents;
	}

	// Remise à zero de l'ensemble des tâches d'un agent (ces tâches ont alors le statut de tâche "non affectée")
	public void resetTache(){
		ArrayList<Tache> listeT = Tache.trier(lesTaches);
		for(Tache t : listeT){
			if(!t.getClass().equals(TacheAccueil.class) && !t.getClass().equals(TacheRepas.class))
				Tache.listeTachesNonAffectees().put(t.getIdTache(), t);
				setNbHeure(getNbHeure().ajout(t.getDuree()));
		}
		lesTaches.clear();
	}
	
	// Récupération de l'horaire reservé pour le repas pour un agent (temps plein)
	public Horaire getHRepas(){
		Horaire horaire;
		do {
			// Génération d'un horaire aléatoire entre 11h30 et 14h
			int h = (int) (Math.random()*(14-11)+11);
			int m = (int) (Math.random()*59);
			if(h == 11)
				m = (int) (Math.random()*29+30);
			horaire = new Horaire(h, m);
		} while(!getHoraire().contient(horaire) || !getHoraire().contient(horaire.ajout(new Duree(1,0))) );
		return horaire;
	}
	
	public String toString(){
		String res = this.matricule +" - "+ this.nom + " - "+ getHoraire() + "\n\nListe des tâches affectées :\n";
		ArrayList<Tache> liste = Tache.trier(lesTaches);
		for(Tache t : liste){
			res += "\t"+ t +"\n";
		}
		return res;
	}
	
	// Retourne True si l'agent est absent, false sinon
	public boolean isAbsent() {
		return absent;
	}

	public void setAbsent(boolean absent) {
		this.absent = absent;
	}

	// Trier la liste des tâches en fonction de leur Horaire de début
	public static ArrayList<Agent> trier(Hashtable h){
		ArrayList<Agent> liste = new ArrayList<Agent>(h.values());
		Collections.sort(liste);
		return liste;
	}
	
	// Permet de comparer deux agents entre eux
    public int compareTo(Agent a){    	
		return getHoraire().getDebutTrancheHoraire().compareTo(a.getHoraire().getDebutTrancheHoraire());
    }
	
	
	
}