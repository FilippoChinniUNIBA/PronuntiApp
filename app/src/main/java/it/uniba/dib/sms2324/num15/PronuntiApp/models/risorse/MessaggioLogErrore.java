package it.uniba.dib.sms2324.num15.PronuntiApp.models.risorse;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * Classe abbandonata per ragioni di tempo, doveva essere una
 * astrazione per i messaggi di log di errore.
 * La si lascia per eventuali sviluppi futuri.
 */
public enum MessaggioLogErrore {
	ERRORE_SCONOSCIUTO(0, "Errore Sconosciuto.");

	private final int codiceErrore;
	private final String descrizioneErrore;

	private MessaggioLogErrore(int codiceErrore, String descrizioneErrore) {
		this.codiceErrore = codiceErrore;
		this.descrizioneErrore = descrizioneErrore;
	}

	public int getCodiceErrore() {
		return codiceErrore;
	}

	public String getDescrizioneErrore() {
		return descrizioneErrore;
	}

	@Override
	public String toString() {
		return "Errore Codice " + codiceErrore + ": " + descrizioneErrore;
	}

}
