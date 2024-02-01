package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import java.util.List;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.classifica.Classifica;

public class Logopedista extends AbstractProfilo implements Persistente<Logopedista> {
	private String telefono;
	private String indirizzo;
	private Classifica classificaPazienti;
	private List<Paziente> pazienti;

	public Logopedista(String idProfilo, String nome, String cognome, String username, String email, String password, String telefono, String indirizzo) {
		super(idProfilo, nome, cognome, username, email, password);
		this.telefono = telefono;
		this.indirizzo = indirizzo;
	}

	public Logopedista(String nome, String cognome, String username, String email, String password, String telefono, String indirizzo, Classifica classificaPazienti, List<Paziente> pazienti) {
		super(nome, cognome, username, email, password);
		this.telefono = telefono;
		this.indirizzo = indirizzo;
		this.classificaPazienti = classificaPazienti;
		this.pazienti = pazienti;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setClassificaPazienti(Classifica classificaPazienti) {
		this.classificaPazienti = classificaPazienti;
	}

	public void setPazienti(List<Paziente> pazienti) {
		this.pazienti = pazienti;
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

	@Override
	public Map<String, Object> toMap() {
		return null;
	}

	@Override
	public Logopedista fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
	}

	@Override
	public String toString() {
		return "Logopedista{" +
				"telefono='" + telefono + '\'' +
				", indirizzo='" + indirizzo + '\'' +
				", classificaPazienti=" + classificaPazienti +
				", pazienti=" + pazienti +
				", idProfilo='" + idProfilo + '\'' +
				", nome='" + nome + '\'' +
				", cognome='" + cognome + '\'' +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
