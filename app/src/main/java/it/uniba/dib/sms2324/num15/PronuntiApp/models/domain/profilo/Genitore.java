package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class Genitore extends AbstractProfilo implements Persistente<Genitore> {
	private String telefono;
	private String refIdPaziente;

	public Genitore(String idProfilo, String nome, String cognome, String username, String email, String password, String telefono, String refIdPaziente) {
		super(idProfilo, nome, cognome, username, email, password);
		this.telefono = telefono;
		this.refIdPaziente = refIdPaziente;
	}

	public Genitore(String nome, String cognome, String username, String email, String password, String telefono, String refIdPaziente) {
		super(nome, cognome, username, email, password);
		this.telefono = telefono;
		this.refIdPaziente = refIdPaziente;
	}

	public Genitore(String nome, String cognome, String username, String email, String password, String telefono) {
		super(nome, cognome, username, email, password);
		this.telefono = telefono;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getRefIdPaziente() {
		return refIdPaziente;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setRefIdPaziente(String refIdPaziente) {
		this.refIdPaziente = refIdPaziente;
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
				"idProfilo='" + idProfilo + '\'' +
				", nome='" + nome + '\'' +
				", cognome='" + cognome + '\'' +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", telefono='" + telefono + '\'' +
				", refIdPaziente='" + refIdPaziente + '\'' +
				'}';
	}

}
