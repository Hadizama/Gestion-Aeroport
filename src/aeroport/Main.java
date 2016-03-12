package aeroport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader entree = new BufferedReader	(new FileReader("./ressources/avions16-v1.txt"));
		String ligne= null;
		while ((ligne = entree.readLine())!=null){			
			StringTokenizer decoupe = new StringTokenizer(ligne);
			new Avion(decoupe.nextToken(), decoupe.nextToken(), Integer.parseInt(decoupe.nextToken()));
		}
		
		entree = new BufferedReader	(new FileReader("./ressources/AgentsMiTemps-16-v1.txt"));
		ligne= null;
		while ((ligne = entree.readLine())!=null){			
			StringTokenizer decoupe = new StringTokenizer(ligne);
			new AgentMiTemps(decoupe.nextToken(), decoupe.nextToken(), decoupe.nextToken(), Integer.parseInt(decoupe.nextToken()));
		}
		
		entree = new BufferedReader	(new FileReader("./ressources/AgentsTempsPlein-16-v1.txt"));
		ligne= null;
		while ((ligne = entree.readLine())!=null){			
			StringTokenizer decoupe = new StringTokenizer(ligne);
			new AgentTempsPlein(decoupe.nextToken(), decoupe.nextToken(), decoupe.nextToken(), Integer.parseInt(decoupe.nextToken()));
		}
		
		entree = new BufferedReader	(new FileReader("./ressources/ProgrammeVolsDeparts16-v2.txt"));
		ligne= null;
		while ((ligne = entree.readLine())!=null){			
			StringTokenizer decoupe = new StringTokenizer(ligne);
			new VolDepart(
					decoupe.nextToken(), 
					new Horaire(Integer.parseInt(decoupe.nextToken()), Integer.parseInt(decoupe.nextToken())),
					decoupe.nextToken(),
					Avion.lesAvions.get(decoupe.nextToken())
				);
		}
		
		entree = new BufferedReader	(new FileReader("./ressources/ProgrammeVolsArrivees16-v2.txt"));
		ligne= null;
		while ((ligne = entree.readLine())!=null){			
			StringTokenizer decoupe = new StringTokenizer(ligne);
			new VolArrivee(
					decoupe.nextToken(), 
					new Horaire(Integer.parseInt(decoupe.nextToken()), Integer.parseInt(decoupe.nextToken())),
					decoupe.nextToken(),
					Avion.lesAvions.get(decoupe.nextToken())
				);
		}

	}

}
