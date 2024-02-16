package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.Serializable;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizio;

public interface EsercizioEseguibile   {
	String getIdEsercizio();
	RisultatoEsercizio getRisultatoEsercizio();

	Map<String, Object> toMap();

}
