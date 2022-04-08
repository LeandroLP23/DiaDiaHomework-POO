package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoInventarioTest {

	private Scanner scanner = new Scanner (System.in);
	Partita partita;
	ComandoInventario comando;
	IO console;
	Attrezzo attrezzo;

	@Before
	public void setUp() {
		comando = new ComandoInventario();
		console = new IOConsole(scanner);
		attrezzo = new Attrezzo("attrezzo",1);
		partita = new Partita();

		comando.setIO(console);
	}

	@Test
	public void testInventarioConAttrezzo() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);

		/*
		 * Utilizzo direttamente ciò che deve eseguire l'esegui partita, in quanto effettua semplicemente la stampa
		 * dell'inventario, mediante il metodo toString di quest'ultima.
		 */
		assertContains("attrezzo", partita.getGiocatore().getBorsa().toString());
	}

	@Test
	public void testInventarioSenzaAttrezzi() {
		assertContains("Borsa vuota", partita.getGiocatore().getBorsa().toString());
	}

	@Test
	public void testInventarioConAttrezzoNull() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(null);
		assertContains("Borsa vuota", partita.getGiocatore().getBorsa().toString());
	}

	public void assertContains(String expected, String interaRiga) {
		assertTrue(interaRiga.contains(expected));
	}
}
