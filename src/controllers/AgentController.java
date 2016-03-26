package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import models.Agent;
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
		}
	}
	
	public String[][] getAgents(){
		String[][] res = new String[Agent.getLesAgents().size()][];
		ArrayList<Agent> atp = Agent.trier(Agent.getLesAgents());
		int i=0;
		for (Agent a : atp) {
			String[] v = {a.getNom(), a.getPrenom(), a.getHoraire().toString(), a.getMatricule()};
			res[i] = v;
			i++;
		}
		return res;
	}

	public void signalerAbsence(String string) {
		// TODO Auto-generated method stub
		
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
