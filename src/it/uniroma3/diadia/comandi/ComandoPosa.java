package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Tentativo di posare un attrezzo dalla borsa e aggiungerlo nella stanza.
 * @see AbstractComando
 * @author Leandro Placidi
 */
public class ComandoPosa extends AbstractComando {

	@Override
	public void esegui(Partita partita) {

		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
		if(a == null) {
			this.getIO().mostraMessaggio("Non hai introdotto il nome dell'attrezzo!\n");
			return;
		}else {
			partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(a);
			this.getIO().mostraMessaggio("Hai posato " + this.getParametro());
		}
	}
}
