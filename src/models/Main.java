package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import vue.Fenetre;
import controllers.AgentController;
import controllers.MainController;
import controllers.VolController;


public class Main {

	public static void main(String[] args) {
		
		MainController mc = new MainController(null, null);
		AgentController ma = new AgentController(null, null);
		VolController mv = new VolController(null, null);
		
		mc.importationFichiers();
		mc.genererPlanning();
		
		// Annulation d'un vol
		System.out.println("Nb vols : " + Vol.getLesVols().size());
		System.out.println("Nb taches : " + TacheVol.getLesTachesVol().size());
		System.out.println("Nombre de tache pour ce vol : " + Vol.getLesVols().get("IT1111").getLesTaches().size() );
		mv.annulerVol("IT1111");
		System.out.println("Nb vols : " + Vol.getLesVols().size());
		System.out.println("Nb taches : " + TacheVol.getLesTachesVol().size());
		
		System.out.println("\n----------------------------------------------------------------\n");
		
		// Retard
		System.out.println(Vol.getLesVols().get("CAL917") +"\n\nListe des tâches affectées à ce vol :");
		Vol vol = Vol.getLesVols().get("CAL917");
		ArrayList<Tache> taches = Tache.trier(vol.getLesTaches());
		for (Tache tache : taches) {
			if( ((TacheVol)tache).getNumeroVol().equals("CAL917") && !Tache.listeTachesNonAffectees().contains(tache) && tache.getAgent() != null)
				System.out.println("\t" + ((TacheVol)tache).toString() + " (" + tache.getAgent().getMatricule() + ", "+ tache.getAgent().getNom() + ")\n");
		}
		
		mv.retarderVol("CAL917", 9, 20);		
		System.out.println("----------------------------------------");		
		
		vol = Vol.getLesVols().get("CAL917");
		taches = Tache.trier(vol.getLesTaches());
		for (Tache tache : taches) {
			if( ((TacheVol)tache).getNumeroVol().equals("CAL917") && !Tache.listeTachesNonAffectees().contains(tache) && tache.getAgent() != null)
				System.out.println("\t" + ((TacheVol)tache).toString() + " (" + tache.getAgent().getMatricule() + ", "+ tache.getAgent().getNom() + ")\n");
		}
		
		
	}

}
