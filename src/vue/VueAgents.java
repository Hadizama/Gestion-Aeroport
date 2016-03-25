package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import controllers.MainController;

public class VueAgents extends JPanel {
	

	public VueAgents(){
		MainController controleur = new MainController(null, this);
		String[][] agents = controleur.getAgents();
		
		JPanel listing = new JPanel();
		listing.setLayout(new GridLayout(agents.length, 1));
				
		for (String[] infos : agents) {
			JPanel ag = new JPanel();
			ag.setLayout(new GridLayout(1,5));
			ag.add(new JLabel(infos[0]));
			ag.add(new JLabel(infos[1]));
			ag.add(new JLabel(infos[2]));
			JButton b = new JButton("Signaler absence");
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controleur.signalerAbsence(infos[3]);
				}
			});
			ag.add(b);
			JButton b2 = new JButton("Afficher planning");
			b2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controleur.afficherPlanningAgent(infos[3]);
				}
			}); 
			ag.add(b2);
			listing.add(ag);
		}
		
		JScrollPane jsp = new JScrollPane(listing);
		jsp.getViewport().getSize(new Dimension(800, 550));
		jsp.setPreferredSize(new Dimension(800, 550));
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(jsp);
		
		
	}
	
}
