package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoVai;

/**
 * Test sulla classe stanza bloccata
 * @author Leandro Placidi
 * @version 1.0
 */
public class StanzaBloccataTest {

	private Scanner scanner = new Scanner(System.in);
	private ComandoVai c;
	private Partita partita;
	private Labirinto labirinto;
	private IO console;
	private Stanza atrio;
	private Stanza stanzaBloccata = new StanzaBloccata("StanzaBloccata","nord","chiave");
	private Attrezzo chiave = new Attrezzo("chiave",0);

	@Before
	public void setUp(){
		this.partita = new Partita();
		this.console = new IOConsole(scanner);

		this.c = new ComandoVai();
		this.c.setIO(console);
		this.c.setParametro("nord");

		this.labirinto = new Labirinto();
		this.atrio = new Stanza("Atrio");
		this.stanzaBloccata = new StanzaBloccata("StanzaBloccata","nord","chiave");
		this.labirinto.setStanzaCorrente(stanzaBloccata);
		this.stanzaBloccata.setStanzaAdiacente("nord", atrio);
		this.atrio.setStanzaAdiacente("sud", stanzaBloccata);
		this.partita.setLabirinto(labirinto);
	}

	@Test
	public void testStanzaBloccata() {
		assertEquals(this.labirinto.getStanzaCorrente(),stanzaBloccata);
		c.esegui(partita);
		assertEquals(this.labirinto.getStanzaCorrente(),stanzaBloccata);
	}

	@Test
	public void testStanzaSbloccata() {
		assertEquals(this.labirinto.getStanzaCorrente(),stanzaBloccata);
		stanzaBloccata.addAttrezzo(chiave);
		c.esegui(partita);
		assertEquals(this.labirinto.getStanzaCorrente(),atrio);
	}

}
