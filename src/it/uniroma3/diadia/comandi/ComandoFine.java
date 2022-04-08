package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Comando fine partita.
 * @see AbstractComando
 * @author Leandro Placidi
 */
public class ComandoFine extends AbstractComando {

	/**
	 * Messaggio di fine partita.
	 */
	public static final String MESSAGGIO_FINE = "" +
			"Hai deciso di abbandonare la partita nonostante non ti "+
			"avessero ancora bocciato, ripresentati al prossimo appello!";

	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio(ComandoFine.MESSAGGIO_FINE + 
				"\nAvevi ancora "+ partita.getGiocatore().getCfu()+" CFU!");
		partita.setFinita();
	}

}
