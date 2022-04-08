package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Tentativo di prendere un attrezzo dalla stanza e aggiungerlo nella borsa.
 * @see AbstractComando
 * @author Leandro Placidi
 */
public class ComandoPrendi extends AbstractComando {

	@Override
	public void esegui(Partita partita) {

		Attrezzo a = partita.getLabirinto().getStanzaCorrente().getAttrezzo(this.getParametro());
		if(a==null) {
			this.getIO().mostraMessaggio("Non hai introdotto il nome corretto dell'attrezzo!\n");
			return;
		}
		else if(partita.getGiocatore().getBorsa().getPeso() + a.getPeso() > partita.getGiocatore().getBorsa().getPesoMax())
			this.getIO().mostraMessaggio("L'attrezzo inserito è troppo pesante!\nDovresti posare qualche attrezzo!\n");
		else {
			partita.getGiocatore().getBorsa().addAttrezzo(a);
			partita.getLabirinto().getStanzaCorrente().removeAttrezzo(a);
			this.getIO().mostraMessaggio("Hai recuperato " + this.getParametro()+"\n");
		}
	}
}
