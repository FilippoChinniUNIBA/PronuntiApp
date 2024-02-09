package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import android.util.Log;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioAbstract;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioDenominazioneImmagine;

public class EsercizioDenominazioneImmagine extends TemplateEsercizioDenominazioneImmagine implements EsercizioEseguibile {
	private String refIdTemplateEsercizio;
	private RisultatoEsercizioDenominazioneImmagine risultatoEsercizio;

	public EsercizioDenominazioneImmagine(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, String immagineEsercizio, String parolaEsercizio, String audioAiuto, String refIdTemplateEsercizio, RisultatoEsercizioDenominazioneImmagine risultatoEsercizio) {
		super(idEsercizio, ricompensaCorretto, ricompensaErrato, immagineEsercizio, parolaEsercizio, audioAiuto);
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
		this.risultatoEsercizio = risultatoEsercizio;
	}

	public EsercizioDenominazioneImmagine(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, String immagineEsercizio, String parolaEsercizio, String audioAiuto, String refIdTemplateEsercizio) {
		super(idEsercizio, ricompensaCorretto, ricompensaErrato, immagineEsercizio, parolaEsercizio, audioAiuto);
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
	}

	public EsercizioDenominazioneImmagine(int ricompensaCorretto, int ricompensaErrato, String immagineEsercizio, String parolaEsercizio, String audioAiuto, String refIdTemplateEsercizio) {
		super(ricompensaCorretto, ricompensaErrato, immagineEsercizio, parolaEsercizio, audioAiuto);
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
	}

	public EsercizioDenominazioneImmagine(int ricompensaCorretto, int ricompensaErrato, String immagineEsercizio, String parolaEsercizio, String audioAiuto) {
		super(ricompensaCorretto, ricompensaErrato, immagineEsercizio, parolaEsercizio, audioAiuto);
	}

	public EsercizioDenominazioneImmagine(int ricompensaCorretto, int ricompensaErrato, String immagineEsercizio, String parolaEsercizio, String audioAiuto, String refIdTemplateEsercizio, RisultatoEsercizioDenominazioneImmagine risultatoEsercizio) {
		super(ricompensaCorretto, ricompensaErrato, immagineEsercizio, parolaEsercizio, audioAiuto);
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
		this.risultatoEsercizio = risultatoEsercizio;
	}

	public EsercizioDenominazioneImmagine(Map<String, Object> fromDatabaseMap, String fromDatabaseKey) {
		EsercizioDenominazioneImmagine e = fromMap(fromDatabaseMap);

		this.idEsercizio = fromDatabaseKey;
		this.ricompensaCorretto = e.getRicompensaCorretto();
		this.ricompensaErrato = e.getRicompensaErrato();
		this.immagineEsercizio = e.getImmagineEsercizio();
		this.parolaEsercizio = e.getParolaEsercizio();
		this.audioAiuto = e.getAudioAiuto();
		this.refIdTemplateEsercizio = e.getRefIdTemplateEsercizio();
		this.risultatoEsercizio = e.getRisultatoEsercizio();
	}

	public String getRefIdTemplateEsercizio() {
		return refIdTemplateEsercizio;
	}

	public RisultatoEsercizioDenominazioneImmagine getRisultatoEsercizio() {
		return risultatoEsercizio;
	}

	public void setRefIdTemplateEsercizio(String refIdTemplateEsercizio) {
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
	}

	public void setRisultatoEsercizio(RisultatoEsercizioDenominazioneImmagine risultatoEsercizio) {
		this.risultatoEsercizio = risultatoEsercizio;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = super.toMap();

		//entityMap.put(CostantiDBEsercizioDenominazioneImmagine.ID_ESERCIZIO, this.idEsercizio);
		entityMap.put(CostantiDBEsercizioDenominazioneImmagine.REF_ID_TEMPLATE_ESERCIZIO, this.refIdTemplateEsercizio);

		if (this.risultatoEsercizio != null) {
			entityMap.put(CostantiDBEsercizioAbstract.RISULTATO_ESERCIZIO, this.risultatoEsercizio.toMap());
		}
		return entityMap;
	}

	@Override
	public EsercizioDenominazioneImmagine fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("EsercizioDenominazioneImmagine.fromMap()", fromDatabaseMap.toString());
		return new EsercizioDenominazioneImmagine(
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBEsercizioDenominazioneImmagine.RICOMPENSA_CORRETTO)),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBEsercizioDenominazioneImmagine.RICOMPENSA_ERRATO)),
				(String) fromDatabaseMap.get(CostantiDBEsercizioDenominazioneImmagine.IMMAGINE_ESERCIZIO),
				(String) fromDatabaseMap.get(CostantiDBEsercizioDenominazioneImmagine.PAROLA_ESERCIZIO),
				(String) fromDatabaseMap.get(CostantiDBEsercizioDenominazioneImmagine.AUDIO_AIUTO),
				(String) fromDatabaseMap.get(CostantiDBEsercizioDenominazioneImmagine.REF_ID_TEMPLATE_ESERCIZIO),
				(fromDatabaseMap.get(CostantiDBEsercizioAbstract.RISULTATO_ESERCIZIO)) != null ? new RisultatoEsercizioDenominazioneImmagine((Map<String, Object>) fromDatabaseMap.get(CostantiDBEsercizioAbstract.RISULTATO_ESERCIZIO)) : null
		);
	}

	@Override
	public String toString() {
		return "EsercizioDenominazioneImmagine{" +
				"idEsercizio='" + idEsercizio + '\'' +
				", ricompensaCorretto=" + ricompensaCorretto +
				", ricompensaErrato=" + ricompensaErrato +
				", immagineEsercizio='" + immagineEsercizio + '\'' +
				", parolaEsercizio='" + parolaEsercizio + '\'' +
				", audioAiuto='" + audioAiuto + '\'' +
				", refIdTemplateEsercizio='" + refIdTemplateEsercizio + '\'' +
				", risultatoEsercizio=" + risultatoEsercizio +
				'}';
	}

}
