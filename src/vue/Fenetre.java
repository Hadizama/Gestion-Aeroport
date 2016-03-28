package vue;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import controllers.MainController;
import vue.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class Fenetre extends JFrame implements Observer {

	public static void main(String[] args) {
		new Fenetre();
	}
	
	private JPanel vue;
	
	public Fenetre() {
		
			// Menu
			JPanel vueAccueil = new JPanel();
			vueAccueil.setLayout(new BorderLayout());
			MainController controleur = new MainController(this, vueAccueil); 
			controleur.importationFichiers();
			JPanel panelboutons = new JPanel();
			panelboutons.setLayout(new GridLayout(1,3));
			panelboutons.setPreferredSize(new Dimension(250, 50));
			JButton gestionVols = new JButton("Gestion des vols");
			gestionVols.addActionListener(controleur);
			panelboutons.add(gestionVols);
			JButton gestionAgents = new JButton("Gestion des agents");
			gestionAgents.addActionListener(controleur);
			panelboutons.add(gestionAgents);
			JButton planning = new JButton("Générer le planning");
			planning.setBackground(Color.GREEN);
			planning.addActionListener(controleur);
			panelboutons.add(planning);
			vueAccueil.add(panelboutons, BorderLayout.NORTH);
		
		this.setTitle("Gestion du personnel d'un aéroport");
		this.setLayout(new BorderLayout());
		this.vue = new JPanel();
		this.add(vueAccueil, BorderLayout.NORTH);
		this.add(vue, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	public void setVue(JPanel v){
		if(this.vue != null)
			this.remove(this.vue);
		this.vue = v;
		this.add(this.vue);
		this.pack();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}