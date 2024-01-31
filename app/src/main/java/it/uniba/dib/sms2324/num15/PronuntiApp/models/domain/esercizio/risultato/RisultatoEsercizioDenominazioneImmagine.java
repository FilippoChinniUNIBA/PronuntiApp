package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class RisultatoEsercizioDenominazioneImmagine extends AbstractRisultatoEsercizioConAudio implements Persistente {
	public RisultatoEsercizioDenominazioneImmagine(boolean esercizioCorretto, File audioRegistrati) {
		super(esercizioCorretto, audioRegistrati);
	}

	@Override
	public Map<String, Object> toMap() {
		return null;
	}
}