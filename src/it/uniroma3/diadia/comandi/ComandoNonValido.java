package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Comando non valido introdotto dall'utente.
 * @see AbstractComando
 * @author Leandro Placidi
 */
public class ComandoNonValido extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio("Comando non valido");
	}
}
