package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio;

public class Personaggio {
	private String idPersonaggio;
	private String nomePersonaggio;
	private int costoSblocco;
	private String texture;

	public Personaggio(String idPersonaggio, String nomePersonaggio, int costoSblocco, String texture) {
		this.idPersonaggio = idPersonaggio;
		this.nomePersonaggio = nomePersonaggio;
		this.costoSblocco = costoSblocco;
		this.texture = texture;
	}

	public String getIdPersonaggio() {
		return idPersonaggio;
	}

	public String getNomePersonaggio() {
		return nomePersonaggio;
	}

	public int getCostoSblocco() {
		return costoSblocco;
	}

	public String getTexture() {
		return texture;
	}
}
