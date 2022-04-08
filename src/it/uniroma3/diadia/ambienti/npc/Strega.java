package it.uniroma3.diadia.ambienti.npc;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe creazione di un personaggio strega.
 * Se la strega viene salutata trasporta il giocatore nella stanza 
 * con più oggetti, altrimenti in quella che ne contiene di meno.
 * @author Leandro Placidi
 * @see AbstractPersonaggio
 */
public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_BUONA = "Sei una matricola educa, " +
			"ti porto in una stanza che possa esserti d'aiuto!";
	private static final String MESSAGGIO_CATTIVA = "Mi spiace, ma i maleducati," +
			"non li sopporto proprio!";

	/**
	 * Costruttore della classe strega.
	 * @param nome Strega.
	 * @param presentazione Strega.
	 */
	public Strega (String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		String tmp;
		if (this.haSalutato()) {
			tmp ="max";
			msg = MESSAGGIO_BUONA;}
		else {
			tmp = "min";
			msg = MESSAGGIO_CATTIVA;
		}

		Stanza s= partita.getLabirinto().getStanzaCorrente().getStanzaNumeroOggetti(tmp);
		partita.getLabirinto().setStanzaCorrente(s);

		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder s = new StringBuilder();
		if(attrezzo!=null) {
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			s.append("Grazie mille imbecille, ma non penso avrai nulla indietro da me...");
			s.append("Grazie per " + attrezzo.getNome() + " AHAHHAHAHAHAHAHAHAHA");
		}
		return s.toString();
	}
}

