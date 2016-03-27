package models;

import java.util.Hashtable;

public class VolDepart extends Vol {
	
	private static Hashtable<String, VolDepart> lesVolsDepart = new Hashtable<String, VolDepart>();

	public VolDepart(String numeroVol, Horaire horaire, String provenance, Avion avion) {
		super(numeroVol, horaire, provenance, avion);
		lesVolsDepart.put(numeroVol, this);
		TacheEmbarquement t = new TacheEmbarquement("Embarquement du vol :" + numeroVol + ".", horaire.retrait(new Duree(15)), numeroVol);
		getLesTaches().put(t.getIdTache(), t);
		int nbtache=avion.getCapacite()/90;
		if(nbtache==0){
			nbtache=1;
		}
		for(int i=0; i< nbtache ; i++){
			TacheEnregistrement t2 = new TacheEnregistrement("Enregistrement " + (i+1) + " du vol :" + numeroVol + ".", horaire.retrait(new Duree(1,30)), numeroVol);
			getLesTaches().put(t2.getIdTache(), t2);
		}
	}

	public static Hashtable<String, VolDepart> getLesVolsDepart() {
		return lesVolsDepart;
	}
}
