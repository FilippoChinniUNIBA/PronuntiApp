package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

public class Logopedista extends AbstractProfilo {
	private String email;
	private String telefono;
	private String indirizzo;

	public Logopedista(String idProfilo, String nome, String cognome, String username, String password, String email, String telefono, String indirizzo) {
		super(idProfilo, nome, cognome, username, password);
		this.email = email;
		this.telefono = telefono;
		this.indirizzo = indirizzo;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getIndirizzo() {
		return indirizzo;
	}
}
