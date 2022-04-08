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

public class ComandoPosaTest {

	private Scanner scanner = new Scanner (System.in);
	Partita partita;
	Labirinto labirinto;
	Stanza stanza;
	ComandoPosa comando;
	IO console;
	Attrezzo attrezzo;

	@Before
	public void setUp() {
		comando = new ComandoPosa();
		console = new IOConsole(scanner);
		comando.setIO(console);

		partita = new Partita();
		labirinto = new Labirinto();
		partita.setLabirinto(labirinto);

		stanza = new Stanza("Attuale");
		attrezzo = new Attrezzo("attrezzo",1);

		labirinto.setStanzaCorrente(stanza);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
	}

	@Test
	public void testPosaAttrezzoEsistente() {
		assertFalse(stanza.hasAttrezzo("attrezzo"));
		comando.setParametro("attrezzo");
		comando.esegui(partita);
		assertTrue(stanza.hasAttrezzo("attrezzo"));
	}

	@Test
	public void testPosaAttrezzoNonPresente() {
		assertFalse(stanza.hasAttrezzo("inesistente"));
		comando.setParametro("inesistente");
		comando.esegui(partita);
		assertFalse(stanza.hasAttrezzo("inesistente"));
	}

	@Test
	public void testPosaAttrezzoNull() {
		comando.setParametro(null);
		comando.esegui(partita);
		assertFalse(stanza.hasAttrezzo(null));
		assertFalse(stanza.hasAttrezzo("attrezzo"));
	}

	@Test
	public void testPartitaConComandoPosa() {
		List<String> comandiDaEseguireList = Arrays.asList("prendi osso","posa osso", "fine");
		IOSimulator io = Fixture.creaSimulazionePartitaEGioca(comandiDaEseguireList);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		io.nextMessaggio();
		assertTrue(io.hasNextMessaggio());
		io.nextMessaggio();
		assertTrue(io.hasNextMessaggio());
		assertContains("Hai posato osso", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		io.nextMessaggio();
		assertTrue(io.hasNextMessaggio());
		assertContains(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	public void assertContains(String expected, String interaRiga) {
		assertTrue(interaRiga.contains(expected));
	}
}
