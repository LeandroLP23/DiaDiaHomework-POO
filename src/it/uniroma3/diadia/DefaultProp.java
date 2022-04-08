package it.uniroma3.diadia;

import it.uniroma3.diadia.DefaultProp;

/**
 * Class ENUM per la gestione dei valori costanti presenti 
 * all'interno della partita, per gestire i valori di default 
 * delle costanti per il file properties.
 * @author Leandro Placidi
 * @see Configurazioni
 *
 */
public enum DefaultProp {

	PESO_MAX, CFU;

	/**
	 * Contiene i valori di Default del delle costanti ENUM che possono 
	 * variare all'inizio di una partita.
	 * @param p Costante Enum.
	 * @return Stringa contenente il valore.
	 */
	public static String getDefaultSingle(DefaultProp p) {
		switch (p) {
		case PESO_MAX: return "10";
		case CFU: return "20";

		default:return null;
		}
	}
}
