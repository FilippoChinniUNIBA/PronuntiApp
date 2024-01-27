package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

public class Genitore extends AbstractProfilo {
	private String telefono;

	public Genitore(String idProfilo, String nome, String cognome, String username, String email, String password, String telefono) {
		super(idProfilo, nome, cognome, username, email, password);
		this.telefono = telefono;
	}

	public String getTelefono() {
		return telefono;
	}

}
