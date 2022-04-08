package it.uniroma3.diadia.ambienti.npc;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che modella la creazione di un mago.
 * @author Leandro Placidi
 * @see AbstractPersonaggio
 */
public class Mago extends AbstractPersonaggio {

	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private Attrezzo attrezzo;
	private boolean magia;

	/**
	 * Costruttore della classe mago.
	 * @param nome Mago.
	 * @param presentazione Mago.
	 * @param attrezzo Che lascia nella stanza dopo aver parlato con il giocatore.
	 */
	public Mago (String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
		this.magia = false;
	}

	@Override
	public String agisci(Partita partita) {

		if (this.attrezzo!=null) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			return MESSAGGIO_DONO;
		}
		return MESSAGGIO_SCUSE;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder s = new StringBuilder();
		if(this.magia ) {
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			partita.getLabirinto().getStanzaCorrente().addAttrezzo( new Attrezzo( attrezzo.getNome(), attrezzo.getPeso()/2));
			s.append("Grazie mille Matricola, ho apprezzato il gesto, ma non ho bisogno di nulla");
			s.append("ti rendo" + attrezzo.getNome() + " ma con un piccolo regalo");
		}else {
			s.append("Figliolo non posso aiutarti di nuovo, il tuo desiderio è gia stato espresso..");
		}
		return s.toString();
	}
}
