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
import vue.Fenetre;
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
			retarderVol(btn.getName());
			frame.setVue(new VueVols(frame));
			break;
			
		}
	}
	
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

	public void annulerVol(String key) {		
		if(Vol.getLesVols().containsKey(key)) {
			ArrayList<Tache> taches = Tache.trier(Vol.getLesVols().get(key).getLesTaches());
			if(taches.size() >= 0){
				for (Tache tache : taches){
					if(tache.getAgent() != null)
						tache.getAgent().desaffecterTache(tache);
					TacheVol.getLesTachesVol().remove(tache.getIdTache());
				}
				Vol.getLesVols().get(key).getLesTaches().clear();
			}
			Vol.getLesVols().remove(key);
		}
	}
	
	public void retarderVol(String key){
		
	}

	public void afficherPlanningVol(String key) {
		if(Vol.getLesVols().containsKey(key)){
			Vol vol = Vol.getLesVols().get(key);
			ArrayList<Tache> taches = Tache.trier(vol.getLesTaches());
			if(vol.getLesTaches().size() == 0)
				new ResultFrame("Aucune tâche n'a été affectée pour ce vol");
			else{
				String res = Vol.getLesVols().get(key).toString()
						+"\n\nListe des tâches affectées à ce vol :\n\n";
				for (Tache tache : taches) {
					if( ((TacheVol)tache).getNumeroVol().equals(key) && !Tache.listeTachesNonAffectees().contains(tache) && tache.getAgent() != null)
						res += "\t" + ((TacheVol)tache).toString() + " (" + tache.getAgent().getMatricule() + ", " 
								+ tache.getAgent().getNom() + ", " + tache.getAgent().getPrenom() + ")\n";
				}
				
				new ResultFrame(res);
			}
		}		
	}

}
