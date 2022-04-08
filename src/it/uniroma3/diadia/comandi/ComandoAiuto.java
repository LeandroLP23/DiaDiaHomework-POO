package it.uniroma3.diadia.comandi;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 * @see AbstractComando
 * @author Leandro Placidi 
 */
public class ComandoAiuto extends AbstractComando {

	//	static final private String[] elencoComandi = {"vai","fine","posa","prendi","inventario","guarda","saluta","interagisci"};

	@Override
	public void esegui(Partita partita) {
		Set <String>elencoComandi = new HashSet<>();
		File folder = new File("src/it/uniroma3/diadia/comandi");
		File[] listOfFiles = folder.listFiles();
		int c = 0;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].getName().startsWith("Comando") && 
					listOfFiles[i].getName().contains(".java") &&
					!listOfFiles[i].getName().contains("NonValido")) {
				c = listOfFiles[i].getName().length();
				elencoComandi.add(listOfFiles[i].getName().substring(7,c-5).toLowerCase());
			}
		}
		this.getIO().mostraMessaggio(elencoComandi.toString());
	}

}
