package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.fixture.Fixture;

public class ComandoGuardaTest {

	private Scanner scanner = new Scanner (System.in);
	Partita partita;
	Labirinto labirinto;
	Stanza stanzaSud;
	Stanza stanzaNord;
	ComandoGuarda comando;
	IO console;
	Attrezzo attrezzo;

	@Before
	public void setUp() {
		comando = new ComandoGuarda();
		console = new IOConsole(scanner);
		comando.setIO(console);

		partita = new Partita();
		labirinto = new Labirinto();
		partita.setLabirinto(labirinto);

		stanzaSud = new Stanza("Attuale");
		stanzaNord = new Stanza("Secondaria");
		stanzaSud.setStanzaAdiacente("nord", stanzaNord);
		stanzaNord.setStanzaAdiacente("sud", stanzaSud);

		labirinto.setStanzaCorrente(stanzaSud);

		attrezzo = new Attrezzo("attrezzo",1);

	}

	/*
	 * Essendo che il comando stampa direttamente una stringa, utilizzo un assertEquals
	 * sul comando che viene effettivamente eseguito dal metodo.
	 */

	@Test
	public void testEseguiConAttrezzo() {
		stanzaSud.addAttrezzo(attrezzo);
		assertContains("attrezzo",partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}

	@Test
	public void testEseguiSenzaAttrezzo() {
		assertContains("nord",partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}

	@Test
	public void testEseguiStanzaDiversa() {
		labirinto.setStanzaCorrente(stanzaNord);
		stanzaNord.addAttrezzo(attrezzo);
		stanzaNord.addAttrezzo(attrezzo);
		assertContains("attrezzo ",partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}

	@Test
	public void testPartitaConComandoGuarda() {
		List<String> comandiDaEseguireList = Arrays.asList("vai ovest","guarda ovest","vai est","prendi chiave","vai ovest","posa chiave","guarda ovest","vai est","vai est","fine");
		IOSimulator io = Fixture.creaSimulazionePartitaEGioca(comandiDaEseguireList);
		assertTrue(io.hasNextMessaggio());

		//Inizio Partita
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());

		//Vai ovest
		assertTrue(io.hasNextMessaggio());
		assertContains("Laboratorio", io.nextMessaggio());

		//Guarda Ovest
		assertTrue(io.hasNextMessaggio());
		assertContains("Stanza chiusa", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("ha bisogno di chiave", io.nextMessaggio());

		//Vai est
		assertTrue(io.hasNextMessaggio());
		assertContains("Atrio", io.nextMessaggio());

		//Prendi chiave
		assertTrue(io.hasNextMessaggio());
		assertContains("recuperato", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Atrio", io.nextMessaggio());

		//Vai Ovest
		assertTrue(io.hasNextMessaggio());
		assertContains("Laboratorio", io.nextMessaggio());

		//Posa Chiave
		assertTrue(io.hasNextMessaggio());
		assertContains("posato", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Laboratorio", io.nextMessaggio());

		//Guarda Ovest
		assertTrue(io.hasNextMessaggio());
		assertContains("Aula N11", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Laboratorio", io.nextMessaggio());

		//Vai Est
		assertContains("Atrio", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());

		//Vai Est
		assertContains("Qui c'è buio pesto", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());

		//Fine
		assertTrue(io.hasNextMessaggio());
		assertContains(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	public void assertContains(String expected, String interaRiga) {
		assertTrue(interaRiga.contains(expected));
	}

}
