package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;

public class Paziente extends AbstractProfilo {
	private int refIdLogopedista;
	private int refIdGenitore;
	private int eta;
	private LocalDate dataNascita;
	private char sesso;
	private int valuta = 0;
	private int punteggioTot = 0;
	private Map<Integer, Boolean> personaggiSbloccati;
	private List<Terapia> terapie;

	public Paziente(String idProfilo, String nome, String cognome, String username, String email, String password, int eta, LocalDate dataNascita, char sesso) {
		super(idProfilo, nome, cognome, username, email, password);
		this.eta = eta;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
	}

	public int getRefIdLogopedista() {
		return refIdLogopedista;
	}

	public int getRefIdGenitore() {
		return refIdGenitore;
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

	public Map<Integer, Boolean> getPersonaggiSbloccati() {
		return personaggiSbloccati;
	}

	public List<Terapia> getTerapie() {
		return terapie;
	}
}
