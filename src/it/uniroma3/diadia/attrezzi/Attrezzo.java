package it.uniroma3.diadia.attrezzi;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 * @author  Leandro Placidi
 * @see Stanza
 */
public class Attrezzo implements Comparable< Attrezzo> {

	private String nome;
	private int peso;

	/**
	 * Costruttore di attrezzo.
	 * @param nome il nome che identifica l'attrezzo.
	 * @param peso il peso dell'attrezzo.
	 */

	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo.
	 * @return nome dell'attrezzo.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo.
	 * @return peso dell'attrezzo.
	 */
	public int getPeso() {
		return this.peso;
	}

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo.
	 * @return la rappresentazione stringa.
	 */
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}

	@Override
	public boolean equals (Object o) {
		Attrezzo a = (Attrezzo) o;
		return (this.nome.equals(a.getNome()));
	}

	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}

	@Override
	public int compareTo( Attrezzo that) {
		return this.nome.compareTo(that.getNome());
	}
}