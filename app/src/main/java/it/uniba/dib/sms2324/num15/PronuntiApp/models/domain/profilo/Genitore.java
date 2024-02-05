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

	public Genitore(String idProfilo, String nome, String cognome, String username, String email, String password, String telefono) {
		super(idProfilo, nome, cognome, username, email, password);
		this.telefono = telefono;
	}

	public Genitore(String idProfilo, String nome, String cognome, String username, String email, String password) {
		super(idProfilo, nome, cognome, username, email, password);
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
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = super.toMap();

		entityMap.put(CostantiDBGenitore.TELEFONO, this.telefono);
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
				(String) fromDatabaseMap.get(CostantiDBGenitore.TELEFONO)
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
				'}';
	}

}
