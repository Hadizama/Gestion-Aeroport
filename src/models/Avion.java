package models;

import java.util.Hashtable;

public class Avion {

	private String matriculeAvion;
	private String modele;
	private int capacite;
	
	public static Hashtable<String,Avion> lesAvions = new Hashtable<String, Avion>();

	public Avion(String matriculeAvion, String modele, int capacite) {
		this.matriculeAvion = matriculeAvion;
		this.modele = modele;
		this.capacite = capacite;
		lesAvions.put(matriculeAvion, this);
	}

	
	public String getMatriculeAvion() {
		return matriculeAvion;
	}

	public String getModele() {
		return modele;
	}

	public int getCapacite() {
		return capacite;
	}


	@Override
	public String toString() {
		return "Avion [matriculeAvion=" + matriculeAvion + ", modele=" + modele
				+ ", capacite=" + capacite + "]";
	}

	
}
