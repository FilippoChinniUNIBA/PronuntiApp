package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.classifica;

import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class Classifica implements Persistente<Classifica> {
	private Map<String, Integer> classificaPazienti;
	private String refIdLogopedista;

	public Classifica(Map<String, Integer> classificaPazienti, String refIdLogopedista) {
		this.classificaPazienti = classificaPazienti;
		this.refIdLogopedista = refIdLogopedista;
	}

	public Classifica(Map<String, Integer> classificaPazienti) {
		this.classificaPazienti = classificaPazienti;
	}
	/*
	public Classifica(Map<String,Object> fromDatabaseMap){
		Classifica C = fromMap(fromDatabaseMap);
		this.classificaPazienti = C.classificaPazienti;
		this.refIdLogopedista = C.refIdLogopedista;
	}
	*/

	public Map<String, Integer> getClassificaPazienti() {
		return classificaPazienti;
	}

	public String getRefIdLogopedista() {
		return refIdLogopedista;
	}

	public void setClassificaPazienti(Map<String, Integer> classificaPazienti) {
		this.classificaPazienti = classificaPazienti;
	}

	public void setRefIdLogopedista(String refIdLogopedista) {
		this.refIdLogopedista = refIdLogopedista;
	}

	@Override
	public Map<String, Object> toMap() {
		return null;
	}

	@Override
	public Classifica fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
	}

	@Override
	public String toString() {
		return "Classifica{" +
				"classificaPazienti=" + classificaPazienti +
				", refIdLogopedista='" + refIdLogopedista + '\'' +
				'}';
	}

}
