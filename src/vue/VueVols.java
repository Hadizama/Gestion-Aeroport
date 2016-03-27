package vue;

import java.awt.BorderLayout;
import java.awt.Color;
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

import controllers.VolController;
import controllers.MainController;

public class VueVols extends JPanel {
	
	private Fenetre frame;
	private String[] infos;
	
	private JScrollPane jsp ;
	private JPanel listing, ag;
	
	public VueVols(Fenetre f){
		this.frame = f;
		VolController controleur = new VolController(frame, this);
		String[][] vols = controleur.getVols();
		
		listing = new JPanel();
		listing.setLayout(new GridLayout(vols.length, 1));
				
		for (String[] inf : vols) {
			this.infos = inf;
			ag = new JPanel();
			ag.setLayout(new GridLayout(1,5));
			ag.add(new JLabel(infos[0]));
			ag.add(new JLabel(infos[1]));
			ag.add(new JLabel(infos[2]));
//			ag.add(new JLabel(infos[3]));
			JButton b = new JButton("Retarder vol");
			b.addActionListener(controleur);
			b.setName(infos[0]);
			ag.add(b);
			JButton b2 = new JButton("Annuler vol");
			b2.setName(infos[0]);
			b2.addActionListener(controleur);
			ag.add(b2);
			JButton b3 = new JButton("Afficher planning");
			b3.setName(infos[0]);
			b3.addActionListener(controleur);
			ag.add(b3);
			listing.add(ag);
		}
		
		jsp = new JScrollPane(listing);
		jsp.getViewport().getSize(new Dimension(800, 550));
		jsp.setPreferredSize(new Dimension(800, 550));
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(jsp);
		
		
	}
	
	public void maj(){
		frame.setVue(new VueVols(frame));
	}
	
}
