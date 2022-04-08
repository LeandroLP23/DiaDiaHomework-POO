package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 *Classe che gestisce i comandi forniti in INPUT dall'utente.
 * @author  Leandro Placidi
 */
public abstract class AbstractComando {

	private String parametro;
	private IO console;

	/**
	 * Esecuzione del comando.
	 * @param partita La partita attuale.
	 */
	public abstract void esegui(Partita partita);

	/**
	 * Set parametro del comando.
	 * @param parametro Il parametro del comando.
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	/**
	 * Set IO del comando.
	 *  @param console La console dalla quale stampare i messaggi.
	 */
	public void setIO(IO console) {
		this.console = console;
	}

	/**
	 * Get IO del comando.
	 *  @return La console dalla quale stampare i messaggi
	 */
	public IO getIO() {
		return this.console;
	}

	/**
	 * Get parametro.
	 * @return Il paramentro del comando.
	 */
	public String getParametro() {
		return this.parametro;

	}

	/**
	 * Get nome comando.
	 * @return Nome del comando.
	 */
	public String getNome() {
		int indice = this.getClass().getCanonicalName().indexOf("Comando");
		StringBuilder s =  new StringBuilder();
		s.append(Character.toLowerCase(this.getClass().getCanonicalName().substring(indice + 7).charAt(0)));
		s.append(this.getClass().getCanonicalName().substring(indice + 8));
		return s.toString();
	}
}