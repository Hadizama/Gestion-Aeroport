package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.Agent;
import vue.Fenetre;

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
		//
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

	public void afficherPlanningAgent(String string) {
		// TODO Auto-generated method stub
		
	}

}
