package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import java.util.Map;

public abstract class AbstractRisultatoEsercizio implements RisultatoEsercizio {
	protected boolean esercizioCorretto;

	public AbstractRisultatoEsercizio() {
	}

	public AbstractRisultatoEsercizio(boolean esercizioCorretto) {
		this.esercizioCorretto = esercizioCorretto;
	}

	public boolean isEsercizioCorretto() {
		return esercizioCorretto;
	}

	public void setEsercizioCorretto(boolean esercizioCorretto) {
		this.esercizioCorretto = esercizioCorretto;
	}

	@Override
	public Map<String, Object> toMap() {
		return null;
	}

	@Override
	public RisultatoEsercizio fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
	}
}
