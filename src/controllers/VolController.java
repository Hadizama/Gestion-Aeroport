package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import models.Tache;
import models.TacheAccueil;
import models.TacheVol;
import models.Vol;
import vue.Fenetre;
import vue.ResultFrame;

public class VolController implements ActionListener{

	private Fenetre frame;
	private JPanel vue;

	public VolController (Fenetre f, JPanel v) {
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

	public void signalerAbsence(String string) {
		// TODO Auto-generated method stub
		
	}

	public void afficherPlanningVol(String key) {
		if(Vol.getLesVols().containsKey(key)){
			ArrayList<Tache> lesTachesVols = Tache.trier(TacheVol.getLesTachesVol());
			if(lesTachesVols.size() == 0)
				new ResultFrame("Aucune tâche n'a été affectée pour ce vol");
			else{
				String res = Vol.getLesVols().get(key).toString()
						+"\n\nListe des tâches affectées à ce vol :\n\n";
				for (Tache tache : lesTachesVols) {
					if( ((TacheVol)tache).getNumeroVol().equals(key) )
						res += "\t" + ((TacheVol)tache).toString() + "\n";
				}
				
				new ResultFrame(res);
			}
		}		
	}

}
