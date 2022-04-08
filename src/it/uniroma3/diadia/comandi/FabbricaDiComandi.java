package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;

/**
 * Interfaccia necessaria per creare i comandi.
 * @see AbstractComando
 * @author Leandro Placidi
 */
public interface FabbricaDiComandi {

	/**
	 * Costruzione del comando istruzione introdotto in input dell'utente.
	 * @param istruzione String comando da eseeguire.
	 * @param console Console IO.
	 * @return Comando suddiviso in nome e parametro.
	 * @see AbstractComando
	 */
	public AbstractComando costruisciComando(String istruzione, IO console) ;
}
