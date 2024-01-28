package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import java.io.File;

public class RisultatoEsercizioSequenzaParole extends AbstractRisultatoEsercizioConAudio {
	public RisultatoEsercizioSequenzaParole(boolean esercizioCorretto, File audioRegistrati) {
		super(esercizioCorretto, audioRegistrati);
	}
}
