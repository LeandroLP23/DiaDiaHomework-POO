package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Test sulla classe stanza buia
 * @author Leandro Placidi
 * @version 1.0
 */
public class StanzaBuiaTest {
	private Stanza stanzaBuia;
	private Attrezzo lanterna;

	@Before
	public void setUp(){
		this.stanzaBuia = new StanzaBuia("StanzaBuia", "lanterna");
		this.lanterna = new Attrezzo("lanterna",1);
	}

	@Test
	public void testGetDescrizione() {
		assertEquals("Qui c'è buio pesto", stanzaBuia.getDescrizione());
		stanzaBuia.addAttrezzo(lanterna);
		assertEquals("StanzaBuia\nUscite: []\nAttrezzi nella stanza: [lanterna (1kg)]", stanzaBuia.getDescrizione());
	}

}
