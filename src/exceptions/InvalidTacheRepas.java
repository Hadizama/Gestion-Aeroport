package exceptions;

public class InvalidTacheRepas extends Exception {
	public String toString(){
		return "L'horaire de la tâche repas doit être compris entre 11h30 et 14h";
	}
}
