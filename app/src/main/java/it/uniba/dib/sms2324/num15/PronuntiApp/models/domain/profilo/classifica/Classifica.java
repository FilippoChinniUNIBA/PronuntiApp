package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.classifica;

import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class Classifica implements Persistente {
	private Map<String, Integer> classificaPazienti;

	public Classifica(Map<String, Integer> classificaPazienti) {
		this.classificaPazienti = classificaPazienti;
	}

	public Map<String, Integer> getClassificaPazienti() {
		return classificaPazienti;
	}

	@Override
	public Map<String, Object> toMap() {
		return null;
	}
}
