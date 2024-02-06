package it.uniba.dib.sms2324.num15.PronuntiApp.models.risorse;

import android.util.Log;

import androidx.annotation.NonNull;

public enum MessaggioLogErrore {
	ERRORE_SCONOSCIUTO(0, "Errore Sconosciuto."),
	FATAL_ERROR_DATABASE(1, "FATAL ERROR: Connessione con il Database Fallita, l'applicazione non puo' funzionare!"),
	ERRORE_IMPREVISTO_DATABASE(2, "Errore a livello Database inaspettato!"),
	CHIAVE_NULL_DATABASE_ERR(3, "Errore di scrittura codice, la reference del database sta puntando a root."),
	TIPO_DATO_SCONOSCIUTO_DATABASE_ERR(4, "Tipo di dato passato non gestito dal Database."),
	FORMATO_ERR(16, "Formato dell'Input inserito errato! Riprovare.");

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
