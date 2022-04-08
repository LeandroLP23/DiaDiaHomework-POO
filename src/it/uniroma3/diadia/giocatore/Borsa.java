package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Configurazioni;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerPeso;

/**
 * Classe che modella la Borsa del giocatore, della quale possiamo inserire gli attrezzi
 * che troviamo durante l'avventura.
 * @author Leandro Placidi
 * @see Attrezzo
 */
public class Borsa {

	private final static int PESO_MAX_BORSA = Configurazioni.getPesoMax();
	private int pesoMax;
	private int peso;
	private Map<String, Attrezzo> attrezzi;
	private Comparator<Attrezzo> comparatore;

	/**
	 * Costruttore borsa senza paramentri, per cui viene utilizzato un 
	 * valore limite del peso massimo totale degli attrezzi nella borsa 
	 * ottenuto da file.
	 * */
	public Borsa() {
		this(PESO_MAX_BORSA);
	}

	/**
	 * Costruttore borsa, per cui viene impostato il valore limite 
	 * del peso massimo totale degli attrezzi nella borsa attraverso un parametro.
	 * @param pesoMax Int che indica il peso massimo trasportabile.
	 * */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.peso = 0;
		this.attrezzi = new HashMap<String, Attrezzo>();
	}

	/**
	 * Metodo che permette di aggiungere attrezzo nella borsa.
	 * @param attrezzo riferimento all'oggetto di tipo attrezzo.
	 * @return TRUE se l'attrezzo viene inserito nella borsa, FALSE se il peso massimo viene superato.
	 * */
	public boolean addAttrezzo(Attrezzo attrezzo) {

		if (attrezzo==null || (this.getPeso() + attrezzo.getPeso()) > this.getPesoMax())
			return false;

		this.attrezzi.put(attrezzo.getNome(),attrezzo);
		this.peso += attrezzo.getPeso();
		return true;
	}

	/**
	 * Restituisce peso massimo degli contenuti nella borsa.
	 * @return pesoMax il peso massimo degli attrezzi trasportabili dal giocatore nella borsa.
	 */
	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * Introdda una stinga, restituisce l'oggetto di tipo Attrezzo con nome uguale a stringa
	 * (confrontato con equals()).
	 * @param nomeAttrezzo nome attrezzo fornito dall'utente.
	 * @return l'attrezzo se presente nell'inventario, null altrimenti.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo!=null)
			return this.attrezzi.get(nomeAttrezzo);
		return null;
	}

	/**
	 * Restituisce il peso totale/attuale degli attezzi presenti nella borsa.
	 * @return Peso totale presente nella borsa.
	 */
	public int getPeso(){
		//		for(String chiave: this.attrezzi.keySet())
		//			peso += this.attrezzi.get(chiave).getPeso();
		//		for(Map.Entry<String, Attrezzo> entry: this.attrezzi.entrySet())
		//			peso +=entry.getValue().getPeso();
		return this.peso;
	}

	/**
	 * Restituisce l'array degli attrezzi contenuti nella borsa.
	 * @return L'array degli attrezzi.
	 */
	public Map<String,Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Verifica se la borsa è vuota.
	 * @return TRUE se la borsa è vuota, FALSE altrimenti.
	 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	/**
	 * Verifica se l'attrezzo è presente nella borsa.
	 * @param nomeAttrezzo Stringa fornita dall'utente.
	 * @return TRUE se l'attrezzo è presente, FALSE altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Rimuove l'attrezzo della borsa se presente.
	 * @param nomeAttrezzo Stringa del nome dell'attrezzo.
	 * @return Attrezzo se è stato rimosso dalla borsa.
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		if(this.getAttrezzo(nomeAttrezzo) == null) return null;

		this.peso -= this.getAttrezzo(nomeAttrezzo).getPeso(); 
		return this.attrezzi.remove(nomeAttrezzo);
	}

	/**
	 * Metodo che permette di ordinare il contenuto della borsa per peso.
	 * @return tmp La lista degli attrezzi nella borsa ordinati per peso e 
	 * quindi, a parità di peso, per nome.
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		this.comparatore = new ComparatorePerPeso();
		List<Attrezzo> tmp = new ArrayList <Attrezzo>();
		tmp.addAll(this.attrezzi.values());
		Collections.sort(tmp,this.comparatore);
		return tmp;
	}

	/**
	 * Metodo che permette di ordinare il contenuto della borsa per nome.
	 * @return tmp L'insieme degli attrezzi nella borsa ordinati per nome.
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		//		SortedSet<Attrezzo> tmp = new TreeSet <Attrezzo>();
		//		tmp.addAll(this.attrezzi.values());
		//		return tmp;
		return new TreeSet<Attrezzo>(this.attrezzi.values());
	}

	/**
	 * Metodo che permette di raggruppare gli attrezzi in una mappa che 
	 * raggruppa gli oggetti per peso.
	 * @return mappa Mappa che associa un intero (rappresentante un peso) 
	 * con l’insieme (comunque non vuoto) degli attrezzi di tale peso: 
	 * tutti gli attrezzi dell'insieme che figura come valore hanno 
	 * lo stesso peso pari all'intero che figura come chiave.
	 */
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){

		Map<Integer, Set<Attrezzo>> mappa = new HashMap<>();
		for(Attrezzo a : this.attrezzi.values()) {
			Set<Attrezzo> tmp = mappa.get(a.getPeso());
			if(tmp==null) {
				tmp = new HashSet<Attrezzo>();
				mappa.put(a.getPeso(),tmp);
			}
			tmp.add(a);
		}
		return mappa;
	}

	/**
	 * Metodo che permette di ordinare il contenuto della borsa per peso.
	 * @return tmp L'insieme gli attrezzi nella borsa 
	 * ordinati per peso e quindi, a parità di peso, per nome.
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){	
		this.comparatore = new ComparatorePerPeso(); 
		SortedSet<Attrezzo> tmp = new TreeSet <Attrezzo>(this.comparatore);
		tmp.addAll(this.attrezzi.values());
		return tmp;
	}

	/**
	 * Inserisce in una stringa tutti gli attrezzi contenuti nella borsa, includendo i rispettivi pesi.
	 * @return Stringa contenente gli attrezzi presenti nella borsa con rispettivo peso parziale.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			s.append(this.attrezzi.values().toString());
		}
		else s.append("Borsa vuota");
		return s.toString();
	}
}