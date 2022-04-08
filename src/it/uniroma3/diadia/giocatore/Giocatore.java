package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configurazioni;

/**
 * Classe che gestisce il giocatore, ed il punteggio (CFU) di esso.
 * Da questa classe si può accedere alla borsa.
 * @author Leandro Placidi
 * @see Borsa
 */
public class Giocatore {

	static final private int CFU_INIZIALI = Configurazioni.getCFU();
	private int cfu;
	private Borsa borsa;

	/**
	 * Costruttore della classe Giocatore.
	 * Imposta il valore dei CFU iniziali attraverso il valore presente nel file
	 */
	public Giocatore(){
		this(CFU_INIZIALI);
	}

	/**
	 * Costruttore della classe Giocatore.
	 * Imposta il valore dei CFU iniziali attraverso il parametro.
	 * @param cfu Il punteggio dal quale inziare.
	 */
	public Giocatore(int cfu){
		this.cfu = cfu;
		this.borsa = new Borsa();
	}

	/**
	 * Restituisce la borsa del giocatore.
	 * @return La borsa del giocatore.
	 */
	public Borsa getBorsa() {
		return this.borsa;
	}

	/**
	 * Restituisce se il giocatore è vivo o morto.
	 * @return TRUE se giocatore vivo, FALSE altrimenti.
	 */
	public boolean isVivo() {
		return this.cfu >0;
	}

	/**
	 * Restituisce i cfu attuali del giocatore.
	 * @return Il numero di  cfu attuali del giocatore.
	 */
	public int getCfu() {
		return this.cfu;
	}

	/**
	 * Imposta i cfu attuali del giocatore.
	 * @param cfu da andare a sostituire con quelli attuali del giocatore.
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
}
