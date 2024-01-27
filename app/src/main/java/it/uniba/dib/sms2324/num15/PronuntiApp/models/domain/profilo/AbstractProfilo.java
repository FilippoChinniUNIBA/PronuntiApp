package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

public abstract class AbstractProfilo {
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
}
