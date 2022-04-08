package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Test sulla classe stanza magica
 * @author Leandro Placidi
 * @version 1.0
 */
public class StanzaMagicaTest {
	private Stanza stanzaMagica;
	private Attrezzo attrezzo;

	@Before
	public void setUp(){
		this.stanzaMagica = new StanzaMagica("Stanza",3);
		this.attrezzo = new Attrezzo("attrezzo",1);
	}

	@Test
	public void testAddAttrezzoMagica() {
		for(int i=0; i<4;i++) {
			assertTrue(this.stanzaMagica.addAttrezzo(attrezzo));
		}
		assertTrue(this.stanzaMagica.hasAttrezzo("ozzertta"));
		assertEquals(new Attrezzo("ozzertta",2),stanzaMagica.getAttrezzo("ozzertta"));
		assertEquals((new Attrezzo("ozzertta",2)),(stanzaMagica.getAttrezzo("ozzertta")));
	}

	public void assertContains(String expected, String interaRiga) {
		assertTrue(interaRiga.contains(expected));
	}
}
