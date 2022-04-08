package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Comando mostra a schermo l'iventario disponibile del giocatore.
 * @see AbstractComando
 * @author Leandro Placidi
 */
public class ComandoInventario extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
}
