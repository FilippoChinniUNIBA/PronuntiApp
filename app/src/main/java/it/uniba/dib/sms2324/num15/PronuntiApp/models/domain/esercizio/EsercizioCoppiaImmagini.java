package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import android.util.Log;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioAbstract;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class EsercizioCoppiaImmagini extends TemplateEsercizioCoppiaImmagini implements EsercizioEseguibile {
	private String refIdTemplateEsercizio;

	public EsercizioCoppiaImmagini(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, File immagineEsercizioCorretta, File immagineEsercizioErrata, String refIdTemplateEsercizio) {
		super(idEsercizio, ricompensaCorretto, ricompensaErrato, audioEsercizio, immagineEsercizioCorretta, immagineEsercizioErrata);
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
	}

	public EsercizioCoppiaImmagini(int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, File immagineEsercizioCorretta, File immagineEsercizioErrata, String refIdTemplateEsercizio) {
		super(ricompensaCorretto, ricompensaErrato, audioEsercizio, immagineEsercizioCorretta, immagineEsercizioErrata);
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
	}

	public EsercizioCoppiaImmagini(int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, File immagineEsercizioCorretta, File immagineEsercizioErrata) {
		super(ricompensaCorretto, ricompensaErrato, audioEsercizio, immagineEsercizioCorretta, immagineEsercizioErrata);
	}

	public EsercizioCoppiaImmagini(Map<String, Object> fromDatabaseMap, String fromDatabaseKey) {
		EsercizioCoppiaImmagini e = fromMap(fromDatabaseMap);

		this.idEsercizio = fromDatabaseKey;
		this.ricompensaCorretto = e.getRicompensaCorretto();
		this.ricompensaErrato = e.getRicompensaErrato();
		this.audioEsercizio = e.getAudioEsercizio();
		this.immagineEsercizioCorretta = e.getImmagineEsercizioCorretta();
		this.immagineEsercizioErrata = e.getImmagineEsercizioErrata();
		this.refIdTemplateEsercizio = e.getRefIdTemplateEsercizio();
	}

	public String getRefIdTemplateEsercizio() {
		return refIdTemplateEsercizio;
	}

	public void setRefIdTemplateEsercizio(String refIdTemplateEsercizio) {
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = super.toMap();

		//entityMap.put(CostantiDBEsercizioCoppiaImmagini.ID_ESERCIZIO, this.idEsercizio);
		entityMap.put(CostantiDBEsercizioCoppiaImmagini.ID_TEMPLATE_ESERCIZIO, this.refIdTemplateEsercizio);
		return entityMap;
	}

	@Override
	public EsercizioCoppiaImmagini fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("EsercizioCoppiaImmagini.fromMap()", fromDatabaseMap.toString());
		return new EsercizioCoppiaImmagini(
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBEsercizioCoppiaImmagini.RICOMPENSA_CORRETTO)),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBEsercizioCoppiaImmagini.RICOMPENSA_ERRATO)),
				new File((String) fromDatabaseMap.get(CostantiDBEsercizioCoppiaImmagini.AUDIO_ESERCIZIO)),
				new File((String) fromDatabaseMap.get(CostantiDBEsercizioCoppiaImmagini.IMMAGINE_ESERCIZIO_CORRETTA)),
				new File((String) fromDatabaseMap.get(CostantiDBEsercizioCoppiaImmagini.IMMAGINE_ESERCIZIO_ERRATA)),
				(String) fromDatabaseMap.get(CostantiDBEsercizioCoppiaImmagini.ID_TEMPLATE_ESERCIZIO)
		);
	}

	@Override
	public String toString() {
		return "EsercizioCoppiaImmagini{" +
				"idEsercizio='" + idEsercizio + '\'' +
				", ricompensaCorretto=" + ricompensaCorretto +
				", ricompensaErrato=" + ricompensaErrato +
				", audioEsercizio=" + audioEsercizio +
				", immagineEsercizioCorretta=" + immagineEsercizioCorretta +
				", immagineEsercizioErrata=" + immagineEsercizioErrata +
				", refIdTemplateEsercizio='" + refIdTemplateEsercizio + '\'' +
				'}';
	}

}
