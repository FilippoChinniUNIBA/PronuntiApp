package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import java.io.File;
import java.util.Map;

public class RisultatoEsercizioDenominazioneImmagine extends AbstractRisultatoEsercizioConAudio {
	public RisultatoEsercizioDenominazioneImmagine(boolean esercizioCorretto, File audioRegistrato) {
		super(esercizioCorretto, audioRegistrato);
	}

	public RisultatoEsercizioDenominazioneImmagine(String idEsercizio, boolean esercizioCorretto, File audioRegistrato) {
		super(idEsercizio, esercizioCorretto, audioRegistrato);
	}

	@Override
	public String toString() {
		return "RisultatoEsercizioDenominazioneImmagine{" +
				"idEsercizio='" + idEsercizio + '\'' +
				", esercizioCorretto=" + esercizioCorretto +
				", audioRegistrato=" + audioRegistrato +
				'}';
	}

}
