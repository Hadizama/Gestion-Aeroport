package vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetreRetarderVol extends JFrame{
	
	public static void main(String[] args) {
		new FenetreRetarderVol();
	}
	
	private JPanel vue;
	private JTextField h = new JTextField();
	private JLabel labelH = new JLabel("Heure");
	private JTextField m = new JTextField();
	private JLabel labelM = new JLabel("Minutes");
	
	public FenetreRetarderVol(){
		setTitle("Retarder un vol");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel vue = new JPanel();
		vue.setLayout(new BorderLayout());
		vue.add(labelH);
		vue.add(h);
		vue.add(labelM);
		vue.add(m);
		setContentPane(vue);
		this.setVisible(true);
	}
	
}
