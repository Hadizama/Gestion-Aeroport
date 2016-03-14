package models;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

import exceptions.InvalidTacheHoraire;

public class Main {

	public static void main(String[] args) throws IOException {
		
		AgentTempsPlein a = new AgentTempsPlein("sfsdfsdfds", "José", "Bové", 1);

		Tache t1 = new TacheAccueil("tache1", new Horaire(9, 10), new Horaire(10, 10));
		Tache t2 = new TacheAccueil("tache2", new Horaire(10, 30), new Horaire(10, 40));
		a.affecterTache(t1);
		a.affecterTache(t2);
				

//		BufferedReader entree = new BufferedReader	(new FileReader("./ressources/avions16-v1.txt"));
//		String ligne= null;
//		while ((ligne = entree.readLine())!=null){			
//			StringTokenizer decoupe = new StringTokenizer(ligne);
//			new Avion(decoupe.nextToken(), decoupe.nextToken(), Integer.parseInt(decoupe.nextToken()));
//		}
//		
//		entree = new BufferedReader	(new FileReader("./ressources/AgentsMiTemps-16-v1.txt"));
//		ligne= null;
//		while ((ligne = entree.readLine())!=null){			
//			StringTokenizer decoupe = new StringTokenizer(ligne);
//			new AgentMiTemps(decoupe.nextToken(), decoupe.nextToken(), decoupe.nextToken(), Integer.parseInt(decoupe.nextToken()));
//		}
//		
//		entree = new BufferedReader	(new FileReader("./ressources/AgentsTempsPlein-16-v1.txt"));
//		ligne= null;
//		while ((ligne = entree.readLine())!=null){			
//			StringTokenizer decoupe = new StringTokenizer(ligne);
//			new AgentTempsPlein(decoupe.nextToken(), decoupe.nextToken(), decoupe.nextToken(), Integer.parseInt(decoupe.nextToken()));
//		}
//		
//		entree = new BufferedReader	(new FileReader("./ressources/ProgrammeVolsDeparts16-v2.txt"));
//		ligne= null;
//		while ((ligne = entree.readLine())!=null){			
//			StringTokenizer decoupe = new StringTokenizer(ligne);
//			new VolDepart(
//					decoupe.nextToken(), 
//					new Horaire(Integer.parseInt(decoupe.nextToken()), Integer.parseInt(decoupe.nextToken())),
//					decoupe.nextToken(),
//					Avion.lesAvions.get(decoupe.nextToken())
//				);
//		}
//		
//		entree = new BufferedReader	(new FileReader("./ressources/ProgrammeVolsArrivees16-v2.txt"));
//		ligne= null;
//		while ((ligne = entree.readLine())!=null){			
//			StringTokenizer decoupe = new StringTokenizer(ligne);
//			new VolArrivee(
//					decoupe.nextToken(), 
//					new Horaire(Integer.parseInt(decoupe.nextToken()), Integer.parseInt(decoupe.nextToken())),
//					decoupe.nextToken(),
//					Avion.lesAvions.get(decoupe.nextToken())
//				);
//		}

	}

}
