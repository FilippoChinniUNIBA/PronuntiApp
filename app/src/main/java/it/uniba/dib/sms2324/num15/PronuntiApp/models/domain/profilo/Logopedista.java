package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

public class Logopedista extends AbstractProfilo {
	private String telefono;
	private String indirizzo;

	public Logopedista(String idProfilo, String nome, String cognome, String username, String email, String password, String telefono, String indirizzo) {
		super(idProfilo, nome, cognome, username, email, password);
		this.telefono = telefono;
		this.indirizzo = indirizzo;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getIndirizzo() {
		return indirizzo;
	}
}
