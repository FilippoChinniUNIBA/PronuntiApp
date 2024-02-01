package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBRisultato;

public abstract class AbstractRisultatoEsercizioConAudio extends AbstractRisultatoEsercizio {
	protected File audioRegistrato;

	public AbstractRisultatoEsercizioConAudio(boolean esercizioCorretto, File audioRegistrato) {
		super(esercizioCorretto);
		this.audioRegistrato = audioRegistrato;
	}

	public AbstractRisultatoEsercizioConAudio(String idEsercizio, boolean esercizioCorretto, File audioRegistrato) {
		super(idEsercizio, esercizioCorretto);
		this.audioRegistrato = audioRegistrato;
	}

	public File getAudioRegistrato() {
		return audioRegistrato;
	}

	public void setAudioRegistrato(File audioRegistrato) {
		this.audioRegistrato = audioRegistrato;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = new HashMap<>();
		//entityMap.put(CostantiDBRisultato.ID_ESERCIZIO, this.idEsercizio);
		entityMap.put(CostantiDBRisultato.CORRETTO, this.esercizioCorretto);
		entityMap.put(CostantiDBRisultato.AUDIO_REGISTRATO, this.audioRegistrato.getPath());
		return entityMap;
	}

	@Override
	public RisultatoEsercizio fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
	}

}
