package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Permette di regalare un oggetto presente nell'inventario del 
 * giocatore al personaggio presente nella stanza.
 * @see AbstractComando
 * @author Leandro Placidi 
 */
public class ComandoRegala extends AbstractComando{

	@Override
	public void esegui(Partita partita) {

		if (partita.getLabirinto().getStanzaCorrente().getPersonaggio()== null)
			this.getIO().mostraMessaggio("Non c'è nessuno a cui puoi fare un regalo");

		else if(partita.getGiocatore().getBorsa().hasAttrezzo(getParametro())) 
			this.getIO().mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getPersonaggio().riceviRegalo(
					(partita.getGiocatore().getBorsa().getAttrezzo(getParametro())), partita));
		else
			this.getIO().mostraMessaggio("Hai inserito un attrezzo non presente nell'inventario");
	}
}
