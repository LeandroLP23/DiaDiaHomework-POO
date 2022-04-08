package it.uniroma3.diadia;
import java.util.Scanner;

/**
 * Classe sfruttata per ottimizzare messaggi I/O.
 * @author Leandro Placidi
 */
public class IOConsole implements IO{

	private Scanner scannerDiLinee;

	/**
	 * Costruttore dell'IOConsole.
	 * @param scanner Scanner da utilizzare.
	 */
	public IOConsole(Scanner scanner) {
		this.scannerDiLinee = scanner;
	}
	/**
	 * Stampa il messaggio a video sulla console.
	 * @param msg Messaggio da mostrare a video.
	 */
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	/**
	 * Restituisce la riga letta in input.
	 * @return riga letta in INPUT.
	 */
	public String leggiRiga() {
		String riga = this.scannerDiLinee.nextLine();
		return riga;
	}

}