package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import android.util.Log;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class TemplateEsercizioDenominazioneImmagine extends AbstractEsercizio implements Esercizio {
	protected String immagineEsercizio;
	protected String parolaEsercizio;
	protected String audioAiuto;

	public TemplateEsercizioDenominazioneImmagine() {}

	public TemplateEsercizioDenominazioneImmagine(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, String immagineEsercizio, String parolaEsercizio, String audioAiuto) {
		super(idEsercizio, ricompensaCorretto, ricompensaErrato);
		this.immagineEsercizio = immagineEsercizio;
		this.parolaEsercizio = parolaEsercizio;
		this.audioAiuto = audioAiuto;
	}

	public TemplateEsercizioDenominazioneImmagine(int ricompensaCorretto, int ricompensaErrato, String immagineEsercizio, String parolaEsercizio, String audioAiuto) {
		super(ricompensaCorretto, ricompensaErrato);
		this.immagineEsercizio = immagineEsercizio;
		this.parolaEsercizio = parolaEsercizio;
		this.audioAiuto = audioAiuto;
	}

	public TemplateEsercizioDenominazioneImmagine(Map<String, Object> fromDatabaseMap, String fromDatabaseKey) {
		TemplateEsercizioDenominazioneImmagine t = this.fromMap(fromDatabaseMap);

		this.idEsercizio = fromDatabaseKey;
		this.ricompensaCorretto = t.getRicompensaCorretto();
		this.ricompensaErrato = t.getRicompensaErrato();
		this.immagineEsercizio = t.getImmagineEsercizio();
		this.parolaEsercizio = t.getParolaEsercizio();
		this.audioAiuto = t.getAudioAiuto();
	}

	public String getImmagineEsercizio() {
		return immagineEsercizio;
	}

	public String getParolaEsercizio() {
		return parolaEsercizio;
	}

	public String getAudioAiuto() {
		return audioAiuto;
	}

	public void setImmagineEsercizio(String immagineEsercizio) {
		this.immagineEsercizio = immagineEsercizio;
	}

	public void setParolaEsercizio(String parolaEsercizio) {
		this.parolaEsercizio = parolaEsercizio;
	}

	public void setAudioAiuto(String audioAiuto) {
		this.audioAiuto = audioAiuto;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = super.toMap();

		//entityMap.put(CostantiDBTemplateEsercizioDenominazioneImmagine.ID_TEMPLATE_ESERCIZIO, this.idEsercizio);
		entityMap.put(CostantiDBTemplateEsercizioDenominazioneImmagine.IMMAGINE_ESERCIZIO, this.immagineEsercizio);
		entityMap.put(CostantiDBTemplateEsercizioDenominazioneImmagine.PAROLA_ESERCIZIO, this.parolaEsercizio);
		entityMap.put(CostantiDBTemplateEsercizioDenominazioneImmagine.AUDIO_AIUTO, this.audioAiuto);
		return entityMap;
	}

	@Override
	public TemplateEsercizioDenominazioneImmagine fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("TemplateEsercizioDenominazioneImmagine.fromMap()", fromDatabaseMap.toString());
		return new TemplateEsercizioDenominazioneImmagine(
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBTemplateEsercizioDenominazioneImmagine.RICOMPENSA_CORRETTO)),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBTemplateEsercizioDenominazioneImmagine.RICOMPENSA_ERRATO)),
				(String) fromDatabaseMap.get(CostantiDBTemplateEsercizioDenominazioneImmagine.IMMAGINE_ESERCIZIO),
				(String) fromDatabaseMap.get(CostantiDBTemplateEsercizioDenominazioneImmagine.PAROLA_ESERCIZIO),
				(String) fromDatabaseMap.get(CostantiDBTemplateEsercizioDenominazioneImmagine.AUDIO_AIUTO)
		);
	}

	@Override
	public String toString() {
		return "TemplateEsercizioDenominazioneImmagine{" +
				"idEsercizio='" + idEsercizio + '\'' +
				", ricompensaCorretto=" + ricompensaCorretto +
				", ricompensaErrato=" + ricompensaErrato +
				", immagineEsercizio='" + immagineEsercizio + '\'' +
				", parolaEsercizio='" + parolaEsercizio + '\'' +
				", audioAiuto='" + audioAiuto + '\'' +
				'}';
	}

}
