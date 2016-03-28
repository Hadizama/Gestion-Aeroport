package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import models.Agent;
import models.Horaire;
import models.Tache;
import models.TacheAccueil;
import models.TacheVol;
import models.Vol;
import models.VolArrivee;
import models.VolDepart;
import vue.Fenetre;
import vue.FenetreRetarderVol;
import vue.ResultFrame;
import vue.VueVols;

public class VolController implements ActionListener{

	private Fenetre frame;
	private VueVols vue;

	public VolController (Fenetre f, VueVols v) {
		if(f!=null)
			this.frame = f;
		this.vue = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton btn = (JButton)arg0.getSource();
		switch (arg0.getActionCommand()) {
		case "Afficher planning":
			afficherPlanningVol(btn.getName());
			break;
		case "Annuler vol":
			annulerVol(btn.getName());
			frame.setVue(new VueVols(frame));
			break;
		case "Retarder vol":
			new FenetreRetarderVol(this, btn.getName());
			break;
			
		}
	}
	
	// R�cup�ration des toutes les informations de chaque vol afin de les transmettre � la vue
	public String[][] getVols(){
		String[][] res = new String[Vol.getLesVols().size()][];
		ArrayList<Vol> lesVols = Vol.trier(Vol.getLesVols());
		int i=0;
		for (Vol v : lesVols) {
			String[] x = {v.getNumeroVol(), v.getProvenance(), v.getHoraire().toString(), v.getAvion().toString()};
			res[i] = x;
			i++;
		}
		return res;
	}

	// Annulation d'un vol et suppression de toutes les taches qui le concernaient
	public void annulerVol(String key) {		
		if(Vol.getLesVols().containsKey(key)) {
			ArrayList<Tache> taches = Tache.trier(Vol.getLesVols().get(key).getLesTaches());
			if(taches.size() >= 0){
				// D�saffectation des t�ches reli�es � ce vol
				for (Tache tache : taches){
					if(tache.getAgent() != null)
						tache.getAgent().desaffecterTache(tache);
					TacheVol.getLesTachesVol().remove(tache.getIdTache());
				}
				// Suppression des taches concernant ce vol
				Vol.getLesVols().get(key).getLesTaches().clear();
			}
			// Suppression du vol
			Vol.getLesVols().remove(key);
		}
	}
	
	// Retarder un vol � un nouvel horaire ainsi que l'ensemble des t�ches le concernant
	public void retarderVol(String key, int h, int m){
		Horaire horaire = new Horaire(h, m);
		if(Vol.getLesVols().containsKey(key)) {
			// Suppression du vol
			Vol vol = Vol.getLesVols().get(key);
			annulerVol(key);
			// Cr�ation d'une copie de ce vol avec un nouvel horaire
			if(vol.getClass().getClass().equals(VolArrivee.class)){
				vol = new VolArrivee(vol.getNumeroVol(), horaire, vol.getProvenance(), vol.getAvion());
			}else{
				vol = new VolDepart(vol.getNumeroVol(), horaire, vol.getProvenance(), vol.getAvion());
			}
			ArrayList<Tache> taches = Tache.trier(vol.getLesTaches());
			// R�affectation des t�ches avec leur nouvel horaire
			for(Tache t: taches){
				Tache.listeTachesNonAffectees().put(t.getIdTache(), t);
			}
			Agent.reaffecterTache();
		}
		// Mise � jour de la vue en temps r�el
		frame.setVue(new VueVols(frame));
	}

	// Affichage du planning concernant un vol (liste des t�ches et l'agent qui leurs est affect�)
	public void afficherPlanningVol(String key) {
		if(Vol.getLesVols().containsKey(key)){
			Vol vol = Vol.getLesVols().get(key);
			ArrayList<Tache> taches = Tache.trier(vol.getLesTaches());
			// Si aucune t�ches n'est affect�e � ce vol
			if(vol.getLesTaches().size() == 0)
				new ResultFrame("Aucune t�che n'a �t� affect�e pour ce vol");
			else{
				// Affichage de chaque t�che ainsi que l'agent � qui elle est affect�e
				String res = Vol.getLesVols().get(key).toString()
						+"\n\nListe des t�ches affect�es � ce vol :\n\n";
				for (Tache tache : taches) {
					if( ((TacheVol)tache).getNumeroVol().equals(key) && !Tache.listeTachesNonAffectees().contains(tache) && tache.getAgent() != null)
						res += "\t" + ((TacheVol)tache).toString() + " (" + tache.getAgent().getMatricule() + ", " 
								+ tache.getAgent().getNom() + ")\n";
				}
				new ResultFrame(res);
			}
		}		
	}

}
