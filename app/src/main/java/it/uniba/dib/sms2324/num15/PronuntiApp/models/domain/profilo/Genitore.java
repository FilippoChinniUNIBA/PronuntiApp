package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class Genitore extends AbstractProfilo implements Persistente<Genitore> {
	private String telefono;

	public Genitore(String idProfilo, String nome, String cognome, String username, String email, String password, String telefono) {
		super(idProfilo, nome, cognome, username, email, password);
		this.telefono = telefono;
	}

	public Genitore(String nome, String cognome, String username, String email, String password, String telefono) {
		super(nome, cognome, username, email, password);
		this.telefono = telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTelefono() {
		return telefono;
	}

	@Override
	public Map<String, Object> toMap() {
		return null;
	}

	@Override
	public Genitore fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
	}

	@Override
	public String toString() {
		return "Genitore{" +
				"telefono='" + telefono + '\'' +
				", idProfilo='" + idProfilo + '\'' +
				", nome='" + nome + '\'' +
				", cognome='" + cognome + '\'' +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
