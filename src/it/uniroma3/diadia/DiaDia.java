package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

//import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca.
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  Leandro Placidi 
 *          (da un'idea del docente di POO) 
 * @see Stanza
 * @see Attrezzo        
 */

public class DiaDia {

	/**
	 * Messaggio di benvanuto.
	 */
	public static final String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.\n";


	static final private Direzione NORD = Direzione.NORD;
	static final private Direzione SUD = Direzione.SUD;
	static final private Direzione EST = Direzione.EST;
	static final private Direzione OVEST = Direzione.OVEST;

	private Partita partita;
	private IO console;

	/**
	 * Costruttore dal quale viene creata una nuova partita.
	 * Inizializzata inoltre la console I/O.
	 * @param labirinto Labirinto nel quale giocare.
	 * @param io Console IO.
	 */
	public DiaDia(Labirinto labirinto, IO io) {
		this.console = io;
		this.partita = new Partita(labirinto);
	}


	/**
	 * Metodo dal quale si inizia la partita e con cui viene gestito l'Input del giocatore.
	 */
	public void gioca() {

		String istruzione; 
		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   

	/**
	 * Processa un'istruzione. 
	 *
	 * @return False se l'istruzione e' eseguita e il gioco continua, TRUE altrimenti.
	 */
	private boolean processaIstruzione(String istruzione) {

		AbstractComando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione, this.console);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			this.console.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			this.console.mostraMessaggio("Oh no! Hai termintato tutti i tuoi CFU!\n"
					+ "Grazie di aver giocato!\nSe vuoi Reimmatricolati e ricomincia da capo!");
		if(!this.partita.isFinita())
			this.console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		return this.partita.isFinita();
	}  

	/**
	 * Metodo main che viene utilizzato per dare inizio alla partita,
	 * richiamando i metodi adeguati.
	 * 
	 * @param argc Argomento fornito in input da console.
	 */

	@SuppressWarnings("unused")
	public static void main(String[] argc) {
		try (Scanner scannerDiLinee = new Scanner(System.in)){
			IO io = new IOConsole(scannerDiLinee);
			Labirinto labirinto = Labirinto.newBuilder()
					//Atrio
					.addStanzaIniziale("Atrio")
					.addAttrezzo("osso", 1)
					.addAttrezzo("chiave", 1)
					.addStrega("Strega","Il mio nome è Carolina, la strega che cucina!")

					//Aula N11
					.addStanzaBuia("Aula N11","lanterna")

					//Aula N10
					.addStanzaBloccata("Laboratorio","chiave", OVEST)
					.addAttrezzo("lanterna", 2)
					.addMago("Mago","Ciao sono un mago", "pipa",1)

					//Laboratorio
					.addStanzaMagica("Aula N10")
					.addAttrezzo("spada", 3)
					.addAttrezzo("spadone", 5)
					.addCane("Cane","GRR BAU")

					.addStanzaVincente("Biblioteca")

					//SetUp Adiacenze
					.addAdiacenza("Atrio", "Biblioteca", NORD)
					.addAdiacenza("Atrio","Aula N11",EST)
					.addAdiacenza("Atrio","Aula N10",SUD)
					.addAdiacenza("Atrio","Laboratorio",OVEST)
					.addAdiacenza("Aula N11","Laboratorio",EST)

					.getLabirinto();

			DiaDia gioco = new DiaDia(new Labirinto("a"),io);
			gioco.gioca();
		}
	}
}