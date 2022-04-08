package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Classe che modella una partita del gioco.
 * @author Leandro Placidi
 * @see Stanza
 * @see Labirinto
 * @see Giocatore
 */

public class Partita {

	private Giocatore giocatore;
	private Labirinto labirinto;
	private boolean finita;

	/**
	 * Costruttore esegue l'inizio alla partita, 
	 * creando le stanze ed un nuovo giocatore.
	 */
	public Partita(){
		this.giocatore = new Giocatore();
		this.labirinto = new Labirinto();
		this.finita = false;
	}
	/**
	 * Costruttore che genera un partita nel labirinto 
	 * passato per parametro.
	 * @param labirinto di tipo Labirinto.
	 */
	public Partita(Labirinto labirinto){
		this();
		setLabirinto(labirinto);
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta.
	 * @return TRUE se partita vinta, FALSE altrimenti.
	 */
	public boolean vinta() {
		return labirinto.getStanzaCorrente() == labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita.
	 * @return TRUE se partita finita, FALSE altrimenti.
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita.
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	/**
	 * Restituisce il labirinto.
	 * @return Il labirinto.
	 */
	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	/**
	 * Imposta il livello di tipo Labirinto in cui si sta giocando la partita.
	 * @param labirinto Livello in cui ci si trova.
	 */
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}

	/**
	 * Restituisce il giocatore.
	 * @return Il giocatore.
	 */
	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	/**
	 * Imposta il giocatore che sta giocando la partita
	 * @param giocatore Giocatore che sta giocando la partita.
	 */
	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	/**
	 * Metodo che informa se il giocatore è vivo o meno.
	 * @return TRUE se il giocatore è vivo, FALSE altrimenti.
	 */
	public boolean giocatoreIsVivo() {
		return this.getGiocatore().isVivo();
	}
}
