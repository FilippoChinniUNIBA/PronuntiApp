package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

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
}
