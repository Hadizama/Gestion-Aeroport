package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.VolController;

public class FenetreRetarderVol extends JFrame{
	
	private FenetreRetarderVol frv;
	private JPanel vue = new JPanel();
	private VolController volC;
	private String numV;
	private JTextField jtfH = new JTextField();
	private JLabel labelH = new JLabel("Heure");
	private JTextField jtfM = new JTextField();
	private JLabel labelM = new JLabel("Minutes");
	private JButton b = new JButton ("Valider");
	
	public FenetreRetarderVol(VolController vc, String numVol){
		volC = vc;
		numV = numVol;
		
		setTitle("Retarder un vol");
		this.setDefaultCloseOperation(ResultFrame.DISPOSE_ON_CLOSE);
	    setSize(300, 200);
		vue.setLayout(new BorderLayout());

		JPanel nouvelHoraire = new JPanel();
		jtfH.setPreferredSize(new Dimension(50, 30));
		jtfM.setPreferredSize(new Dimension(50, 30));
		nouvelHoraire.add(labelH);
		nouvelHoraire.add(jtfH);
		nouvelHoraire.add(labelM);
		nouvelHoraire.add(jtfM);
		vue.add(nouvelHoraire, BorderLayout.NORTH);
		
		b.setPreferredSize(new Dimension(50,30));
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int h = Integer.parseInt(jtfH.getText());
				int m = Integer.parseInt(jtfM.getText());
				if(h>=0 && h<=23 && m>=0 && m<=59){
					volC.retarderVol(numV, h, m);
					frv.dispose();
				}else{
					JOptionPane.showMessageDialog(vue, "Horaire invalide !", null, 0);
				}
			}
		});
		vue.add(b);
		setContentPane(vue);
		this.setVisible(true);
		
		frv = this;
	}
}
