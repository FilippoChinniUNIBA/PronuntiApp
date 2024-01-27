package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.classifica.Classifica;

public class Logopedista extends AbstractProfilo {
	private String telefono;
	private String indirizzo;
	private Classifica classificaPazienti;
	private List<Paziente> pazienti;

	public Logopedista(String idProfilo, String nome, String cognome, String username, String email, String password, String telefono, String indirizzo) {
		super(idProfilo, nome, cognome, username, email, password);
		this.telefono = telefono;
		this.indirizzo = indirizzo;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public Classifica getClassificaPazienti() {
		return classificaPazienti;
	}

	public List<Paziente> getPazienti() {
		return pazienti;
	}
}
