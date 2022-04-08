package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore.
 * @see AbstractComando
 * @see Stanza
 * @author Leandro Placidi
 */
public class ComandoVai extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(this.getParametro()==null) {
			this.getIO().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.getParametro());
		if(prossimaStanza==null) {
			this.getIO().mostraMessaggio("Direzione inesistente");
			return;
		}

		if(partita.getLabirinto().getStanzaCorrente()!=prossimaStanza)
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);

		partita.getLabirinto().setStanzaCorrente(prossimaStanza);
	}
}
