package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.util.Hashtable;

import models.Agent;
import models.Tache;
import models.TacheVol;
import vue.Fenetre;
import vue.ResultFrame;

public class AgentController implements ActionListener{

	private Fenetre frame;
	private JPanel vue;

	public AgentController (Fenetre f, JPanel v) {
		if(f!=null)
			this.frame = f;
		this.vue = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton btn = (JButton)arg0.getSource();
		switch (arg0.getActionCommand()) {
		case "Afficher planning":
			afficherPlanningAgent(btn.getName());
			break;
		case "Signaler absence":
			signalerAbsence(btn.getName());
			btn.getParent().setBackground(Color.ORANGE);
			btn.setText("Retour agent");
			break;
		case "Retour agent":
			retourAgent(btn.getName());
			btn.getParent().setBackground(null);
			btn.setText("Signaler absence"
					+ "");
			break;
		}
	}
	
	public String[][] getAgents(){
		String[][] res = new String[Agent.getLesAgents().size()][];
		ArrayList<Agent> atp = Agent.trier(Agent.getLesAgents());
		int i=0;
		for (Agent a : atp) {
			String[] v = {a.getNom(), a.getHoraire().toString(), a.getMatricule()};
			res[i] = v;
			i++;
		}
		return res;
	}

	public void signalerAbsence(String key) {
		Hashtable<String, Agent> a = Agent.getLesAgents();
		if(!a.get(key).isAbsent()){
			if(a.containsKey(key)){
				a.get(key).setAbsent(true);
				a.get(key).resetTache();
				Agent.reaffecterTache();
			}
		}
	}
	
	public void retourAgent(String key){
		Hashtable<String, Agent> a = Agent.getLesAgents();
		ArrayList<Tache> t = Tache.trier(Tache.listeTachesNonAffectees());
		if(a.containsKey(key)){
			a.get(key).setAbsent(false);
			for(Tache tache : t){
				a.get(key).affecterTache(tache);
			}
			a.get(key).affectationTachesAccueil();
		}
		
	}

	public void afficherPlanningAgent(String key) {
		if(Agent.getLesAgents().containsKey(key)){
			if(Agent.getLesAgents().get(key).getLesTaches().size() == 0)
				new ResultFrame("Vous devez d'abord générer le planning (Bouton vert en haut à droie)");
			else
				new ResultFrame(Agent.getLesAgents().get(key).toString());
		}		
	}

}
