package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.classifica;

import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBClassifica;
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
		Map<String, Object> entityMap = new HashMap<>();
		entityMap.put(CostantiDBClassifica.CLASSIFICA_PAZIENTI, this.classificaPazienti);
		entityMap.put(CostantiDBClassifica.ID_LOGOPEDISTA, this.refIdLogopedista);
		return entityMap;
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
