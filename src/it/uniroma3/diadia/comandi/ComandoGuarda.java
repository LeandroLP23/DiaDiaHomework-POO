package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Comando che permette di avere la descrizione della stanza in cui ci si trova
 * o in una adiacente non bloccata.
 * @see AbstractComando
 * @see Stanza
 * @author Leandro Placidi
 */
public class ComandoGuarda extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		if(this.getParametro()==null) {
			return;
		}else {
			Stanza s = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(this.getParametro());
			if(s!=null) {
				if(partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(this.getParametro())==partita.getLabirinto().getStanzaCorrente())
					this.getIO().mostraMessaggio("Stanza chiusa, non è possibile guardare al suo interno"); 
				else this.getIO().mostraMessaggio("A "+ this.getParametro() +" c'è: " + 
						partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(this.getParametro()).toString()+"\n");
				//"Personaggio presente nella stanza: "+partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(
				// getParametro()).getPersonaggio() +"\n");
			}
			else this.getIO().mostraMessaggio("Non ci sono stanze a "+ this.getParametro() +"!");
		}
	}
}
