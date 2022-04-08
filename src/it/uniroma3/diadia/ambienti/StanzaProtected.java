package it.uniroma3.diadia.ambienti;

import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author Leandro Placidi
 * @see Attrezzo
 */

public class StanzaProtected {

	//	static final private String[] DIREZIONI = {"nord","sud", "ovest", "est"};
	private String nome;
	protected List<Attrezzo> attrezzi;
	private List<Stanza> stanzeAdiacenti;
	private ArrayList<String> direzioni;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza.
	 */
	public StanzaProtected(String nome) {		
		this.nome = nome;
		this.direzioni = new ArrayList<String>();
		this.stanzeAdiacenti = new ArrayList<Stanza>();
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione Direzione in cui sara' posta la stanza adiacente.
	 * @param stanza Stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void setStanzaAdiacente(String direzione, Stanza stanza) {

		//Se non ho gia un stanza nella direzione introdotta come paramentro, aggiungo la direzione introdotta e poi la stanza
		if(!this.direzioni.contains(direzione)) {
			this.direzioni.add(direzione);
			this.stanzeAdiacenti.add(stanza);
		}else{
			//ho gia la direzione introdotta come paramentro percio aggiongo la stanza nella posizione corretta
			this.stanzeAdiacenti.set(this.direzioni.indexOf(direzione), stanza);
		}

	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata.
	 * @param direzione Direzione della stanza adiacente.
	 * @return Stanza presente nella direzione in cui si vuole andare.
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		if(this.direzioni.contains(direzione))
			return this.stanzeAdiacenti.get(this.direzioni.indexOf(direzione));
		return null;
	}

	/**
	 * Restituisce il nome della stanza.
	 * @return Il nome della stanza.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return La descrizione della stanza.
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return La collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo L'attrezzo da mettere nella stanza.
	 * @return TRUE se riesce ad aggiungere l'attrezzo, FALSE altrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo!=null)
			return this.attrezzi.add(attrezzo);
		return false;
	}

	/**
	 * Opera un inserimento di attrezzo all'interno della stanza durante la creazione del livello.
	 * @param attrezzo Attrezzo da inserire
	 * @return TRUE se riesce ad aggiungere l'attrezzo, FALSE altrimenti.
	 */
	protected boolean addAttrezzoSetUp(Attrezzo attrezzo) {
		return this.addAttrezzo(attrezzo);
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @param nomeAttrezzo Stringa dell'attrezzo.
	 * @return TRUE se l'attrezzo esiste nella stanza, FALSE altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo!=null) {
			Attrezzo temp = new Attrezzo(nomeAttrezzo,0);
			return this.attrezzi.contains(temp);
		}
		return false;
	}


	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo Stringa del nome dell'attrezzo.
	 * @return L'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo!=null) {
			Attrezzo temp = new Attrezzo(nomeAttrezzo, 0);
			if(this.attrezzi.contains(temp))
				return this.attrezzi.get(this.attrezzi.indexOf(temp));
		}
		return null;	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param attrezzo Classe Attrezzo dell'attrezzo da rimuovere.
	 * @return TRUE se l'attrezzo e' stato rimosso, FALSE altrimenti.
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo);

	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti.
	 * @return la rappresentazione stringa.
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.direzioni)
			if (direzione!=null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo!=null) {
				risultato.append(attrezzo.toString()+" ");
			}
		}
		return risultato.toString();
	}
}