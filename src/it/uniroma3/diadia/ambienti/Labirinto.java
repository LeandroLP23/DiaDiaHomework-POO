package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.ambienti.npc.Cane;
import it.uniroma3.diadia.ambienti.npc.Mago;
import it.uniroma3.diadia.ambienti.npc.Strega;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che gestisce la creazione del labirinto nella sua completezza.
 * Si occupa della creazione degli oggetti, delle stanze e di relazionarli tra di loro.
 * 
 * @author Leandro Placidi
 */
public class Labirinto {

	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;

	/**
	 * Creazione e componimento del labirinto includendo le varie stanze adiacenti
	 * ed anche gli oggetti presenti in esso.
	 * @param nomeFile Il livello che si vuole creare. 
	 */

	public Labirinto(String nomeFile) {

		Class<?> classe = this.getClass();
		ClassLoader loader = classe.getClassLoader();

		CaricatoreLabirinto c;
		try {
			c = new CaricatoreLabirinto(loader.getResource("labirinto1.txt").getPath());			
			c.carica();
			this.setLabirinto(c.getLabirinto());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	//		public Labirinto(String nomeFile,int n) {
	//	
	//			CaricatoreLabirinto c =
	//					new CaricatoreLabirinto("C:\\Users\\Leandro\\OneDrive\\POO\\diadiaHW\\resources\\labirinto1.txt");
	//	
	//			c.carica();
	//			this.setLabirinto(c.getLabirinto());
	//		}


	/**
	 * Costruttore vuoto per permettere la crezione del labirinto attraverso vari metodi.
	 */
	public Labirinto() {}

	/**
	 * Imposta come labirinto il labirinto passato per parametro.
	 * @param labirinto Labirinto.
	 */
	private void setLabirinto(Labirinto labirinto) {
		this.setStanzaCorrente(labirinto.getStanzaCorrente());
		this.setStanzaVincente(labirinto.getStanzaVincente());
	}

	/**
	 * Permette la creazione di un labirinto sfruttando la classe
	 * nidificata {@link LabirintoBuilder}.
	 * @return LabirintoBuilder.
	 */
	public static LabirintoBuilder newBuilder(){
		return new LabirintoBuilder();
	}

	/**
	 * Imposta la stanza vincente.
	 * @param stanzaVincente Stanza vincente.
	 */
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	/**
	 * Restituisce la stanza in cui si vince.
	 * @return La stanza in cui si vince.
	 */
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	/**
	 * Imposta la stanza corrente.
	 * @param stanzaCorrente Stanza attuale del giocatore.
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	/**
	 * Restituisce la stanza attuale.
	 * @return Stanza attuale.
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	/**
	 * Classe nidificata che permette la crazione di un labirinto attraverso 
	 * chaining method.
	 */
	public static class LabirintoBuilder {

		private Map<String,Stanza> nome2stanze;
		private Stanza ultimaAggiunta;
		private Labirinto labirinto;

		/**
		 * Costruttore di LabirintoBuilder.
		 */
		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.ultimaAggiunta = null;
			nome2stanze = new HashMap<String,Stanza>();
		}

		/**
		 * Aggiungere la stanza iniziale al labirinto
		 * @param nomeStanza identificativo della stanza.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			Stanza stanzaIniziale = new Stanza (nomeStanza);
			this.labirinto.setStanzaCorrente(stanzaIniziale);
			this.aggiungiAMappaEAggiornaUltima(stanzaIniziale);
			return this;
		}

		/**
		 * Aggiungere la stanza vincente al labirinto
		 * @param nomeStanza identificativo della stanza.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addStanzaVincente(String nomeStanza) {
			Stanza stanzaVincente = new Stanza (nomeStanza);
			this.labirinto.setStanzaVincente(stanzaVincente);
			this.aggiungiAMappaEAggiornaUltima(stanzaVincente);
			return this;
		}
		
		/**
		 * Aggiungere una stanza generica al labirinto
		 * @param nomeStanza identificativo della stanza.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza stanza = new Stanza (nomeStanza);
			this.aggiungiAMappaEAggiornaUltima(stanza);
			return this;
		}

		/**
		 * Aggiungere la stanza magica al labirinto
		 * @param nomeStanza identificativo della stanza.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addStanzaMagica(String nomeStanza) {
			Stanza stanza = new StanzaMagica (nomeStanza);
			this.aggiungiAMappaEAggiornaUltima(stanza);;
			return this;
		}

		/**
		 * Aggiungere la stanza Buia al labirinto
		 * @param nomeStanza identificativo della stanza.
		 * @param attrezzoChiave permette alla stanzaBuia di tornare generica.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addStanzaBuia(String nomeStanza,String attrezzoChiave) {
			Stanza stanza = new StanzaBuia (nomeStanza, attrezzoChiave );
			this.aggiungiAMappaEAggiornaUltima(stanza);
			return this;
		}

		/**
		 * Aggiungere la stanza Bloccata al labirinto
		 * @param nomeStanza identificativo della stanza.
		 * @param attrezzoChiave permette alla stanzaBloccata di tornare generica.
		 * @param direzione direzione bloccata.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addStanzaBloccata(String nomeStanza, String attrezzoChiave, Direzione direzione) {
			Stanza stanza = new StanzaBloccata (nomeStanza, direzione.toString().toLowerCase(), attrezzoChiave);
			this.aggiungiAMappaEAggiornaUltima(stanza);
			return this;
		}

		/**
		 * Connette le stanze passate per parametro nella direzione impostata.
		 * @param stanza1 stanza iniziale.
		 * @param stanza2 stanza finale.
		 * @param direzione direzione che unisce le due stanze.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addAdiacenza(String stanza1, String stanza2, Direzione direzione) {
			Stanza s1 = this.nome2stanze.get(stanza1);
			Stanza s2 = this.nome2stanze.get(stanza2);

			if(s1!=null && s2!=null) { 
				s1.setStanzaAdiacente(direzione.toString().toLowerCase(), s2);
				s2.setStanzaAdiacente(Direzione.opposta(direzione).toString().toLowerCase(), s1);
			}
			return this;
		}

		/**
		 * Aggiunge un attrezzo all'ultima stanza aggiunta.
		 * @param attrezzo String nome attrezzo.
		 * @param peso Int peso attrezzo.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addAttrezzo(String attrezzo, int peso) {
			this.ultimaAggiunta.addAttrezzoSetUp(new Attrezzo(attrezzo,peso));
			return this;
		}

		/**
		 * Aggiunge un attrezzo nella stanza passata come parametro.
		 * @param attrezzo String nome attrezzo.
		 * @param peso Int peso attrezzo.
		 * @param nomeStanza nome della stanza in cui aggiungere attrezzo.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addAttrezzo(String attrezzo, int peso, String nomeStanza) {
			this.nome2stanze.get(nomeStanza).addAttrezzoSetUp(new Attrezzo(attrezzo,peso));
			return this;
		}

		/**
		 * Aggiunge un mago all'ultima stanza aggiunta.
		 * @param nome Mago.
		 * @param presentazione Mago.
		 * @param attrezzo nome Attrezzo.
		 * @param peso Attrezzo.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addMago( String nome, String presentazione, String attrezzo, int peso) {
			this.ultimaAggiunta.setPersonaggio(new Mago(nome,presentazione, new Attrezzo(attrezzo,peso)));
			return this;
		}

		/**
		 * Aggiunge un mago nella stanza passata come parametro.
		 * @param nome Mago.
		 * @param presentazione Mago.
		 * @param attrezzo nome Attrezzo.
		 * @param peso Attrezzo.
		 * @param nomeStanza nome della stanza in cui aggiungere attrezzo.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addMago( String nome, String presentazione, String attrezzo, int peso, String nomeStanza) {
			this.nome2stanze.get(nomeStanza).setPersonaggio(new Mago(nome,presentazione, new Attrezzo(attrezzo,peso)));
			return this;
		}

		/**
		 * Aggiunge una strega all'ultima stanza aggiunta.
		 * @param nome Strega.
		 * @param presentazione Strega.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addStrega( String nome, String presentazione) {
			this.ultimaAggiunta.setPersonaggio(new Strega(nome,presentazione));
			return this;
		}

		/**
		 * Aggiunge una strega nella stanza passata come parametro.
		 * @param nome Strega.
		 * @param presentazione Strega.
		 * @param nomeStanza nome della stanza in cui aggiungere attrezzo.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addStrega( String nome, String presentazione, String nomeStanza) {
			this.nome2stanze.get(nomeStanza).setPersonaggio(new Strega(nome,presentazione));
			return this;
		}

		/**
		 * Aggiunge un cane all'ultima stanza aggiunta.
		 * @param nome Cane.
		 * @param presentazione Cane.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addCane( String nome, String presentazione) {
			this.ultimaAggiunta.setPersonaggio(new Cane(nome,presentazione));
			return this;
		}

		/**
		 * Aggiunge un cane nella stanza passata come parametro.
		 * @param nome Cane.
		 * @param presentazione Cane.
		 * @param nomeStanza nome della stanza in cui aggiungere attrezzo.
		 * @return {@link LabirintoBuilder}
		 */
		public LabirintoBuilder addCane( String nome, String presentazione, String nomeStanza) {
			this.nome2stanze.get(nomeStanza).setPersonaggio(new Cane(nome,presentazione));
			return this;
		}

		/**
		 * Aggiunge alla mappa del labirinto la stanza passata come parametro
		 * e aggiorna l'ultimaAggiunta.
		 * @param stanza Da inserire nel labirinto.
		 */
		private void aggiungiAMappaEAggiornaUltima(Stanza stanza) {
			this.ultimaAggiunta = stanza;
			this.nome2stanze.put(stanza.getNome(), stanza);
		}

		/**
		 * Richiama il labirinto.
		 * @return Labirinto.
		 */
		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		/**
		 * Verifica se il nome della stanza passato per parametro sia
		 * presente nella mappa del labirinto.
		 * @param nomeStanza Da verificare
		 * @return TRUE se presente, FALSE altrimenti.
		 */
		public boolean isValida(String nomeStanza) {
			return this.nome2stanze.containsKey(nomeStanza);

		}
	}
}