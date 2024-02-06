package it.uniba.dib.sms2324.num15.PronuntiApp.models.risorse;

import android.util.Log;

public enum MessaggioLogDebug {
	MESSAGGIOBHO(0, "Bho");

	private final int codiceMessaggio;
	private final String descrizioneMessaggio;

	private MessaggioLogDebug(int codiceMessaggio, String descrizioneMessaggio) {
		this.codiceMessaggio = codiceMessaggio;
		this.descrizioneMessaggio = descrizioneMessaggio;
	}

	public int getCodiceMessaggio() {
		return codiceMessaggio;
	}

	public String getDescrizioneMessaggio() {
		return descrizioneMessaggio;
	}

	@Override
	public String toString() {
		return "Messaggio Codice " + codiceMessaggio + ": " + descrizioneMessaggio;
	}

}
