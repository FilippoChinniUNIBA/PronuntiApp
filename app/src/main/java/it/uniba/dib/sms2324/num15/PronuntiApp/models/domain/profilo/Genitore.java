package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class Genitore extends AbstractProfilo implements Persistente {
	private String telefono;
	private Paziente figlio;

	public Genitore(String idProfilo, String nome, String cognome, String username, String email, String password, String telefono) {
		super(idProfilo, nome, cognome, username, email, password);
		this.telefono = telefono;
	}

	public String getTelefono() {
		return telefono;
	}

	public Paziente getFiglio() {
		return figlio;
	}

	@Override
	public Map<String, Object> toMap() {
		return null;
	}
}
