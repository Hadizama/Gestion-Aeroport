package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vue.PanelAccueil;
import models.*;

public class MainController implements ActionListener{
	
	private JPanel vue;

	public MainController (JPanel v) {
		this.vue = v;
		importationFichiers();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(arg0.getActionCommand());
		switch (arg0.getActionCommand()) {
		case "Générer le planning":
			this.genererPlanning();
			JOptionPane.showMessageDialog(vue, "La planning à été généré avec succés !", null, 1);
			((PanelAccueil)this.vue).changeBtn();
			break;

		default:
			break;
		}
		
	}
	
	private void importationFichiers(){
		String lvl = "Avions";
		try{
			BufferedReader entree = new BufferedReader	(new FileReader("./ressources/avions16-v1.txt"));
			String ligne= null;
			while ((ligne = entree.readLine())!=null){			
				StringTokenizer decoupe = new StringTokenizer(ligne);
				new Avion(decoupe.nextToken(), decoupe.nextToken(), Integer.parseInt(decoupe.nextToken()));
			}
			
			lvl = "Agents Mi-Temps";
			entree = new BufferedReader	(new FileReader("./ressources/AgentsMiTemps-16-v1.txt"));
			ligne= null;
			while ((ligne = entree.readLine())!=null){			
				StringTokenizer decoupe = new StringTokenizer(ligne);
				new AgentMiTemps(decoupe.nextToken(), decoupe.nextToken(), decoupe.nextToken(), Integer.parseInt(decoupe.nextToken()));
			}
			
			lvl = "Agents Temps Plein";
			entree = new BufferedReader	(new FileReader("./ressources/AgentsTempsPlein-16-v1.txt"));
			ligne= null;
			while ((ligne = entree.readLine())!=null){			
				StringTokenizer decoupe = new StringTokenizer(ligne);
				new AgentTempsPlein(decoupe.nextToken(), decoupe.nextToken(), decoupe.nextToken(), Integer.parseInt(decoupe.nextToken()));
			}
			
			lvl = "Vols Départs";
			entree = new BufferedReader	(new FileReader("./ressources/ProgrammeVolsDeparts16-v2.txt"));
			ligne= null;
			while ((ligne = entree.readLine())!=null){			
				StringTokenizer decoupe = new StringTokenizer(ligne);
				new VolDepart(
						decoupe.nextToken(), 
						new Horaire(Integer.parseInt(decoupe.nextToken()), Integer.parseInt(decoupe.nextToken())),
						decoupe.nextToken(),
						Avion.getLesAvions().get(decoupe.nextToken())
					);
			}
			
			lvl = "Vols Arrivées";
			entree = new BufferedReader	(new FileReader("./ressources/ProgrammeVolsArrivees16-v2.txt"));
			ligne= null;
			while ((ligne = entree.readLine())!=null){			
				StringTokenizer decoupe = new StringTokenizer(ligne);
				new VolArrivee(
						decoupe.nextToken(), 
						new Horaire(Integer.parseInt(decoupe.nextToken()), Integer.parseInt(decoupe.nextToken())),
						decoupe.nextToken(),
						Avion.getLesAvions().get(decoupe.nextToken())
					);
			}
		} catch (NumberFormatException | IOException e) {
			System.out.println("Erreur d'importation du fichier :\"" + lvl +"\"");
		}
	}
	
	
	public void genererPlanning(){
		ArrayList<Agent> atp = Agent.trier(AgentTempsPlein.getLesAgentsTempsPlein());
		for(int i=0; i<atp.size(); i++){
			atp.get(i).affecterTache(new TacheRepas("Repas", atp.get(i).getHRepas()));
		}
		ArrayList<Tache> tv = Tache.trier(TacheVol.getLesTachesVol());
		ArrayList<Agent> a = Agent.trier(Agent.getLesAgents());
		for(Tache t : tv){
			int i = 0;
			while(i<a.size() && !a.get(i).affecterTache(t)){ i++; }
		}
		for(int i=0; i<a.size();i++){
			a.get(i).affectationTachesAccueil();
		}
	}


}
