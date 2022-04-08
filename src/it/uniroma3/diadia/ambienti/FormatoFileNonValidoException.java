package it.uniroma3.diadia.ambienti;

/**
 * Exception per interaggire con con l'utente.
 * @author Leandro Placidi
 */
public class FormatoFileNonValidoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * EXCEPTION.
	 * @param msg Messaggio da stampare.
	 */
	public FormatoFileNonValidoException(String msg){
		super(msg);
	}
}