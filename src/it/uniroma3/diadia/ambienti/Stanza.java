package it.uniroma3.diadia.ambienti;

import java.util.*;

import it.uniroma3.diadia.ambienti.npc.AbstractPersonaggio;
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
public class Stanza {

	private String nome;
	private List<Attrezzo> attrezzi;
	private Map<String,Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza.
	 */
	public Stanza(String nome) {		
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<String,Stanza>();
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione Direzione in cui sara' posta la stanza adiacente.
	 * @param stanzaAdiacente Stanza nella direzione indicata dal primo parametro.
	 */
	public void setStanzaAdiacente(String direzione, Stanza stanzaAdiacente) {
		this.stanzeAdiacenti.put(direzione, stanzaAdiacente);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata.
	 * @param direzione Direzione della stanza adiacente.
	 * @return Stanza presente nella direzione in cui si vuole andare.
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce una mappa contenente le stanze adiacenti.
	 * @return Mappa String-Stanza.
	 */
	public Map<String,Stanza> getStanzeAdiacenti(){
		return this.stanzeAdiacenti;
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

	@Override
	public boolean equals(Object o) {
		Stanza s = (Stanza) o;
		return this.getNome().equals(s.getNome());
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode()*31;
	}

	/**
	 * Richiedere il personaggio presente nella stanza.
	 * @return AbstractPersonaggio personaggio.
	 */
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	/**
	 * Impostare il personaggio presente nella stanza.
	 * @param personaggio AbstractPersonaggio da inserire.
	 */
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	/**
	 * Metodo che conta il numero di oggetti presenti nelle stanze
	 * adiacenti a quella in cui ci si trova e ritorna la stanza 
	 * con il numero di oggetti maggiore o minore.
	 * @param condizione Se si vuole il numero maggiore o minore di oggetti.
	 * @return Stanza con il numemero maggiore o minore di oggetti, 
	 * 			a seconda del parametro.
	 */
	public Stanza getStanzaNumeroOggetti(String condizione) {
		TreeSet <Stanza> set = new TreeSet<Stanza>( new Comparator<Stanza>(){
			@Override
			public int compare(Stanza o1, Stanza o2) {
				return -(o1.getAttrezzi().size() - o2.getAttrezzi().size()) ;
			}
		});
		set.addAll(this.getStanzeAdiacenti().values());
		if(condizione.equals("max"))
			return set.first();
		return set.last();
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
		Set <String>direzioni= this.stanzeAdiacenti.keySet();
		risultato.append(direzioni.toString());
		risultato.append("\nAttrezzi nella stanza: ");
		risultato.append(this.attrezzi.toString());
		if(this.personaggio != null) {
			risultato.append("\nNella stanza c'è anche: ");
			risultato.append(this.personaggio.toString());
		}
		return risultato.toString();
	}
}