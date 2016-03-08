package aeroport;

public class TacheVol extends Tache {
	
	private String numeroVol;

	public TacheVol(String libelle, Horaire debut, Horaire fin, Duree duree, String numeroVol) {
		super(libelle, debut, fin, duree);
		this.numeroVol = numeroVol;
	}

}
