package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import android.util.Log;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBRisultato;

public class RisultatoEsercizioDenominazioneImmagine extends AbstractRisultatoEsercizioConAudio {
	private int countAiuti;

	/*public RisultatoEsercizioDenominazioneImmagine(String idEsercizio, boolean esitoCorretto, File audioRegistrato, int countAiuti) {
		super(idEsercizio, esitoCorretto, audioRegistrato);
		this.countAiuti = countAiuti;
	}*/

	public RisultatoEsercizioDenominazioneImmagine(boolean esitoCorretto, String audioRegistrato, int countAiuti) {
		super(esitoCorretto, audioRegistrato);
		this.countAiuti = countAiuti;
	}

	public RisultatoEsercizioDenominazioneImmagine(Map<String, Object> fromDatabaseMap) {
		RisultatoEsercizioDenominazioneImmagine r = this.fromMap(fromDatabaseMap);

		//this.idEsercizio = fromDatabaseKey;
		this.esitoCorretto = r.isEsitoCorretto();
		this.audioRegistrato = r.getAudioRegistrato();
		this.countAiuti = r.getCountAiuti();
	}

	public int getCountAiuti() {
		return countAiuti;
	}

	public void setCountAiuti(int countAiuti) {
		this.countAiuti = countAiuti;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = super.toMap();

		entityMap.put(CostantiDBRisultato.COUNT_AIUTI, this.countAiuti);
		return entityMap;

	}

	@Override
	public RisultatoEsercizioDenominazioneImmagine fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("RisultatoEsercizioDenominazioneImmagine.fromMap()", fromDatabaseMap.toString());
		return new RisultatoEsercizioDenominazioneImmagine(
				(boolean) fromDatabaseMap.get(CostantiDBRisultato.ESITO_CORRETTO),
				(String) fromDatabaseMap.get(CostantiDBRisultato.AUDIO_REGISTRATO),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBRisultato.COUNT_AIUTI))
		);
	}

	@Override
	public String toString() {
		return "RisultatoEsercizioDenominazioneImmagine{" +
				"esitoCorretto=" + esitoCorretto +
				", audioRegistrato=" + audioRegistrato +
				", countAiuti=" + countAiuti +
				'}';
	}

}
