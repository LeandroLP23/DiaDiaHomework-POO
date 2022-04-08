package it.uniroma3.diadia;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe necessaria per effettuare test non utilizzando IO da console.
 * @author Leandro Placidi
 * @see IO
 */
public class IOSimulator implements IO {

	private List<String> righeDaLeggere;
	private Iterator<String> iRigheDaLeggere;

	private List<String> messaggiProdotti;
	private Iterator<String> iMessaggiProdotti;

	/**
	 * Costruttore della classe che inizializza le operazioni di test.
	 * @param righeDaLeggere Input Output.
	 */
	public IOSimulator(List<String> righeDaLeggere) {
		this.righeDaLeggere = new LinkedList<>();	
		this.righeDaLeggere.addAll(righeDaLeggere);
		this.iRigheDaLeggere= this.righeDaLeggere.iterator();

		this.messaggiProdotti = new LinkedList<String>();	
		this.iMessaggiProdotti= this.messaggiProdotti.iterator();
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti.add(messaggio);
		iMessaggiProdotti = this.messaggiProdotti.iterator();
	}

	@Override
	public String leggiRiga() {
		return this.iRigheDaLeggere.next();

	}

	/**
	 * Metodo che aggiorna il messaggio da eseguire.
	 * @return Stringa contenente messaggio.
	 */
	public String nextMessaggio() {
		return this.iMessaggiProdotti.next();
	}

	/**
	 * Metodo che verifica la presenza di un messaggio succesivo in memoria da eseguire.
	 * @return TRUE se presente, FALSE altrimenti.
	 */
	public boolean hasNextMessaggio() {
		return this.iMessaggiProdotti.hasNext();
	}

}
