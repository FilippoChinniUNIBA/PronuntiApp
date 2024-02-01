package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBRisultato;

public abstract class AbstractRisultatoEsercizio implements RisultatoEsercizio {
	protected String idEsercizio;
	protected boolean esercizioCorretto;

	public AbstractRisultatoEsercizio() {
	}

	public AbstractRisultatoEsercizio(boolean esercizioCorretto) {
		this.esercizioCorretto = esercizioCorretto;
	}

	public AbstractRisultatoEsercizio(String idEsercizio, boolean esercizioCorretto) {
		this.idEsercizio = idEsercizio;
		this.esercizioCorretto = esercizioCorretto;
	}

	public boolean isEsercizioCorretto() {
		return esercizioCorretto;
	}

	public void setEsercizioCorretto(boolean esercizioCorretto) {
		this.esercizioCorretto = esercizioCorretto;
	}

	public String getIdEsercizio() {
		return idEsercizio;
	}

	public void setIdEsercizio(String idEsercizio) {
		this.idEsercizio = idEsercizio;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = new HashMap<>();
		//entityMap.put(CostantiDBRisultato.ID_ESERCIZIO, this.idEsercizio);
		entityMap.put(CostantiDBRisultato.CORRETTO, this.esercizioCorretto);
		return entityMap;
	}

	@Override
	public RisultatoEsercizio fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
	}
}
