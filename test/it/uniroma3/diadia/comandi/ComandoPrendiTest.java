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

public class ComandoPrendiTest {

	private Scanner scanner = new Scanner (System.in);
	Partita partita;
	Labirinto labirinto;
	Stanza stanza;
	ComandoPrendi comando;
	IO console;
	Attrezzo attrezzo;

	@Before
	public void setUp() {
		comando = new ComandoPrendi();
		console = new IOConsole(scanner);
		comando.setIO(console);

		partita = new Partita();
		labirinto = new Labirinto();
		partita.setLabirinto(labirinto);

		stanza = new Stanza("Attuale");
		attrezzo = new Attrezzo("attrezzo",1);

		labirinto.setStanzaCorrente(stanza);
		stanza.addAttrezzo(attrezzo);
	}
	@Test
	public void testPrendiAttrezzoEsistente() {
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
		comando.setParametro("attrezzo");
		comando.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
	}

	@Test
	public void testPrendiAttrezzoNonPresente() {
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("inesistente"));
		comando.setParametro("inesistente");
		comando.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("inesistente"));
	}

	@Test
	public void testPrendiAttrezzoNull() {
		comando.setParametro(null);
		comando.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(null));
	}

	@Test
	public void testPartitaConComandoPrendi() {
		List<String> comandiDaEseguireList = Arrays.asList("prendi osso", "fine");
		IOSimulator io = Fixture.creaSimulazionePartitaEGioca(comandiDaEseguireList);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Hai recuperato osso", io.nextMessaggio());
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
