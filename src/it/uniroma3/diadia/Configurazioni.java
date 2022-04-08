package it.uniroma3.diadia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

/**
 * Classe per la gestione delle configurazioni iniziali di valori
 * riguardanti la partita che possono variare e vengono contenuti 
 * all'interno del file diadia.properties.
 * @author Leandro Placidi
 * @see DefaultProp
 */
public class Configurazioni {

	private static final String DIADIA_PROPERTIES_READ = "diadia.properties";
	private static final String DIADIA_PROPERTIES_WRITE = "resources/diadia.properties";
	private static final DefaultProp CFU = DefaultProp.CFU;
	private static final DefaultProp PESO_MAX= DefaultProp.PESO_MAX;
	private static Properties prop = null;

	/**
	 * Metodo che ritorna il numero di CFU iniziali del giocatore.
	 * @return Int il numero di CFU iniziali.
	 */
	public static int getCFU() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(CFU.toString()));
	}

	/**
	 * Metodo che ritorna il peso massimo che la borsa 
	 * del giocatore può contenere.
	 * @return Int il peso massimo che la borsa può contere.
	 */
	public static int getPesoMax() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(PESO_MAX.toString()));
	}

	private static void carica() {
		prop = new Properties();
		try {
			prop.load(Configurazioni.class.getClassLoader().getResourceAsStream(DIADIA_PROPERTIES_READ));
			if(prop.getProperty(CFU.toString()).equals("") ||
					prop.getProperty(PESO_MAX.toString()).equals("")) {

				caricaDef();
			}
		}catch(Exception e) {
			caricaDef();
		}
	}

	private static void caricaDef() {
		Writer s;
		try {
			s = new FileWriter(DIADIA_PROPERTIES_WRITE);
			setDefault();
			prop.store(s, null);
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void setDefault() {
		prop.setProperty(PESO_MAX.toString(), DefaultProp.getDefaultSingle(PESO_MAX));
		prop.setProperty(CFU.toString(),DefaultProp.getDefaultSingle(CFU));

	}
}

/*
Set<Object> set = prop.keySet();
Iterator<Object> i = set.iterator();

while(i.hasNext()) {
	String chiave =(String) prop.get(i);
	String stringa = i.next().toString();
	if (DefaultProp.valueOf(chiave)!=null) {
		if (stringa.equals("")) {
			prop.setProperty(chiave, DefaultProp.getDefaultSingle(DefaultProp.valueOf(stringa)));
		}
	}else{
		i.next();
	}
}
 */