package aeroport;

public abstract class Tache {
	
	private String libelle;
	private Horaire debut, fin;
	private Duree duree;
	
	public Tache(String libelle, Horaire debut, Horaire fin, Duree duree) {
		this.libelle = libelle;
		this.debut = debut;
		this.fin = fin;
		this.duree = duree;
	}
	
	
	

}
