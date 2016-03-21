package models;

import java.util.Hashtable;

public class VolDepart extends Vol {
	
	public static Hashtable<String, VolDepart> lesVolsDepart = new Hashtable<String, VolDepart>();

	public VolDepart(String numeroVol, Horaire horaire, String provenance, Avion avion) {
		super(numeroVol, horaire, provenance, avion);
		lesVolsDepart.put(numeroVol, this);
		new TacheEmbarquement("Embarquement vol :" + numeroVol + ".", horaire.retrait(new Duree(15)), numeroVol);
		for(int i=0; i< (int)Math.ceil(avion.getCapacite()/90); i++)
			new TacheEnregistrement("Enregistrement " + (i+1) + " du vol :" + numeroVol + ".", horaire.retrait(new Duree(1,30)), numeroVol);
	}
}
