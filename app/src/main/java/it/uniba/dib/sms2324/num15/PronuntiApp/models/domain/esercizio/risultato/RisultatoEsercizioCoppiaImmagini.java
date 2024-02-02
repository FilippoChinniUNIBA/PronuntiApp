package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import android.util.Log;

import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBRisultato;

public class RisultatoEsercizioCoppiaImmagini extends AbstractRisultatoEsercizio {
	public RisultatoEsercizioCoppiaImmagini(String idEsercizio, boolean esitoCorretto) {
		super(idEsercizio, esitoCorretto);
	}
	public RisultatoEsercizioCoppiaImmagini(boolean esitoCorretto) {
		super(esitoCorretto);
	}

	public RisultatoEsercizioCoppiaImmagini(Map<String, Object> fromDatabaseMap) {
		RisultatoEsercizioCoppiaImmagini r = this.fromMap(fromDatabaseMap);

		this.idEsercizio = r.getIdEsercizio();
		this.esitoCorretto = r.isEsitoCorretto();
	}

	@Override
	public Map<String, Object> toMap() {
		return super.toMap();
	}

	@Override
	public RisultatoEsercizioCoppiaImmagini fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("RisultatoEsercizioCoppiaImmagini.fromMap()", fromDatabaseMap.toString());
		return new RisultatoEsercizioCoppiaImmagini(
				(boolean) fromDatabaseMap.get(CostantiDBRisultato.ESITO_CORRETTO)
		);
	}

	@Override
	public String toString() {
		return "RisultatoEsercizioCoppiaImmagini{" +
				"idEsercizio='" + idEsercizio + '\'' +
				", esitoCorretto=" + esitoCorretto +
				'}';
	}

}
