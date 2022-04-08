package it.uniroma3.diadia.ambienti;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

/**
 * Classe che permette di caricare il labirinto da file.
 * @author Leandro Placidi
 * @see Labirinto
 */
public class CaricatoreLabirinto {

	private static final String FINE = "Fine";
	private final String  STANZE   = "Stanze:";
	private final String  MAGICHE   = "Magica:";
	private final String  BUIE   = "Buia:";
	private final String  BLOCCATE   = "Bloccata:";
	private final String  MAGO   = "Mago:";
	private final String  CANE = "Cane:";
	private final String  STREGA= "Strega:";
	private final String  ESTREMI = "Estremi:";
	private final String  ATTREZZI = "Attrezzi:";
	private final String  USCITE   = "Uscite:";
	private BufferedReader reader;
	private LabirintoBuilder builder;
	private String riga;

	private int numeroLinea;

	/**
	 * Costruttore generico che permette di caricare il 
	 * labirinto anche da testo e non solo da file.
	 * @param nomeFile testo comprendente creazione del labirinto.
	 * @throws FileNotFoundException exception
	 */
	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this(new FileReader(nomeFile));
	}

	/**
	 * Costruttore che permette la creazione attraverso un
	 * Reader passato come parametro.
	 * @param reader Gia connesso al file.
	 */
	public CaricatoreLabirinto(Reader reader) {
		this.builder = Labirinto.newBuilder();
		this.numeroLinea = 0;
		this.reader = new BufferedReader(reader);
		this.riga= null;
	}

	/**
	 * Caricamento delle informazioni dal file.
	 */
	public void carica() {
		try {
			this.riga = this.leggiRiga(reader);
			this.leggiStanze();
			this.leggiInizialeEvincente();
			this.leggiMago();
			this.leggiCane();
			this.leggiStrega();
			this.leggiAttrezzi();
			this.leggiUscite();
		} catch (FormatoFileNonValidoException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	/**
	 * Lettore riga singola.
	 * @throws FormatoFileNonValidoException
	 */
	private String leggiRiga(BufferedReader reader) throws FormatoFileNonValidoException {
		try {
			this.numeroLinea++;
			String riga = reader.readLine();
			System.err.println("Letta riga "+ this.numeroLinea + ": "+ riga);
			return riga;
		} catch (IOException e) {
			throw new FormatoFileNonValidoException("Problemi lettura file [" + this.numeroLinea + "]");
		}
	}

	/**
	 * Caricamento dei Maghi dal file.
	 * @throws FormatoFileNonValidoException
	 */
	private void leggiMago() throws FormatoFileNonValidoException {
		this.riga = this.leggiRiga(reader);//riga da file
		String nomeMago = null;
		String nomeStanza = null;
		String presentazione = null;
		String nomeAttrezzo = null;
		String pesoAttrezzo = null;

		//se la riga non contiene mago, errore
		if (!this.riga.contains(MAGO))
			throw new FormatoFileNonValidoException("Formato file non valido [" +
					this.numeroLinea + "]"+": "+MAGO +" non trovato");

		do {
			//contiene mago, allora provo a leggere qualcosa
			try (Scanner scannerLinea = new Scanner(this.riga)){
				//finche ci sono parole dopo MAGO e non sono CANE allora le prendo
				while(scannerLinea.hasNext() && !this.riga.contains(CANE)) {
					nomeMago = scannerLinea.next();
					if (nomeMago == null)
						throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
					if(!nomeMago.equals(MAGO)) {
						nomeStanza = scannerLinea.next();
						nomeAttrezzo = scannerLinea.next();
						pesoAttrezzo = scannerLinea.next();
						int peso;

						if (nomeStanza == null)
							throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
						if (nomeAttrezzo == null)
							throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
						try {
							peso = Integer.parseInt(pesoAttrezzo);
						}
						catch (NumberFormatException e) {
							throw new FormatoFileNonValidoException("Peso attrezzo "+nomeAttrezzo+" non valido [" + this.numeroLinea + "].");
						}
						if(scannerLinea.hasNext()) // non ci devono essere altre parole sulla riga
							throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
						//presentazione del mago
						this.riga = this.leggiRiga(reader);
						if(this.riga.contains(CANE))//deve esserci una presentazione
							throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
						presentazione = riga.replace("(", "").replace(")", "");//rimuovo parentesi
						this.builder.addMago(nomeMago, presentazione, nomeAttrezzo, peso, nomeStanza);
					}
				}
				while (!this.riga.contains(CANE) && scannerLinea.hasNext());

				if(!this.riga.contains(CANE) && !scannerLinea.hasNext())
					riga = this.leggiRiga(reader);
			}
		}while(!this.riga.contains(CANE));
	}

	/* Versione alterntiva */
	//	private void leggiMago() throws FormatoFileNonValidoException {
	//		this.riga = this.leggiRiga(reader);//riga da file
	//		String nomeMago = null;
	//		String nomeStanza = null;
	//		String presentazione = null;
	//		String nomeAttrezzo = null;
	//		String pesoAttrezzo = null;
	//
	//		//se la riga non contiene mago, errore
	//		if (!this.riga.contains(MAGO))
	//			throw new FormatoFileNonValidoException("Formato file non valido [" +
	//					this.numeroLinea + "]"+": "+MAGO +" non trovato");
	//
	//		//contiene mago, allora provo a leggere qualcosa
	//		try (Scanner scannerLinea = new Scanner(this.riga)){
	//			//finche ci sono parole dopo MAGO e non sono CANE allora le prendo
	//			while(scannerLinea.hasNext()&& !riga.contains(CANE)) {
	//				scannerLinea.next();
	//				nomeMago = scannerLinea.next();
	//				nomeStanza = scannerLinea.next();
	//				nomeAttrezzo = scannerLinea.next();
	//				pesoAttrezzo = scannerLinea.next();
	//				int peso;
	//
	//				if (nomeMago == null)
	//					throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
	//				if (nomeStanza == null)
	//					throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
	//				if (nomeAttrezzo == null)
	//					throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
	//				try {
	//					peso = Integer.parseInt(pesoAttrezzo);
	//				}
	//				catch (NumberFormatException e) {
	//					throw new FormatoFileNonValidoException("Peso attrezzo "+nomeAttrezzo+" non valido [" + this.numeroLinea + "].");
	//				}
	//
	//				if(scannerLinea.hasNext()) // non ci devono essere altre parole sulla riga
	//					throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
	//				//presentazione del mago
	//				this.riga = this.leggiRiga(reader);
	//				if(this.riga.contains(CANE))//deve esserci una presentazione
	//					throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
	//				presentazione = riga.replace("(", "").replace(")", "");//rimuovo parentesi
	//				this.builder.addMago(nomeMago, presentazione, nomeAttrezzo, peso, nomeStanza);
	//				this.riga = this.leggiRiga(reader);
	//			}
	//		}
	//	}

	/**
	 * Caricamento dei Cane dal file.
	 * @throws FormatoFileNonValidoException
	 */
	private void leggiCane() throws FormatoFileNonValidoException {

		String nomeCane = null;
		String nomeStanza = null;
		String presentazione = null;

		if (!riga.contains(CANE))
			throw new FormatoFileNonValidoException("Formato file non valido [" +
					this.numeroLinea + "]"+": "+CANE +" non trovato");

		while(!riga.contains(STREGA))
			try (Scanner scannerLinea = new Scanner(this.riga)){
				scannerLinea.next();
				while(scannerLinea.hasNext()) {
					nomeCane = scannerLinea.next();
					nomeStanza = scannerLinea.next();
					if (nomeCane == null)
						throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
					if (nomeStanza == null)
						throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
					if(scannerLinea.hasNext())
						throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
					this.riga = this.leggiRiga(reader);
					if(this.riga.contains(STREGA))
						throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
					presentazione = riga.replace("(", "").replace(")", "");
					this.builder.addCane(nomeCane, presentazione,nomeStanza);
					this.riga = this.leggiRiga(reader);
				}
			}
	}

	/**
	 * Caricamento dei Strega dal file.
	 * @throws FormatoFileNonValidoException
	 */
	private void leggiStrega() throws FormatoFileNonValidoException {

		String nomeStrega = null;
		String nomeStanza = null;
		String presentazione = null;

		if (!this.riga.contains(STREGA))
			throw new FormatoFileNonValidoException("Formato file non valido [" +
					this.numeroLinea + "]"+": "+STREGA +" non trovato");

		while(!this.riga.contains(ATTREZZI))
			try (Scanner scannerLinea = new Scanner(this.riga)){
				scannerLinea.next();
				while(scannerLinea.hasNext() ) {
					nomeStrega = scannerLinea.next();
					nomeStanza = scannerLinea.next();
					if (nomeStrega == null)
						throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
					if (nomeStanza == null)
						throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
					if(scannerLinea.hasNext())
						throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
					this.riga = this.leggiRiga(reader);
					if(this.riga.contains(ATTREZZI))
						throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
					presentazione = this.riga.replace("(", "").replace(")", "");
					this.builder.addStrega(nomeStrega, presentazione,nomeStanza);
					this.riga = this.leggiRiga(reader);
				}
			}
	}


	/**
	 * Lettore stanza iniziale e vincente.
	 * @throws FormatoFileNonValidoException
	 */
	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		String nomeStanzaVincente = null;
		try (Scanner scanner = new Scanner(this.riga)){
			if(scanner.hasNext()) {
				scanner.next(); //salto la descrizione della riga
				if(scanner.hasNext())
					nomeStanzaIniziale = scanner.next();
				if (!this.stanzaValida(nomeStanzaIniziale))
					throw new FormatoFileNonValidoException("Formato file non valido [" + this.numeroLinea + "]: stanza "+ nomeStanzaIniziale +" non definita");
				if(scanner.hasNext())
					nomeStanzaVincente = scanner.next();
				if (!this.stanzaValida(nomeStanzaVincente))
					throw new FormatoFileNonValidoException("Formato file non valido [" + this.numeroLinea + "]: stanza"+ nomeStanzaVincente+" non definita");
				if(scanner.hasNext())
					throw new FormatoFileNonValidoException("Formato file non valido [" + this.numeroLinea + "]:" +ATTREZZI +" non trovato");		

				this.builder.addStanzaIniziale(nomeStanzaIniziale);
				this.builder.addStanzaVincente(nomeStanzaVincente);
			}
		}
	}

	/**
	 * Istanzia la lettura di tutte le stanze.
	 * @throws FormatoFileNonValidoException
	 */
	private void leggiStanze() throws FormatoFileNonValidoException  {
		if (!this.riga.contains(STANZE))
			throw new FormatoFileNonValidoException("Formato file non valido [" +
					this.numeroLinea + "]"+": "+STANZE +" non trovato");
		try {
			this.leggiStanzeNormali();

			this.leggiStanzeMagiche();

			this.leggiStanzeBuie();

			this.leggiStanzeBloccate();

		}catch(NullPointerException e) {
			throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
		}
	}

	/**
	 * Lettore delle stanze Normali.
	 * @throws FormatoFileNonValidoException
	 */
	private void leggiStanzeNormali() throws FormatoFileNonValidoException {
		String nomeStanza = null;
		try (Scanner scannerLinea = new Scanner(this.riga)){
			nomeStanza=scannerLinea.next();
			while(scannerLinea.hasNext() && !nomeStanza.equals(MAGICHE)) {
				nomeStanza = scannerLinea.next();
				if (nomeStanza == null)
					throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
				this.builder.addStanza(nomeStanza);
			}
			this.riga= this.leggiRiga(reader);
		}
	}

	/**
	 * Lettore delle stanze Magiche.
	 * @throws FormatoFileNonValidoException
	 */
	private void leggiStanzeMagiche() throws FormatoFileNonValidoException {
		String nomeStanza = null;
		try (Scanner scannerLinea = new Scanner(this.riga)){
			nomeStanza = scannerLinea.next();
			while(scannerLinea.hasNext() && !nomeStanza.equals(BUIE)) {
				nomeStanza = scannerLinea.next();
				if (nomeStanza == null)
					throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
				this.builder.addStanzaMagica(nomeStanza);
			}
			this.riga = this.leggiRiga(reader);
		}
	}

	/**
	 * Lettore delle stanze Buie.
	 * @throws FormatoFileNonValidoException
	 */
	private void leggiStanzeBuie() throws FormatoFileNonValidoException {
		String attrezzo = null;
		String nomeStanza = null;

		try (Scanner scannerLinea = new Scanner(this.riga)){
			nomeStanza = scannerLinea.next();
			while(scannerLinea.hasNext()&& !nomeStanza.equals(BLOCCATE)) {
				nomeStanza = scannerLinea.next();
				if (nomeStanza == null)
					throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
				if(scannerLinea.hasNext())
					attrezzo = scannerLinea.next();
				this.builder.addStanzaBuia(nomeStanza,attrezzo);
			}
			this.riga=this.leggiRiga(reader);
		}
	}

	/**
	 * Lettore delle stanze Bloccate.
	 * @throws FormatoFileNonValidoException
	 */
	private void leggiStanzeBloccate() throws FormatoFileNonValidoException {
		String attrezzo = null;
		String direzione = null;
		String nomeStanza = null;
		try (Scanner scannerLinea = new Scanner(this.riga)){
			nomeStanza = scannerLinea.next();
			while(scannerLinea.hasNext()&& !nomeStanza.equals(ESTREMI)) {
				nomeStanza = scannerLinea.next();
				if (nomeStanza == null)
					throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
				if(scannerLinea.hasNext())
					attrezzo= scannerLinea.next();
				if(scannerLinea.hasNext())
					direzione= scannerLinea.next();
				this.builder.addStanzaBloccata(nomeStanza,attrezzo, Direzione.valueOf(direzione.toUpperCase()));
			}
			this.riga = this.leggiRiga(reader);
		}
	}

	/**
	 * Lettore delle stanze Attrezzi.
	 * @throws FormatoFileNonValidoException
	 */
	private void leggiAttrezzi() throws FormatoFileNonValidoException {
		String nomeAttrezzo = null;
		String pesoAttrezzo = null;
		String nomeStanza = null; 
		String definizioneAttrezzo = null;

		try (Scanner scanner = new Scanner(this.riga)){
			definizioneAttrezzo = scanner.next();
			while (scanner.hasNext() && !definizioneAttrezzo.equals(USCITE)) {
				int peso;
				nomeAttrezzo = scanner.next();
				if (nomeAttrezzo == null)
					throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
				pesoAttrezzo = scanner.next();
				try {
					peso = Integer.parseInt(pesoAttrezzo);
				}
				catch (NumberFormatException e) {
					throw new FormatoFileNonValidoException("Peso attrezzo "+nomeAttrezzo+" non valido [" + this.numeroLinea + "].");
				}
				nomeStanza = scanner.next();
				nomeStanza = nomeStanza.replace(",", "");
				if (!stanzaValida(nomeStanza))
					throw new FormatoFileNonValidoException("Definizione attrezzo "+ nomeAttrezzo+" errata [" + this.numeroLinea + "]" +": stanza " +nomeStanza+" inesistente");

				this.builder.addAttrezzo(nomeAttrezzo,peso,nomeStanza);
			}
			this.riga= this.leggiRiga(reader);
		}

	}

	/**
	 * Verifica se il nome di una stanza introdotta sia valido.
	 */
	private boolean stanzaValida(String nomeStanza) {
		return this.builder.isValida(nomeStanza);
	}

	/**
	 * Aggiunge i collegamenti tra le stanze.
	 * @param nomeUscita Direzione che connette le due stanze.
	 * @param nomeStanzaPartenza Stanza di partenza.
	 * @param nomeStanzaDestinazione Stanza di arrivo.
	 */
	private void impostaUscita(String nomeUscita, String nomeStanzaPartenza, String nomeStanzaDestinazione) {
		this.builder.addAdiacenza(nomeStanzaPartenza, nomeStanzaDestinazione, Direzione.valueOf(nomeUscita.toUpperCase()));
	}

	private void leggiUscite() throws FormatoFileNonValidoException {
		String nomeStanzaPartenza = null;
		String nomeUscita = null;
		String nomeStanzaDestinazione = null;
		String datiUscita = null;

		try(Scanner scannerDiLinea = new Scanner(this.riga)){	
			datiUscita=scannerDiLinea.next();
			while (scannerDiLinea.hasNext() && !datiUscita.equals(FINE) ) {
				nomeStanzaPartenza = scannerDiLinea.next();
				nomeUscita = scannerDiLinea.next();
				nomeStanzaDestinazione = scannerDiLinea.next();
				nomeStanzaDestinazione = nomeStanzaDestinazione.replace(",", "");
				if (!stanzaValida(nomeStanzaPartenza))
					throw new FormatoFileNonValidoException("Definizione errata uscita [" + this.numeroLinea + "] " + nomeUscita);
				if (!stanzaValida(nomeStanzaDestinazione))
					throw new FormatoFileNonValidoException("Definizione errata uscita [" + this.numeroLinea + "] " + nomeUscita);
				impostaUscita(nomeUscita, nomeStanzaPartenza, nomeStanzaDestinazione);

			}
			//this.riga= this.leggiRiga(reader);
		}
	}

	/**
	 * Ritorna il labirinto creato.
	 * @return Labirinto generato.
	 */
	public Labirinto getLabirinto() {
		return this.builder.getLabirinto();
	}
}