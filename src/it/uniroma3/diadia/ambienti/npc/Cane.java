package it.uniroma3.diadia.ambienti.npc;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che modella la creazione di un cane.
 * @author Leandro Placidi
 * @see AbstractPersonaggio
 */
public class Cane extends AbstractPersonaggio{

	final static private String MESSAGGIO_FAME = "GRRR... WHOAF! (Il cane ti morde, diminuendo i tuoi CFU di 1)";
	final static private String MESSAGGIO_BUONO = "Bau Bau!";
	final static private String ATTREZZO = "osso";
	private boolean ricevutoRegalo;

	/**
	 * Costruttore della classe cane.
	 * @param nome cane.
	 * @param presentaz cane.
	 */
	public Cane(String nome, String presentaz) {
		super(nome, presentaz);
		this.ricevutoRegalo = false;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MESSAGGIO_FAME;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {

		if(!ricevutoRegalo) {
			if(attrezzo.getNome().equals(ATTREZZO)) {
				ricevutoRegalo = true;
				partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
				partita.getLabirinto().getStanzaCorrente().addAttrezzo(new Attrezzo("sasso",1));
				return ( MESSAGGIO_BUONO +"\n Per ringraziarti, il cane ti dona l'unico attrezzo a sua disposizione" );      
			} 
			return ((agisci(partita) + "Forse il cane non ha apprezzato "+ attrezzo.getNome() + ". Il suo morso dovrebbe esserti d'aiuto!"));
		}
		return (agisci(partita) + "Attenzione, il cane ha già mangiato, non penso voglia esser disturbato di nuovo!");
	}
}