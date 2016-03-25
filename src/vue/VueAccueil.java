package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import controllers.MainController;

public class VueAccueil extends JPanel {

	private JFrame frame;
	private JPanel contentPane;
	private JLabel status;

	public VueAccueil() {
		this.setLayout(new BorderLayout());
		ActionListener controleur = new MainController(this); 		

		// Menu
		JPanel panelboutons = new JPanel();
		panelboutons.setLayout(new GridLayout(1,3));
		panelboutons.setPreferredSize(new Dimension(250, 50));
		JButton gestionVols = new JButton("Gestion des vols");
		gestionVols.addActionListener(controleur);
		panelboutons.add(gestionVols);
		JButton gestionAgents = new JButton("Gestion des agents");
		gestionAgents.addActionListener(controleur);
		panelboutons.add(gestionAgents);
		JButton planning = new JButton("Visionner le planning");
		planning.addActionListener(controleur);
		panelboutons.add(planning);
		this.add(panelboutons, BorderLayout.NORTH);		

		// Contenu
		
		
		// Barre d'état
		JLabel status = new JLabel();
		status.setBorder(new EtchedBorder());
		this.add(status,BorderLayout.SOUTH);
		
		
	}
	
	public void setStatus(String text){
		this.status.setText(text);
	}

	
}
