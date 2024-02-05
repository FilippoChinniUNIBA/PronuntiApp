package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.classifica;

import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBClassifica;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBRisultato;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioSequenzaParole;

public class Classifica implements Persistente<Classifica> {
	//private String refIdLogopedista;
	private Map<String, Integer> classificaPazienti;

	/*public Classifica(String refIdLogopedista, Map<String, Integer> classificaPazienti) {
		this.refIdLogopedista = refIdLogopedista;
		this.classificaPazienti = classificaPazienti;
	}*/

	public Classifica(Map<String, Integer> classificaPazienti) {
		this.classificaPazienti = classificaPazienti;
	}

	public Classifica(Map<String, Object> fromDatabaseMap, String fromDatabaseKey) {
		Classifica c = this.fromMap(fromDatabaseMap);

		//this.refIdLogopedista = fromDatabaseKey;
		this.classificaPazienti = c.getClassificaPazienti();
	}

	/*public String getRefIdLogopedista() {
		return refIdLogopedista;
	}*/

	public Map<String, Integer> getClassificaPazienti() {
		return classificaPazienti;
	}

	/*public void setRefIdLogopedista(String refIdLogopedista) {
		this.refIdLogopedista = refIdLogopedista;
	}*/

	public void setClassificaPazienti(Map<String, Integer> classificaPazienti) {
		this.classificaPazienti = classificaPazienti;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = new HashMap<>();

		//entityMap.put(CostantiDBClassifica.ID_LOGOPEDISTA, this.refIdLogopedista);
		entityMap.put(CostantiDBClassifica.CLASSIFICA_PAZIENTI, this.classificaPazienti);
		return entityMap;
	}

	@Override
	public Classifica fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("Classifica.fromMap()", fromDatabaseMap.toString());
		return new Classifica(
				(Map<String, Integer>) fromDatabaseMap.get(CostantiDBClassifica.CLASSIFICA_PAZIENTI)
		);
	}

	@Override
	public String toString() {
		return "Classifica{" +
				"classificaPazienti=" + classificaPazienti +
				'}';
	}

}
