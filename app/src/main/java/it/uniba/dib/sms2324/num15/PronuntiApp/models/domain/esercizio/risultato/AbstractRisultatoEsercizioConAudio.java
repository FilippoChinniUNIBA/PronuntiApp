package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBRisultato;

public abstract class AbstractRisultatoEsercizioConAudio extends AbstractRisultatoEsercizio {
	protected String audioRegistrato;

	public AbstractRisultatoEsercizioConAudio() {}

	/*public AbstractRisultatoEsercizioConAudio(String idEsercizio, boolean esitoCorretto, File audioRegistrato) {
		super(idEsercizio, esitoCorretto);
		this.audioRegistrato = audioRegistrato;
	}*/

	public AbstractRisultatoEsercizioConAudio(boolean esitoCorretto, String audioRegistrato) {
		super(esitoCorretto);
		this.audioRegistrato = audioRegistrato;
	}

	public String getAudioRegistrato() {
		return audioRegistrato;
	}

	public void setAudioRegistrato(String audioRegistrato) {
		this.audioRegistrato = audioRegistrato;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = super.toMap();

		entityMap.put(CostantiDBRisultato.AUDIO_REGISTRATO, this.audioRegistrato);
		return entityMap;
	}

}
