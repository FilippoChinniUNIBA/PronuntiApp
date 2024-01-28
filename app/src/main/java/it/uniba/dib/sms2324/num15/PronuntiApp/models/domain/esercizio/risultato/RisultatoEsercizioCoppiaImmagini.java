package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class RisultatoEsercizioCoppiaImmagini extends AbstractRisultatoEsercizio implements Persistente {
	public RisultatoEsercizioCoppiaImmagini(boolean esercizioCorretto) {
		super(esercizioCorretto);
	}

	@Override
	public Map<String, Object> toMap() {
		return null;
	}
}
