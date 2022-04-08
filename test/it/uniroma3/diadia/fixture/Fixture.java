package it.uniroma3.diadia.fixture;

import java.util.List;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Fixture {


	static final public Direzione NORD = Direzione.NORD;
	static final public Direzione SUD = Direzione.SUD;
	static final public Direzione EST = Direzione.EST;
	static final public Direzione OVEST = Direzione.OVEST;

	public static IOSimulator creaSimulazionePartitaEGioca(List<String> righeDaLeggere, Labirinto labirinto) {
		IOSimulator io = new IOSimulator(righeDaLeggere);
		new DiaDia(labirinto,io).gioca(); 
		return io;
	}

	public static IOSimulator creaSimulazionePartitaEGioca(List<String> righeDaLeggere) {
		Labirinto l = Labirinto.newBuilder()

				//Atrio
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1)
				.addAttrezzo("chiave", 1)
				.addStrega("Strega","Il mio nome e' Carolina, la strega che cucina!")

				//Aula N11
				.addStanzaBuia("Aula N11","lanterna")

				//Aula N10
				.addStanzaBloccata("Laboratorio","chiave", OVEST)
				.addAttrezzo("lanterna", 2)
				.addMago("Mago","Ciao sono un mago", "pipa",1)

				//Laboratorio
				.addStanzaMagica("Aula N10")
				.addAttrezzo("spada", 3)
				.addAttrezzo("spadone", 5)
				.addCane("Cane","GRR BAU")

				.addStanzaVincente("Biblioteca")

				//SetUp Adiacenze
				.addAdiacenza("Atrio", "Biblioteca", NORD)
				.addAdiacenza("Atrio","Aula N11",EST)
				.addAdiacenza("Atrio","Aula N10",SUD)
				.addAdiacenza("Atrio","Laboratorio",OVEST)
				.addAdiacenza("Aula N11","Laboratorio",EST)

				.getLabirinto();

		return Fixture.creaSimulazionePartitaEGioca(righeDaLeggere,l);
	}

	public static Attrezzo creaAttrezzoEAggiungiAStanza(Stanza stanzaDaRiempire, String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		stanzaDaRiempire.addAttrezzo(attrezzo);
		return attrezzo;
	}

}
