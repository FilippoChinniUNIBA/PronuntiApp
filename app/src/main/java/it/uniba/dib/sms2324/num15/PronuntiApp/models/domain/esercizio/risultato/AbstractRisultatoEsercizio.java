package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBRisultato;

public abstract class AbstractRisultatoEsercizio implements RisultatoEsercizio {
	protected boolean esitoCorretto;

	public AbstractRisultatoEsercizio() {}

	public AbstractRisultatoEsercizio(boolean esitoCorretto) {
		this.esitoCorretto = esitoCorretto;
	}

	public boolean isEsitoCorretto() {
		return esitoCorretto;
	}

	public void setEsitoCorretto(boolean esitoCorretto) {
		this.esitoCorretto = esitoCorretto;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = new HashMap<>();

		entityMap.put(CostantiDBRisultato.ESITO_CORRETTO, this.esitoCorretto);
		return entityMap;
	}

}
