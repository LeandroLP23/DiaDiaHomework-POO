package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

public class LabirintoTest {
	private Labirinto monolocale;
	private Labirinto bilocale;
	private Labirinto trilocale;

	public LabirintoTest() {
		monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto") 
				.addStanzaVincente("salotto") 
				.getLabirinto();
		bilocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto",10)
				.addAdiacenza("salotto", "camera", Direzione.NORD) 
				.getLabirinto();
		trilocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola",1) 
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "cucina", Direzione.NORD)
				.addAdiacenza("cucina", "camera", Direzione.EST)
				.getLabirinto();  
	}

	public Labirinto getMonolocale() {
		return monolocale;
	}

	public Labirinto getBilocale() {
		return bilocale;
	}

	public Labirinto getTrilocale() {
		return trilocale;
	}

}
