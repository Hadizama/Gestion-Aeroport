package vue;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controllers.MainController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class FenetreAccueil extends JFrame implements Observer {

	private JFrame frame;
	private JPanel contentPane;

	public static void main(String[] args) {
		try {
			JFrame frame = new FenetreAccueil();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace(); // A enlever plus tard (debogage seulement)
		}
	}

	public FenetreAccueil() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		setTitle("Accueil");
		contentPane.setLayout(new BorderLayout());
		ActionListener controleur = new MainController(this); 		

		
		// Menu
		JPanel panelboutons = new JPanel();
		contentPane.add(panelboutons, BorderLayout.NORTH);		
		JButton gestionVols = new JButton("Gestion des vols");
		panelboutons.add(gestionVols);
		JButton gestionAgents = new JButton("Gestion des agents");
		panelboutons.add(gestionAgents);
		JButton planning = new JButton("Générer planning");
		panelboutons.add(planning);
		
		// Contenu
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
