package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import controllers.MainController;

public abstract class Agent implements Comparable<Agent>{

	private String matricule, nom, prenom;
	private int code;
	private Hashtable<Integer, Tache> lesTaches;
	
	private static Hashtable<String, Agent> lesAgents = new Hashtable<String, Agent>();
	
	public Agent(String matricule, String nom, String prenom, int code) {
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.code = code;
		this.lesTaches = new Hashtable<Integer, Tache>();
		lesAgents.put(matricule, this);
	}	
	
	public abstract TrancheHoraire getHoraire();
	public abstract Duree getNbHeure();
	public abstract void setNbHeure(Duree nbHeure);
	
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
	
	private boolean resteTempsTravail(Tache t){
		if(getNbHeure().compareTo(t.getDuree()) > 0)
			return true;
		else
			return false;
	}
	
	public boolean affecterTache(Tache t) {
		if(estDisponible(t.getDebut(), t.getFin()) && resteTempsTravail(t)){
			getLesTaches().put(t.getIdTache(), t);
			setNbHeure(getNbHeure().retirer(t.getDuree()));
			Tache.listeTachesNonAffectees().remove(t.getIdTache());
			return true;
		}
		else{
			if(!t.getClass().equals(TacheAccueil.class))
			Tache.listeTachesNonAffectees().put(t.getIdTache(), t);
			return false;
		}		
	}
	
	public void affectationTachesAccueil(){
		ArrayList<Tache> taches = Tache.trier(lesTaches);
		Duree free;
		Horaire fin;
		if(taches.size() == 0){
			// ??????????????
		}
		else{
			// Debut
			free = taches.get(0).getDebut().retrait(getHoraire().getDebutTrancheHoraire());
			if(free.dureeEnMinutes() >= 30){
				fin = (free.dureeEnMinutes() <= getNbHeure().dureeEnMinutes()) ? taches.get(0).getDebut() : getHoraire().getDebutTrancheHoraire().ajout(getNbHeure());
				if(fin.retrait(getHoraire().getDebutTrancheHoraire()).dureeEnMinutes() >= 30){
					affecterTache(new TacheAccueil("Accueil 1", getHoraire().getDebutTrancheHoraire(), fin));
					MainController.xxx++;
				}
			}
			
			// Ensemble des tâches
			for (int i = 1; i < taches.size()-1; i++) {
				free = taches.get(i).getDebut().retrait(taches.get(i-1).getFin());
				if(free.dureeEnMinutes() >= 30){
					fin = (free.dureeEnMinutes() <= getNbHeure().dureeEnMinutes()) ? taches.get(i).getDebut() : taches.get(i-1).getFin().ajout(getNbHeure());
					if(fin.retrait(taches.get(i-1).getFin()).dureeEnMinutes() >= 30){
						affecterTache(new TacheAccueil("Accueil 2", taches.get(i-1).getFin(), fin));
						MainController.xxx++;
					}
				}
			}
//			
//			// Fin
			free = getHoraire().getFinTrancheHoraire().retrait( taches.get(taches.size()-1).getFin());
//			free = (free.dureeEnMinutes() >= getNbHeure().dureeEnMinutes()) ? getNbHeure() : free;
			if(free.dureeEnMinutes() >= 30 && getNbHeure().dureeEnMinutes() >=30){
				free = (free.dureeEnMinutes() >= getNbHeure().dureeEnMinutes()) ? getNbHeure() : free;
				fin = ( (taches.get(taches.size()-1).getFin().ajout(free)).compareTo(getHoraire().getFinTrancheHoraire()) <= 0 ) ? taches.get(taches.size()-1).getFin().ajout(free) : getHoraire().getFinTrancheHoraire();
//				fin = (free.dureeEnMinutes() <= getNbHeure().dureeEnMinutes()) ? getHoraire().getFinTrancheHoraire() : taches.get(taches.size()-1).getFin().ajout(getNbHeure());
					affecterTache(new TacheAccueil("Accueil 3", taches.get(taches.size()-1).getFin(), taches.get(taches.size()-1).getFin().ajout(free)));	
			}
			
		}
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

	public void resetTache(){
		this.lesTaches.clear();
	}
	
	public Horaire getHRepas(){
		Horaire horaire;
		do {
			int h = (int) (Math.random()*(14-11)+11);
			int m = (int) (Math.random()*59);
			if(h == 11)
				m = (int) (Math.random()*29+30);
			horaire = new Horaire(h, m);
		} while(!getHoraire().contient(horaire) || !getHoraire().contient(horaire.ajout(new Duree(1,0))) );
		return horaire;
	}
	
	public String toString(){
		String res = this.matricule +" - "+ this.nom + this.prenom + " - "+ getHoraire() + "\nListe des tâches affectées :\n";
		ArrayList<Tache> liste = Tache.trier(lesTaches);
		for(Tache t : liste){
			res += "\t"+ t +"\n";
		}
		return res;
	}
	
	public void afficherPlanningTache(){
		ArrayList<Tache> liste = new ArrayList<Tache>(lesTaches.values());
		Collections.sort(liste);
		for (Tache tache : liste) {
			System.out.println(tache);
		}
	}
	
	public static ArrayList<Agent> trier(Hashtable h){
		ArrayList<Agent> liste = new ArrayList<Agent>(h.values());
		Collections.sort(liste);
		return liste;
	}
	
    public int compareTo(Agent a) {
		return getHoraire().getDebutTrancheHoraire().compareTo(a.getHoraire().getDebutTrancheHoraire());
    }
	
	
	
}