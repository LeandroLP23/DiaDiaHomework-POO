package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Permette di salutare il personaggio presente nella stanza.
 * @see AbstractComando
 * @author Leandro Placidi 
 */
public class ComandoSaluta extends AbstractComando {

	private static final String MESSAGGIO_CON_CHI = "Chi dovrei salutare...?";

	@Override
	public void esegui(Partita partita) {
		if(partita.getLabirinto().getStanzaCorrente().getPersonaggio()!=null)
			this.getIO().mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getPersonaggio().saluta());
		else 
			this.getIO().mostraMessaggio(MESSAGGIO_CON_CHI);
	}
}
