package vue;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FenetreAccueil extends JFrame {

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
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		setTitle("Accueil");
		
		JPanel boutons = new JPanel();
		boutons.setLayout(new GridLayout(3,1));
		contentPane.add(boutons);
		
		
		JButton gestionVols = new JButton("Gestion des vols");
		boutons.add(gestionVols);
		JButton gestionAgents = new JButton("Gestion des agents");
		boutons.add(gestionAgents);
		JButton planning = new JButton("Générer planning");
		boutons.add(planning);
		
	}

}
