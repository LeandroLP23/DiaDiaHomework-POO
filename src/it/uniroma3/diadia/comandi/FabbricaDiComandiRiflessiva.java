package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

/**
 * Classe che permette lo smistamento del comando richiesto dall'utente. 
 * @see AbstractComando
 * @author Leandro Placidi
 */
public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi{

	@SuppressWarnings("deprecation")
	@Override
	public AbstractComando costruisciComando(String istruzione, IO console) {

		try (Scanner scannerDiParole = new Scanner(istruzione)) {
			String nomeComando = null; 
			String parametro = null; 
			AbstractComando comando = null;

			if (scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next();
			if (scannerDiParole.hasNext())
				parametro = scannerDiParole.next();

			StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
			try {			
				nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) );
				nomeClasse.append( nomeComando.substring(1) ) ;
				comando = (AbstractComando)Class.forName(nomeClasse.toString()).newInstance();

			} catch (Exception e) {
				comando = new ComandoNonValido();
			}
			comando.setParametro(parametro);
			comando.setIO(console);
			return comando;
		}
	}
}
