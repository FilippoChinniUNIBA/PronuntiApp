package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import java.io.File;

public abstract class AbstractRisultatoEsercizioConAudio extends AbstractRisultatoEsercizio {
	protected File audioRegistrati;

	public AbstractRisultatoEsercizioConAudio() {
	}

	public AbstractRisultatoEsercizioConAudio(boolean esercizioCorretto, File audioRegistrati) {
		super(esercizioCorretto);
		this.audioRegistrati = audioRegistrati;
	}

	public File getAudioRegistrati() {
		return audioRegistrati;
	}

	public void setAudioRegistrati(File audioRegistrati) {
		this.audioRegistrati = audioRegistrati;
	}
}
