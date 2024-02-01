package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import java.io.File;
import java.util.Map;

public class RisultatoEsercizioSequenzaParole extends AbstractRisultatoEsercizioConAudio {
	public RisultatoEsercizioSequenzaParole(boolean esercizioCorretto, File audioRegistrato) {
		super(esercizioCorretto, audioRegistrato);
	}

	public RisultatoEsercizioSequenzaParole(String idEsercizio, boolean esercizioCorretto, File audioRegistrato) {
		super(idEsercizio, esercizioCorretto, audioRegistrato);
	}

	@Override
	public String toString() {
		return "RisultatoEsercizioSequenzaParole{" +
				"idEsercizio='" + idEsercizio + '\'' +
				", esercizioCorretto=" + esercizioCorretto +
				", audioRegistrato=" + audioRegistrato +
				'}';
	}

}
