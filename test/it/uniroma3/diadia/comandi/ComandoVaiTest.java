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
import it.uniroma3.diadia.LabirintoTest;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.fixture.Fixture;

public class ComandoVaiTest {

	private Scanner scanner = new Scanner (System.in);
	Partita partita;
	Labirinto labirinto;
	Stanza stanza1;
	Stanza stanza2;
	ComandoVai comando;
	IO console;
	LabirintoTest labirintoTest;

	@Before
	public void setUp() {
		comando = new ComandoVai();
		console = new IOConsole(scanner);
		comando.setIO(console);

		partita = new Partita();
		labirinto = new Labirinto();
		labirintoTest = new LabirintoTest();
		partita.setLabirinto(labirinto);

		stanza1 = new Stanza("Attuale");
		stanza2 = new Stanza("Vincente");
		stanza1.setStanzaAdiacente("nord", stanza2);
		stanza2.setStanzaAdiacente("sud", stanza1);

		labirinto.setStanzaCorrente(stanza1);

	}

	@Test
	public void testEseguiDirezioneEsistente() {
		assertEquals(stanza1,partita.getLabirinto().getStanzaCorrente());
		comando.setParametro("nord");
		comando.esegui(partita);
		assertEquals(stanza2,partita.getLabirinto().getStanzaCorrente());

	}

	@Test
	public void testEseguiDirezioneErrata() {
		assertEquals(stanza1,partita.getLabirinto().getStanzaCorrente());
		comando.setParametro("sud");
		comando.esegui(partita);
		assertEquals(stanza1,partita.getLabirinto().getStanzaCorrente());
	}

	@Test
	public void testEseguiDirezioneInesistente() {
		assertEquals(stanza1,partita.getLabirinto().getStanzaCorrente());
		comando.setParametro("direzioneProva");
		comando.esegui(partita);
		assertEquals(stanza1,partita.getLabirinto().getStanzaCorrente());
	}

	@Test
	public void testEseguiNull() {
		assertEquals(stanza1,partita.getLabirinto().getStanzaCorrente());
		comando.setParametro(null);
		comando.esegui(partita);
		assertEquals(stanza1,partita.getLabirinto().getStanzaCorrente());
	}

	@Test
	public void testPartitaConComandoVai() {
		List<String> comandiDaEseguireList = Arrays.asList("vai sud", "fine");
		IOSimulator io = Fixture.creaSimulazionePartitaEGioca(comandiDaEseguireList);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Aula N10", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testLabirintoMonolocale() {
		this.partita.setLabirinto(this.labirintoTest.getMonolocale());
		String[] comandiDaEseguire = {"vai sud","vai nord", "vai est", "vai ovest", "fine"};
		List<String> comandiDaEseguireList = Arrays.asList(comandiDaEseguire);
		IOSimulator io = Fixture.creaSimulazionePartitaEGioca(comandiDaEseguireList,this.partita.getLabirinto());
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		for (int i=0;i<4;i++) {
			assertTrue(io.hasNextMessaggio());
			assertContains("Direzione inesistente", io.nextMessaggio());
			assertTrue(io.hasNextMessaggio());
			assertContains("salotto", io.nextMessaggio());
		}

		assertTrue(io.hasNextMessaggio());
		assertContains(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testLabirintoBilocale() {
		this.partita.setLabirinto(this.labirintoTest.getBilocale());
		String[] comandiDaEseguire = {"vai sud", "vai ovest", "vai est","vai nord"};
		List<String> comandiDaEseguireList = Arrays.asList(comandiDaEseguire);
		IOSimulator io = Fixture.creaSimulazionePartitaEGioca(comandiDaEseguireList,this.partita.getLabirinto());

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		for (int i=0;i<3;i++) {
			assertTrue(io.hasNextMessaggio());
			assertContains("Direzione inesistente", io.nextMessaggio());
			assertTrue(io.hasNextMessaggio());
			assertContains("salotto", io.nextMessaggio());
		}

		assertTrue(io.hasNextMessaggio());
		assertContains("Hai vinto", io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testLabirintoTrilocale() {
		this.partita.setLabirinto(this.labirintoTest.getTrilocale());
		String[] comandiDaEseguire = {"vai sud", "vai ovest","vai nord", "vai est"};
		List<String> comandiDaEseguireList = Arrays.asList(comandiDaEseguire);
		IOSimulator io = Fixture.creaSimulazionePartitaEGioca(comandiDaEseguireList,this.partita.getLabirinto());

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		for (int i=0;i<2;i++) {
			assertTrue(io.hasNextMessaggio());
			assertContains("Direzione inesistente", io.nextMessaggio());
			assertTrue(io.hasNextMessaggio());
			assertContains("salotto", io.nextMessaggio());
		}

		assertTrue(io.hasNextMessaggio());
		assertContains("cucina", io.nextMessaggio());

		assertTrue(io.hasNextMessaggio());
		assertContains("Hai vinto", io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	public void assertContains(String expected, String interaRiga) {
		assertTrue(interaRiga.contains(expected));
	}

}
