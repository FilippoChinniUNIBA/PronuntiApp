package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class RisultatoEsercizioCoppiaImmagini extends AbstractRisultatoEsercizio {
	public RisultatoEsercizioCoppiaImmagini(boolean esercizioCorretto) {
		super(esercizioCorretto);
	}

	@Override
	public String toString() {
		return "RisultatoEsercizioCoppiaImmagini{" +
				"esercizioCorretto=" + esercizioCorretto +
				'}';
	}
}
