package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {

	private Borsa borsaVuota;
	private Borsa borsa;
	private Attrezzo moneta;
	private Attrezzo chiave;
	private Attrezzo peso11;
	private Attrezzo attrezzo;

	@Before
	public void setUp() {

		//Creazione borsa
		this.borsa = new Borsa();
		this.borsaVuota = new Borsa();

		//Creazione attrezzo
		this.attrezzo = new Attrezzo("attrezzo",2);
		this.peso11= new Attrezzo("peso11",11);
		this.chiave= new Attrezzo("chiave", 0);
		this.moneta= new Attrezzo("moneta", 0);
	}

	@Test
	public void testAddAttrezzoStandard() {
		assertFalse(borsa.hasAttrezzo("chiave"));
		borsa.addAttrezzo(chiave);
		assertTrue(borsa.addAttrezzo(chiave));
	}

	@Test
	public void testAddAttrezzoTroppoPesante() {
		assertFalse(borsa.addAttrezzo(peso11));
	}

	@Test
	public void testAddAttrezzoNull() {
		assertFalse(borsa.addAttrezzo(null));
	}

	@Test 
	public void testGetPesoBorsaVuota() {
		assertEquals(0,borsaVuota.getPeso());
	} 

	@Test
	public void testGetAttrezzoPresente() {
		assertFalse(borsa.hasAttrezzo("chiave"));
		borsa.addAttrezzo(chiave);
		assertEquals(chiave,borsa.getAttrezzo("chiave"));
	}

	@Test
	public void testGetAttrezzoNonPresente() {
		assertNull(borsa.getAttrezzo("osso"));
	}

	@Test
	public void testGetAttrezzoNull() {
		assertNull(borsa.getAttrezzo(null));
	}

	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		assertNull(borsaVuota.removeAttrezzo("chiave"));
	}

	@Test
	public void testRemoveAttrezzoPresente() {
		assertFalse(borsa.hasAttrezzo("chiave"));
		borsa.addAttrezzo(chiave);
		assertEquals(chiave,borsa.removeAttrezzo("chiave"));
	}

	@Test
	public void testRemoveAttrezzoNonPresente() {
		assertNull(borsaVuota.removeAttrezzo("chiavi"));
	}

	@Test
	public void testRemoveAttrezzoNull() {
		assertNull(borsaVuota.removeAttrezzo(null));
	}

	@Test 
	public void testAttrezziPesoUgualeNomeDiverso() {
		borsa.addAttrezzo(chiave);
		borsa.addAttrezzo(moneta);
		assertEquals("[chiave (0kg), moneta (0kg)]",this.borsa.getSortedSetOrdinatoPerPeso().toString());
	}

	@Test 
	public void testGetContenutoOrdinatoPerPeso() {
		riempiBorsa();
		List<Attrezzo>tmp = this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals("[chiave (0kg), moneta (0kg), attrezzo (2kg)]", tmp.toString());
	}

	@Test 
	public void testGetContenutoOrdinatoPerNome() {
		riempiBorsa();
		Set<Attrezzo>tmp = this.borsa.getContenutoOrdinatoPerNome();
		assertEquals("[attrezzo (2kg), chiave (0kg), moneta (0kg)]", tmp.toString());
	}

	@Test 
	public void testGetContenutoRaggruppatoPerPeso() {
		riempiBorsa();
		Map<Integer, Set<Attrezzo>> tmp = this.borsa.getContenutoRaggruppatoPerPeso();
		assertEquals("{0=[chiave (0kg), moneta (0kg)], 2=[attrezzo (2kg)]}", tmp.toString());
	}

	//Factory Method
	private void riempiBorsa() {
		this.borsa.addAttrezzo(moneta);
		this.borsa.addAttrezzo(chiave);
		this.borsa.addAttrezzo(attrezzo);
	}
}
