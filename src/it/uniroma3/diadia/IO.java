package it.uniroma3.diadia;

/**
 * Interfaccia necessaria per l'accesso alla console in input e in output.
 */
public interface IO {

	/**
	 * Metodo che permette di stampare su terminale.
	 * @param messaggio Stringa da stampare sul terminale.
	 */
	public void mostraMessaggio(String messaggio);

	/**
	 * Metodo che permette di leggere una riga introdotta in input da terminale.
	 * @return La stringa inserita dall'utente.
	 */
	public String leggiRiga();

}