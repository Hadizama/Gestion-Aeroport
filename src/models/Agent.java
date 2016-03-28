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
	
	// Retourne True si l'agent est disponible pour la tranche horaire renseign�e, sinon faux
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
	
	// V�rifie l'agent a encore du temps de travail
	private boolean resteTempsTravail(Tache t){
		if(getNbHeure().compareTo(t.getDuree()) >= 0)
			return true;
		else
			return false;
	}
	
	// Affecter une t�che � un agent si ce dernier � la possibilit� de r�aliser cette t�che
	public boolean affecterTache(Tache t) {
		// V�rification de la disponibilit�, du temps de travail restant et de son etat d'absence
		if(estDisponible(t.getDebut(), t.getFin()) && resteTempsTravail(t) && !isAbsent()){
			getLesTaches().put(t.getIdTache(), t);
			// Mise � jour du temps de travail
			setNbHeure(getNbHeure().retirer(t.getDuree()));
			Tache.listeTachesNonAffectees().remove(t.getIdTache());
			// Affectation de l'agent � la tache
			t.setAgent(this);
			return true;
		}
		else{
			// Ajout de la t�che � la liste des t�ches non affect�es
			Tache.listeTachesNonAffectees().put(t.getIdTache(), t);
			return false;
		}		
	}
	
	// Affectation des t�ches accueil pour "remplir" les temps libre (>= 30min) de l'agent
	public void affectationTachesAccueil(){
		ArrayList<Tache> taches = Tache.trier(lesTaches);
		Duree free;
		Horaire debut, fin;
		if(taches.size() == 0){
			affecterTache(new TacheAccueil("Accueil", getHoraire().getDebutTrancheHoraire(), getHoraire().getFinTrancheHoraire()));
		}
		else{
			// Debut de journ�e
			free = taches.get(0).getDebut().retrait(getHoraire().getDebutTrancheHoraire());
			if(free.dureeEnMinutes() >= 30)
				affecterTache(new TacheAccueil("Accueil", getHoraire().getDebutTrancheHoraire(), taches.get(0).getDebut()));
			
			// Entre les t�ches affect�es
			for (int i = 1; i < taches.size()-1; i++) {
				free = taches.get(i).getDebut().retrait(taches.get(i-1).getFin());
				if(free.dureeEnMinutes() >= 30)
					affecterTache(new TacheAccueil("Accueil", taches.get(i-1).getFin(), taches.get(i).getDebut()));
			}
			
			// Fin de journ�e
			free = getHoraire().getFinTrancheHoraire().retrait(taches.get(taches.size()-1).getFin());
			if(free.dureeEnMinutes() >= 30)
				affecterTache(new TacheAccueil("Accueil", taches.get(taches.size()-1).getFin(), getHoraire().getFinTrancheHoraire()));	
			
		}
	}
	
	// M�thode statique permettant d'affecter les t�ches "non affect�s" aux diff�rents agent
	// Utilis� lors de l'absence d'un agent pour reaffecter l'ensemble de ses t�ches � d'autres agents
	public static void reaffecterTache(){
		ArrayList<Tache> ltna = Tache.trier(Tache.listeTachesNonAffectees());
		ArrayList<Agent> agents = Agent.trier(lesAgents);
		// Parcours des t�ches non affect�es
		for(Tache t: ltna){
			boolean affecte = false;
			int i = 0;
			// Si un agent a du temps libre pour r�aliser l'une des t�ches
			while(!affecte && i<agents.size()){
				if(!agents.get(i).isAbsent())
					affecte = agents.get(i).affecterTache(t);
				i++;
			}
			i = 0;
			// Remplacement de tache Accueil afin de r�aliser l'une des t�ches non affect�es
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
	
	// D�saffecter une t�che � un agent
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

	// Remise � zero de l'ensemble des t�ches d'un agent (ces t�ches ont alors le statut de t�che "non affect�e")
	public void resetTache(){
		ArrayList<Tache> listeT = Tache.trier(lesTaches);
		for(Tache t : listeT){
			if(!t.getClass().equals(TacheAccueil.class) && !t.getClass().equals(TacheRepas.class))
				Tache.listeTachesNonAffectees().put(t.getIdTache(), t);
				setNbHeure(getNbHeure().ajout(t.getDuree()));
		}
		lesTaches.clear();
	}
	
	// R�cup�ration de l'horaire reserv� pour le repas pour un agent (temps plein)
	public Horaire getHRepas(){
		Horaire horaire;
		do {
			// G�n�ration d'un horaire al�atoire entre 11h30 et 14h
			int h = (int) (Math.random()*(14-11)+11);
			int m = (int) (Math.random()*59);
			if(h == 11)
				m = (int) (Math.random()*29+30);
			horaire = new Horaire(h, m);
		} while(!getHoraire().contient(horaire) || !getHoraire().contient(horaire.ajout(new Duree(1,0))) );
		return horaire;
	}
	
	public String toString(){
		String res = this.matricule +" - "+ this.nom + " - "+ getHoraire() + "\n\nListe des t�ches affect�es :\n";
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

	// Trier la liste des t�ches en fonction de leur Horaire de d�but
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