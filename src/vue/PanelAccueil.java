package vue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javafx.scene.control.TextField;

import javax.swing.*;

import controllers.MainController;

public class PanelAccueil extends JPanel {
	
	private JButton btn_generer;
	
	public PanelAccueil(){
		ActionListener controleur = new MainController(this); 	
		this.setLayout (new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = GridBagConstraints.CENTER;
		gbc.gridy = GridBagConstraints.RELATIVE;
		btn_generer = new JButton("Générer le planning");
		btn_generer.setPreferredSize(new Dimension(400,100));
		btn_generer.addActionListener(controleur);
		this.add(btn_generer, gbc);
		
	}
	
	public void changeBtn(){
		btn_generer.setText("Visionner le planning");
		this.updateUI();	
	}
}