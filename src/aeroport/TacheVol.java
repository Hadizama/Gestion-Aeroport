package aeroport;

import java.util.Hashtable;

public class TacheVol extends Tache {
	
	private String numeroVol;
	
	public static Hashtable<String, TacheVol> lesTachesVol = new Hashtable<String, TacheVol>();

	public TacheVol(String libelle, Horaire debut, Horaire fin, Duree duree, String numeroVol) {
		super(libelle, debut, fin, duree);
		this.numeroVol = numeroVol;
		lesTachesVol.put(numeroVol, this);
	}

	public String getNumeroVol() {
		return numeroVol;
	}	

}
