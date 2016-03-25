package vue;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import controllers.MainController;

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
		this.setTitle("Gestion du personnel d'un aéroport");
		this.vue = new PanelAccueil();
		this.setLayout(new BorderLayout());
		this.add(new VueAccueil(), BorderLayout.NORTH);
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
		this.repaint();
		this.pack();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}