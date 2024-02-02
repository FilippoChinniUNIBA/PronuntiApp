package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBProfiloAbstract;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBRisultato;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public abstract class AbstractProfilo implements Profilo {
	protected String idProfilo;
	protected String nome;
	protected String cognome;
	protected String username;
	protected String email;
	protected String password;

	public AbstractProfilo(String idProfilo, String nome, String cognome, String username, String email, String password) {
		this.idProfilo = idProfilo;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public AbstractProfilo(String nome, String cognome, String username, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getIdProfilo() {
		return idProfilo;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setIdProfilo(String idProfilo) {
		this.idProfilo = idProfilo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = new HashMap<>();
		//entityMap.put(CostantiDBProfiloAbstract.ID_PROFILO, this.idProfilo);
		entityMap.put(CostantiDBProfiloAbstract.NOME, this.nome);
		entityMap.put(CostantiDBProfiloAbstract.COGNOME, this.cognome);
		entityMap.put(CostantiDBProfiloAbstract.USERNAME, this.username);
		entityMap.put(CostantiDBProfiloAbstract.EMAIL, this.email);
		entityMap.put(CostantiDBProfiloAbstract.PASSWORD, this.password);
		return entityMap;
	}

	@Override
	public Profilo fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
	}

	@Override
	public String toString() {
		return "AbstractProfilo{" +
				"idProfilo='" + idProfilo + '\'' +
				", nome='" + nome + '\'' +
				", cognome='" + cognome + '\'' +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}

}
