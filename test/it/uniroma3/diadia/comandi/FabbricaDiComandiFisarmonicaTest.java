package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

public class FabbricaDiComandiFisarmonicaTest {

	private Scanner scanner = new Scanner (System.in);
	IO console;
	String istruzione;
	AbstractComando comando;
	FabbricaDiComandi factory;

	@Before
	public void setUp() {
		this.console = new IOConsole(scanner);
		this.istruzione = new String();
		factory = new FabbricaDiComandiRiflessiva();
	}

	@Test
	public void testComandoNonValido() {
		istruzione = "ComandoInvalido";
		comando = factory.costruisciComando(istruzione, console);
		assertEquals("nonValido",this.comando.getNome());
		assertNull(this.comando.getParametro());

		istruzione = "Comando prova non valido";
		comando = factory.costruisciComando(istruzione, console);
		assertEquals("nonValido",this.comando.getNome());
	}

	@Test
	public void testComandoNull() {
		comando = factory.costruisciComando(istruzione, console);
		assertEquals("nonValido",this.comando.getNome());
		assertNull(this.comando.getParametro());
	}

	@Test
	public void testComandoPrendi() {
		istruzione = "prendi attrezzo";
		comando = factory.costruisciComando(istruzione, console);
		assertEquals("prendi",this.comando.getNome());
		assertEquals("attrezzo",this.comando.getParametro());
	}

	@Test
	public void testComandoPrendiNull() {
		istruzione = "prendi";
		comando = factory.costruisciComando(istruzione, console);
		assertEquals("prendi",this.comando.getNome());
		assertNull(this.comando.getParametro());
	}

	@Test
	public void testComandoPosa() {
		istruzione = "posa attrezzo";
		comando = factory.costruisciComando(istruzione, console);
		assertEquals("posa",this.comando.getNome());
		assertEquals("attrezzo",this.comando.getParametro());
	}

	@Test
	public void testComandoPosaNull() {
		istruzione = "posa";
		comando = factory.costruisciComando(istruzione, console);
		assertEquals("posa",this.comando.getNome());
		assertNull(this.comando.getParametro());
	}

	@Test
	public void testComandoAiuto() {
		istruzione = "aiuto";
		comando = factory.costruisciComando(istruzione, console);
		assertEquals("aiuto",this.comando.getNome());
		assertNull(this.comando.getParametro());
	}

	@Test
	public void testComandoFine() {
		istruzione = "fine";
		comando = factory.costruisciComando(istruzione, console);
		assertEquals("fine",this.comando.getNome());
		assertNull(this.comando.getParametro());
	}

	@Test
	public void testComandoGuarda() {
		istruzione = "guarda";
		comando = factory.costruisciComando(istruzione, console);
		assertEquals("guarda",this.comando.getNome());
		assertNull(this.comando.getParametro());
	}

	@Test
	public void testComandoInventario() {
		istruzione = "inventario";
		comando = factory.costruisciComando(istruzione, console);
		assertEquals("inventario",this.comando.getNome());
		assertNull(this.comando.getParametro());
	}
}
