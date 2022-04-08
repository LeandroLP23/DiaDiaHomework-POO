package it.uniroma3.diadia.ambienti;

/**
 * Stanza buia, non è possibile guardare o avere descrizione della stanza 
 * se non è presente l'attrezzo corretto nella stanza.
 * @author Leandro Placidi
 */
public class StanzaBuia extends Stanza {
	private String attrezzoLuce;

	/**
	 * Costruttore di una stanza buia.
	 * @param nome String nome stanza.
	 * @param attrezzo String nome attrezzo chiave.
	 */
	public StanzaBuia(String nome, String attrezzo) {
		super(nome);
		this.attrezzoLuce = attrezzo;
	}

	@Override
	public String getDescrizione() {

		if(this.hasAttrezzo(this.attrezzoLuce))
			return super.getDescrizione();
		return "Qui c'è buio pesto";
	}
}
