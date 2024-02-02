package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import java.util.List;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBLogopedista;
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

	public Logopedista(String nome, String cognome, String username, String email, String password, String telefono, String indirizzo, Classifica classificaPazienti, List<Paziente> pazienti) {
		super(nome, cognome, username, email, password);
		this.telefono = telefono;
		this.indirizzo = indirizzo;
		this.classificaPazienti = classificaPazienti;
		this.pazienti = pazienti;
	}

	public Logopedista(Map<String,Object> fromDatabaseMap){
		super(fromDatabaseMap);
		Logopedista L = fromMap(fromDatabaseMap);
		this.pazienti = L.pazienti;
		this.classificaPazienti = L.classificaPazienti;
		this.telefono = L.telefono;
		this.cognome = L.cognome;
		this.email = L.email;
		this.idProfilo = L.idProfilo;
		this.nome = L.nome;
		this.username = L.username;
		this.password = L.password;
		this.indirizzo = L.indirizzo;
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

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = super.toMap();
		entityMap.put(CostantiDBLogopedista.TELEFONO, this.telefono);
		entityMap.put(CostantiDBLogopedista.INDIRIZZO, this.indirizzo);
		return entityMap;
	}

	@Override
	public Logopedista fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
	}

	@Override
	public String toString() {
		return "Logopedista{" +
				"idProfilo='" + idProfilo + '\'' +
				", nome='" + nome + '\'' +
				", cognome='" + cognome + '\'' +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", telefono='" + telefono + '\'' +
				", indirizzo='" + indirizzo + '\'' +
				", classificaPazienti=" + classificaPazienti +
				", pazienti=" + pazienti +
				'}';
	}

}
