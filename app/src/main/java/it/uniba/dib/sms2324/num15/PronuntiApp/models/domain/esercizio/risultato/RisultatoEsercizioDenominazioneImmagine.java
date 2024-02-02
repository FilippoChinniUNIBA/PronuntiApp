package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import android.util.Log;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBRisultato;

public class RisultatoEsercizioDenominazioneImmagine extends AbstractRisultatoEsercizioConAudio {
	public RisultatoEsercizioDenominazioneImmagine(String idEsercizio, boolean esitoCorretto, File audioRegistrato) {
		super(idEsercizio, esitoCorretto, audioRegistrato);
	}

	public RisultatoEsercizioDenominazioneImmagine(boolean esitoCorretto, File audioRegistrato) {
		super(esitoCorretto, audioRegistrato);
	}

	public RisultatoEsercizioDenominazioneImmagine(Map<String, Object> fromDatabaseMap, String fromDatabaseKey) {
		RisultatoEsercizioDenominazioneImmagine r = this.fromMap(fromDatabaseMap);

		this.idEsercizio = fromDatabaseKey;
		this.esitoCorretto = r.isEsitoCorretto();
		this.audioRegistrato = r.getAudioRegistrato();
	}

	@Override
	public Map<String, Object> toMap() {
		return super.toMap();
	}

	@Override
	public RisultatoEsercizioDenominazioneImmagine fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("RisultatoEsercizioDenominazioneImmagine.fromMap()", fromDatabaseMap.toString());
		return new RisultatoEsercizioDenominazioneImmagine(
				(boolean) fromDatabaseMap.get(CostantiDBRisultato.ESITO_CORRETTO),
				new File((String) fromDatabaseMap.get(CostantiDBRisultato.AUDIO_REGISTRATO))
		);
	}

	@Override
	public String toString() {
		return "RisultatoEsercizioDenominazioneImmagine{" +
				"idEsercizio='" + idEsercizio + '\'' +
				", esitoCorretto=" + esitoCorretto +
				", audioRegistrato=" + audioRegistrato +
				'}';
	}

}
