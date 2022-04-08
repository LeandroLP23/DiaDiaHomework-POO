package it.uniroma3.diadia.ambienti.npc;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoInteragisci;
import it.uniroma3.diadia.comandi.ComandoRegala;

/**
 * Classe astratta per la gestione di Personaggi
 * Non Giocanti.
 * @author Leandro Placidi
 */
public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	private boolean haSalutato;

	/**
	 * Costruttore di un personaggio.
	 * @param nome personaggio .
	 * @param presentaz presentazione.
	 */
	public AbstractPersonaggio(String nome, String presentaz) {
		this.nome = nome;
		this.presentazione = presentaz;
		this.haSalutato = false;
	}

	/**
	 * Metodo che permette al personaggio di salutare
	 * il giocatore (che lo ha salutato a sua volta).
	 * @return String messaggio.
	 */
	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono ");

		risposta.append(this.getNome()+".");
		if (!haSalutato)
			risposta.append(this.presentazione);
		else
			risposta.append("Ci siamo gia' presentati!");

		this.haSalutato = true;
		return risposta.toString();
	}

	/**
	 * Azione del personaggio dopo che il giocatore esegue
	 * {@link ComandoInteragisci}.
	 * @param partita Partita in cui si trova.
	 * @return String messaggio.
	 */
	abstract public String agisci(Partita partita);

	/**
	 * Comportamento del personaggio dopo che il giocatore esegue 
	 * {@link ComandoRegala}.
	 * @param attrezzo Attrezzo ricevuto.
	 * @param partita Partita in cui si trova.
	 * @return String messaggio.
	 */
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);

	@Override
	public String toString() {
		return this.getNome();
	}

	/**
	 * Richiedere il nome del personaggio.
	 * @return nome personaggio.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Verificare se il personaggio ha gia salutato il giocatore.
	 * @return TRUE se ha salutato, FALSE altrimenti
	 */
	public boolean haSalutato() {
		return this.haSalutato;
	}
}