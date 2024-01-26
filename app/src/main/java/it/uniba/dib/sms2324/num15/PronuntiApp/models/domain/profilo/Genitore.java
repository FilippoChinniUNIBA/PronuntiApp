package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

public class Genitore extends AbstractProfilo {
	private String email;
	private String telefono;

	public Genitore(String idProfilo, String nome, String cognome, String username, String password, String email, String telefono) {
		super(idProfilo, nome, cognome, username, password);
		this.email = email;
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefono() {
		return telefono;
	}

}
