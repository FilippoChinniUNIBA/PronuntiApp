package it.uniba.dib.sms2324.num15.PronuntiApp.models.risorse;

/**
 * Classe abbandonata per ragioni di tempo, doveva essere una
 * astrazione per i messaggi di log di debug.
 * La si lascia per eventuali sviluppi futuri.
 */
public enum MessaggioLogDebug {
	MESSAGGIO_DEBUG_GENERICO(0, "Bho");

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
