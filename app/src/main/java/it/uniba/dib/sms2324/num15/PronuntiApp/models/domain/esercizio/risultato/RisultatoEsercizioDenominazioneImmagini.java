package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import java.io.File;

public class RisultatoEsercizioDenominazioneImmagini extends AbstractRisultatoEsercizioConAudio {
	public RisultatoEsercizioDenominazioneImmagini(boolean esercizioCorretto, File audioRegistrati) {
		super(esercizioCorretto, audioRegistrati);
	}
}
