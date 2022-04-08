package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Test sulla classe stanza
 * @author Leandro Placidi
 * @version 1.0
 */
public class StanzaTest {

	private Stanza aulaVuota;
	private Stanza aulaN10;
	private Attrezzo lanterna;
	private Attrezzo osso;
	private Attrezzo fazzoletto;

	@Before
	public void setUp(){
		//creazione stanze
		this.aulaVuota = new Stanza("test");
		this.aulaN10=new Stanza("aulaN10");

		//imposto direzioni
		this.aulaN10.setStanzaAdiacente("est", aulaVuota);
		this.aulaN10.setStanzaAdiacente("ovest", null);

		//creazione attrezzi
		this.lanterna= new Attrezzo("lanterna",1);
		this.osso = new Attrezzo("osso",1);

		//attrezzi nelle stanze
		aulaN10.addAttrezzo(lanterna);
		aulaN10.addAttrezzo(null);

	}

	@Test
	public void testGetStanzaAdiacenteEsistente(){
		assertEquals(aulaVuota,aulaN10.getStanzaAdiacente("est"));
	}

	@Test
	public void testGetStanzaAdiacenteInesistente(){
		assertNull(aulaN10.getStanzaAdiacente("ovest"));
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneInesistente(){
		assertNull(aulaN10.getStanzaAdiacente("sinistra"));
	}

	@Test 
	public void testAddAttrezzoStandard() {	
		assertFalse(aulaN10.hasAttrezzo("osso"));
		aulaN10.addAttrezzo(osso);
		assertTrue(aulaN10.addAttrezzo(osso));
	}
	@Test 
	public void testAddAttrezzoVuoto() {
		assertFalse(aulaN10.addAttrezzo(null));
	}

	@Test
	public void testHasAttrezzoPresente() {
		assertFalse(aulaN10.hasAttrezzo("osso"));
		aulaN10.addAttrezzo(osso);
		assertTrue(aulaN10.hasAttrezzo("osso"));
	}

	@Test
	public void testHasAttrezzoNonPresente() {
		assertFalse(aulaN10.hasAttrezzo("chiave"));
	}

	@Test
	public void testHasAttrezzoNull() {
		assertFalse(aulaN10.hasAttrezzo(null));
	}

	@Test
	public void testGetAttrezzoPresente() {
		assertFalse(aulaN10.hasAttrezzo("osso"));
		aulaN10.addAttrezzo(osso);
		assertEquals(osso,aulaN10.getAttrezzo("osso"));
		assertTrue(aulaN10.hasAttrezzo("osso"));
	}

	@Test
	public void testGetAttrezzoNonPresente() {
		assertNull(aulaN10.getAttrezzo("chiave"));
	}

	@Test
	public void testGetAttrezzoNull() {
		assertNull(aulaN10.getAttrezzo(null));
	}

	@Test
	public void testRemoveAttrezzoPresente(){
		assertFalse(aulaN10.hasAttrezzo("osso"));
		aulaN10.addAttrezzo(osso);
		assertTrue(aulaN10.removeAttrezzo(osso));
	}

	@Test
	public void testRemoveAttrezzoInsesistente(){
		assertFalse(aulaN10.removeAttrezzo(fazzoletto));
	}

	@Test
	public void testRemoveAttrezzoStanzaVuota(){
		assertFalse(aulaN10.hasAttrezzo("osso"));
		assertFalse(aulaN10.removeAttrezzo(osso));
	}

	@Test
	public void testRemoveAttrezzoNull(){
		assertFalse(aulaVuota.removeAttrezzo(null));
	}

}
