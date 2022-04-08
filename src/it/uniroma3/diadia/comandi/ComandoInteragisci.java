package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.npc.AbstractPersonaggio;

/**
 * Permette di interagire con i personaggi presenti nelle stanze.
 * @see AbstractComando
 * @author Leandro Placidi 
 */
public class ComandoInteragisci extends AbstractComando {

	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	private String messaggio;

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.agisci(partita);
			this.getIO().mostraMessaggio(this.messaggio);

		} else this.getIO().mostraMessaggio(MESSAGGIO_CON_CHI);
	}

	/**
	 * Richiede il messaggio del personaggio con
	 * cui sto interagendo.
	 * @return String messaggio.
	 */
	public String getMessaggio() {
		return this.messaggio;
	}

}
