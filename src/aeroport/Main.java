package aeroport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader entreeAvion = new BufferedReader	(new FileReader("./ressources/avions16-v1.txt"));
		String ligne= null;
		while ((ligne = entreeAvion.readLine())!=null){			
			StringTokenizer decoupe = new StringTokenizer(ligne);
			Avion avion = new Avion(decoupe.nextToken(), decoupe.nextToken(), Integer.parseInt(decoupe.nextToken()));
		}
		
		System.out.println("Welcome");

	}

}
