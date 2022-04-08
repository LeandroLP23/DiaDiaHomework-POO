package it.uniroma3.diadia.ambienti;

/**
 * Stanza con una direzione bloccata, si blocca se la stanza contiene attrezzo specifico.
 * @author Leandro Placidi
 * 
 */
public class StanzaBloccata extends Stanza {

	private String direzioneBloccata;
	private String attrezzoChiave;

	/**
	 * Costruttore di una stanza con una direzione bloccata.
	 * @param nome Stringa nome della stanza.
	 * @param direzione Stringa direzione bloccata.
	 * @param attrezzo Stringa attrezzo chiave della direzione.
	 */
	public StanzaBloccata(String nome, String direzione, String attrezzo) {
		super(nome);
		this.direzioneBloccata = direzione;
		this.attrezzoChiave = attrezzo;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {	
		if (this.direzioneBloccata.equals(direzione) && this.hasAttrezzo(this.attrezzoChiave)||!this.direzioneBloccata.equals(direzione) )
			return super.getStanzaAdiacente(direzione);
		return this;
	}

	@Override
	public String getDescrizione() {
		if(!this.hasAttrezzo(this.attrezzoChiave))
			return "Ti trovi in una stanza con una una porta a "+ this.direzioneBloccata+
					" chiusa e ha bisogno di " + this.attrezzoChiave + " per aprire la porta\n"+ super.toString();
		return super.getDescrizione();

	}
}
