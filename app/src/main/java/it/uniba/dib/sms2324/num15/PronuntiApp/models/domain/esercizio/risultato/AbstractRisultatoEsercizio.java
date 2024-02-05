package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBRisultato;

public abstract class AbstractRisultatoEsercizio implements RisultatoEsercizio {
	//protected String idEsercizio;
	protected boolean esitoCorretto;

	public AbstractRisultatoEsercizio() {}

	/*public AbstractRisultatoEsercizio(String idEsercizio, boolean esitoCorretto) {
		this.idEsercizio = idEsercizio;
		this.esitoCorretto = esitoCorretto;
	}*/

	public AbstractRisultatoEsercizio(boolean esitoCorretto) {
		this.esitoCorretto = esitoCorretto;
	}

	/*public String getIdEsercizio() {
		return idEsercizio;
	}*/

	public boolean isEsitoCorretto() {
		return esitoCorretto;
	}

	/*public void setIdEsercizio(String idEsercizio) {
		this.idEsercizio = idEsercizio;
	}*/

	public void setEsitoCorretto(boolean esitoCorretto) {
		this.esitoCorretto = esitoCorretto;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = new HashMap<>();

		//entityMap.put(CostantiDBRisultato.ID_ESERCIZIO, this.idEsercizio);
		entityMap.put(CostantiDBRisultato.ESITO_CORRETTO, this.esitoCorretto);
		return entityMap;
	}

}
