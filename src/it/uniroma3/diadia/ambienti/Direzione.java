package it.uniroma3.diadia.ambienti;

/**
 * Classe ENUM che contiene le direzioni possibili in cui
 * è possibile collegare le stanze tra di loro all'interno 
 * del labirinto.
 * @author Leandro Placidi
 */
public enum Direzione {
	NORD, EST, SUD, OVEST;

	/**
	 * Ritorna la direzione opposta.
	 * @param direzione.
	 * @return rispettiva direzione opposta.
	 */
	public static Direzione opposta(Direzione direzione) {
		switch(direzione) {
		case NORD: return SUD;
		case EST: return OVEST;
		case SUD: return NORD;
		case OVEST: return EST;
		default: return null;
		}
	}
}

