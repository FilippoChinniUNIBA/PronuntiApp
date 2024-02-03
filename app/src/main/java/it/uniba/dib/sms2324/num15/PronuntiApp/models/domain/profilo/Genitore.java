package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import android.util.Log;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBGenitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBPersonaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class Genitore extends AbstractProfilo {
	private String telefono;
	private List<Appuntamento> appuntamenti;
	private String refIdPaziente;

	public Genitore(String idProfilo, String nome, String cognome, String username, String email, String password, String telefono, List<Appuntamento> appuntamenti, String refIdPaziente) {
		super(idProfilo, nome, cognome, username, email, password);
		this.telefono = telefono;
		this.appuntamenti = appuntamenti;
		this.refIdPaziente = refIdPaziente;
	}

	public Genitore(String nome, String cognome, String username, String email, String password, String telefono, List<Appuntamento> appuntamenti, String refIdPaziente) {
		super(nome, cognome, username, email, password);
		this.telefono = telefono;
		this.appuntamenti = appuntamenti;
		this.refIdPaziente = refIdPaziente;
	}

	public Genitore(String nome, String cognome, String username, String email, String password, String telefono, List<Appuntamento> appuntamenti) {
		super(nome, cognome, username, email, password);
		this.telefono = telefono;
		this.appuntamenti = appuntamenti;
	}

	public Genitore(Map<String,Object> fromDatabaseMap, String fromDatabaseKey) {
		Genitore g = this.fromMap(fromDatabaseMap);

		this.idProfilo = fromDatabaseKey;
		this.nome = g.getNome();
		this.cognome = g.getCognome();
		this.username = g.getUsername();
		this.email = g.getEmail();
		this.password = g.getPassword();
		this.telefono = g.getTelefono();
		this.appuntamenti = g.getAppuntamenti();
		this.refIdPaziente = g.getRefIdPaziente();
	}

	public String getTelefono() {
		return telefono;
	}

	public List<Appuntamento> getAppuntamenti() {
		return appuntamenti;
	}

	public String getRefIdPaziente() {
		return refIdPaziente;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setAppuntamenti(List<Appuntamento> appuntamenti) {
		this.appuntamenti = appuntamenti;
	}

	public void setRefIdPaziente(String refIdPaziente) {
		this.refIdPaziente = refIdPaziente;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = super.toMap();

		entityMap.put(CostantiDBGenitore.TELEFONO, this.telefono);
		entityMap.put(CostantiDBGenitore.LISTA_APPUNTAMENTI, this.appuntamenti.stream().map(Appuntamento::getIdAppuntamento).collect(Collectors.toList()));
		entityMap.put(CostantiDBGenitore.ID_PAZIENTE, this.refIdPaziente);
		return entityMap;
	}

	@Override
	public Genitore fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("Genitore.fromMap()", fromDatabaseMap.toString());
		return new Genitore(
				(String) fromDatabaseMap.get(CostantiDBGenitore.NOME),
				(String) fromDatabaseMap.get(CostantiDBGenitore.COGNOME),
				(String) fromDatabaseMap.get(CostantiDBGenitore.USERNAME),
				(String) fromDatabaseMap.get(CostantiDBGenitore.EMAIL),
				(String) fromDatabaseMap.get(CostantiDBGenitore.PASSWORD),
				(String) fromDatabaseMap.get(CostantiDBGenitore.TELEFONO),
				//TODO: lancerà eccezione, bisogna fare una funzione che faccia la get di ogni appuntamento
				(List<Appuntamento>) fromDatabaseMap.get(CostantiDBGenitore.LISTA_APPUNTAMENTI),
				(String) fromDatabaseMap.get(CostantiDBGenitore.ID_PAZIENTE)
		);
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
				", appuntamenti=" + appuntamenti +
				", refIdPaziente='" + refIdPaziente + '\'' +
				'}';
	}

}
