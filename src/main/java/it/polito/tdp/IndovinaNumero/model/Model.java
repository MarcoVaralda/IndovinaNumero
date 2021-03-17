package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;

public class Model {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}

	public void nuovaPartita() {
		//gestione inizio nuova partita
    	this.segreto = (int) (Math.random() * NMAX) +1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	// la gestione dell'interfaccia è delegata alla classe controller
	}
	
	public int tentativo(int tentativo) { // 0=tentativo corretto; -1=tentativo troppo basso; 1=tentativo troppo alto
		// Controllo se la partita è in corso
		if(inGioco==false)
			throw new IllegalStateException("Hai perso! il segreto era: "+this.segreto);
		
		// Controllo l'input: il valore deve essere compreso tra 1 e NMAX
		if(!tentativoValido(tentativo))
			throw new InvalidParameterException("devi inserire un numero compreso tra 1 e NMAX");
		 
		// tentativo valido
		this.tentativiFatti++;
		if(this.tentativiFatti==TMAX-1)
			this.inGioco=false;
		
		// Controllo il tentativo
		if(tentativo==segreto)
			return 0; // Partita finita
		if(tentativo<segreto)
			return -1;
		else
			return 1;
	}
	
	public boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo>NMAX)
			return false;
		return true;
	}

}
