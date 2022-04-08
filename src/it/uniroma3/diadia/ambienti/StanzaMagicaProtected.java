package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Stanza Magica, effettua operazioni magiche dopo il superamento di una data soglia.
 * @author Leandro Placidi
 *
 */
public class StanzaMagicaProtected extends StanzaProtected {

	final static private int SOGLIA_MAGICA_DEFAULT = 1;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	/**
	 * Costruttore stanza Magica con soglia magica di default.
	 * @param nome String nome della stanza.
	 */
	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	/**
	 * Costruttore stanza Magica con soglia magica input.
	 * @param nome String nome della stanza.
	 * @param soglia Valore della soglia magica.
	 */
	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {

		if (attrezzo!=null) {
			this.contatoreAttrezziPosati++;
			if (this.contatoreAttrezziPosati > this.sogliaMagica)
				attrezzo = this.modificaAttrezzo(attrezzo);
			return this.attrezzi.add(attrezzo);
		}
		return false;
	}

	@Override
	protected boolean addAttrezzoSetUp(Attrezzo attrezzo) {
		return this.addAttrezzo(attrezzo);
	}

	/**
	 * Modifica caratteristiche di un attrezzo.
	 * @param attrezzo Attrezzo che va modificato.
	 * @return Attrezzo con peso raddoppiato e nome specchiato.
	 */
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {

		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),pesoX2);
		return attrezzo;
	}

}