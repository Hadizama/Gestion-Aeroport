package exceptions;

public class InvalidTacheRepas extends Exception {
	public String toString(){
		return "L'horaire de la t�che repas doit �tre compris entre 11h30 et 14h";
	}
}
