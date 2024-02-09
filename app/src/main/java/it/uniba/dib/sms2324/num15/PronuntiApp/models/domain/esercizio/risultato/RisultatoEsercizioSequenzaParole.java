package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato;

import android.util.Log;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBRisultato;

public class RisultatoEsercizioSequenzaParole extends AbstractRisultatoEsercizioConAudio {
	/*public RisultatoEsercizioSequenzaParole(String idEsercizio, boolean esitoCorretto, File audioRegistrato) {
		super(idEsercizio, esitoCorretto, audioRegistrato);
	}*/

	public RisultatoEsercizioSequenzaParole(boolean esitoCorretto, String audioRegistrato) {
		super(esitoCorretto, audioRegistrato);
	}

	public RisultatoEsercizioSequenzaParole(Map<String, Object> fromDatabaseMap) {
		RisultatoEsercizioSequenzaParole r = this.fromMap(fromDatabaseMap);

		//this.idEsercizio = fromDatabaseKey;
		this.esitoCorretto = r.isEsitoCorretto();
		this.audioRegistrato = r.getAudioRegistrato();
	}

	@Override
	public Map<String, Object> toMap() {
		return super.toMap();
	}

	@Override
	public RisultatoEsercizioSequenzaParole fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("RisultatoEsercizioSequenzaParole.fromMap()", fromDatabaseMap.toString());
		return new RisultatoEsercizioSequenzaParole(
				(boolean) fromDatabaseMap.get(CostantiDBRisultato.ESITO_CORRETTO),
				(String) fromDatabaseMap.get(CostantiDBRisultato.AUDIO_REGISTRATO)
		);
	}

	@Override
	public String toString() {
		return "RisultatoEsercizioSequenzaParole{" +
				"esitoCorretto=" + esitoCorretto +
				", audioRegistrato=" + audioRegistrato +
				'}';
	}

}
