package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import java.time.LocalDate;

public class Paziente extends AbstractProfilo {
	private int eta;
	private LocalDate dataNascita;
	private char sesso;
	private int valuta = 0;
	private int punteggioTot = 0;

	public Paziente(String idProfilo, String nome, String cognome, String username, String password, int eta, LocalDate dataNascita, char sesso) {
		super(idProfilo, nome, cognome, username, password);
		this.eta = eta;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
	}

	public int getEta() {
		return eta;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public char getSesso() {
		return sesso;
	}

	public int getValuta() {
		return valuta;
	}

	public int getPunteggioTot() {
		return punteggioTot;
	}
}
