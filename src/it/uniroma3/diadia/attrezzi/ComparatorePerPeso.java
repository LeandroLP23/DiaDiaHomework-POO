package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

/**
 * Classe per permette la comparazione di due attrezzi
 * rispetto il peso. (In caso di uguaglianza, per nome).
 * @author Leandro Placidi
 * @see Attrezzo
 */
public class ComparatorePerPeso implements Comparator<Attrezzo>{

	@Override
	public int compare(Attrezzo o1, Attrezzo o2) {
		if( o1.getPeso() - o2.getPeso()==0)
			//compareTo di attrezzo compara per nome
			return o1.compareTo(o2);
		return o1.getPeso() - o2.getPeso();
	}

}
