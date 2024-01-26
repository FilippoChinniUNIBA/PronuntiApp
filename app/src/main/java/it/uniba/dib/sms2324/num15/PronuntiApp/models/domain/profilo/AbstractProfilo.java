package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

public abstract class AbstractProfilo {
	protected String nome;
	protected String cognome;
	protected String email;
	protected String password;
	protected String username;
	protected String sesso;
	protected String dataNascita;

	public AbstractProfilo(String nome, String cognome, String email, String password, String username, String sesso,
			String dataNascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.username = username;
		this.sesso = sesso;
		this.dataNascita = dataNascita;
	}

	public AbstractProfilo() {
		super();
	}

	@Override
	public String toString() {
		return "AbstractProfilo{" +
				"nome='" + nome + '\'' +
				", cognome='" + cognome + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", username='" + username + '\'' +
				", sesso='" + sesso + '\'' +
				", dataNascita='" + dataNascita + '\'' +
				'}';
	}

	public String getUsername() {
		return username;
	}
}
