package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vue.Fenetre;
import vue.ResultFrame;
import vue.VueAgents;
import vue.VueVols;
import models.*;

public class MainController implements ActionListener{
	
	private Fenetre frame;
	private JPanel vue;

	public MainController (Fenetre f, JPanel v) {
		if(f!=null)
			this.frame = f;
		this.vue = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "Générer le planning":
			this.genererPlanning();
			JOptionPane.showMessageDialog(vue, "La planning à été généré avec succés !", null, 1);
			break;

		case "Gestion des agents":
			frame.setVue(new VueAgents(frame));
			break;
		case "Gestion des vols":
			frame.setVue(new VueVols(frame));
			break;
		case "Taches non affectées":
			if(Tache.listeTachesNonAffectees().size() == 0)
				new ResultFrame("Veuillez generer le planning pour connaitre les taches non affectees.");
			else{
				ResultFrame rf = new ResultFrame(Tache.listeTachesNonAffectees().toString());
				rf.setTitre("Liste des taches non affectees");
			}
			break;
		}			
	}
	
	// Importation de l'ensemble des fichiers contenus dans le dossier "ressources"
	public void importationFichiers(){
		String lvl = "Avions";
		try{
			BufferedReader entree = new BufferedReader (new FileReader("./ressources/avions16-v1.txt"));
			String ligne= null;
			while ((ligne = entree.readLine())!=null){			
				StringTokenizer decoupe = new StringTokenizer(ligne);
				new Avion(decoupe.nextToken(), decoupe.nextToken(), Integer.parseInt(decoupe.nextToken()));
			}
			
			lvl = "Agents Mi-Temps";
			entree = new BufferedReader (new FileReader("./ressources/AgentsMiTemps-16-v1.txt"));
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
	
	
	// Génération du planning pour l'ensemble des agents
	public void genererPlanning(){
		ArrayList<Agent> atp = Agent.trier(AgentTempsPlein.getLesAgentsTempsPlein());
		// Affectation de toutes les tâches repas pour les agents temps plein
		for(int i=0; i<atp.size(); i++){
			atp.get(i).affecterTache(new TacheRepas("Repas", ((AgentTempsPlein) atp.get(i)).getHRepas()));
		}
		ArrayList<Tache> tv = Tache.trier(TacheVol.getLesTachesVol());
		ArrayList<Agent> a = Agent.trier(Agent.getLesAgents());
		// Affectation de toutes les tâches relatives aux vols
		for(Tache t : tv){
			int i = 0;
			while(i<a.size() && !a.get(i).affecterTache(t)){ i++; }
		}
		// Complétion des temps libre supérieur à 30 minutes par des tâches accueil
		for(int i=0; i<a.size();i++){
			a.get(i).affectationTachesAccueil();
		}
	}


}
