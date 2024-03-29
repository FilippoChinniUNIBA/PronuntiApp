package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import android.util.Log;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioAbstract;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioSequenzaParole;

public class EsercizioSequenzaParole extends TemplateEsercizioSequenzaParole implements EsercizioEseguibile {
	private String refIdTemplateEsercizio;
	private RisultatoEsercizioSequenzaParole risultatoEsercizio;

	public EsercizioSequenzaParole(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, String audioEsercizio, String parola1, String parola2, String parola3, String refIdTemplateEsercizio, RisultatoEsercizioSequenzaParole risultatoEsercizio) {
		super(idEsercizio, ricompensaCorretto, ricompensaErrato, audioEsercizio, parola1, parola2, parola3);
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
		this.risultatoEsercizio = risultatoEsercizio;
	}

	public EsercizioSequenzaParole(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, String audioEsercizio, String parola1, String parola2, String parola3, String refIdTemplateEsercizio) {
		super(idEsercizio, ricompensaCorretto, ricompensaErrato, audioEsercizio, parola1, parola2, parola3);
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
	}

	public EsercizioSequenzaParole(int ricompensaCorretto, int ricompensaErrato, String audioEsercizio, String parola1, String parola2, String parola3, String refIdTemplateEsercizio) {
		super(ricompensaCorretto, ricompensaErrato, audioEsercizio, parola1, parola2, parola3);
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
	}

	public EsercizioSequenzaParole(int ricompensaCorretto, int ricompensaErrato, String audioEsercizio, String parola1, String parola2, String parola3) {
		super(ricompensaCorretto, ricompensaErrato, audioEsercizio, parola1, parola2, parola3);
	}

	public EsercizioSequenzaParole(int ricompensaCorretto, int ricompensaErrato, String audioEsercizio, String parola1, String parola2, String parola3, String refIdTemplateEsercizio, RisultatoEsercizioSequenzaParole risultatoEsercizio) {
		super(ricompensaCorretto, ricompensaErrato, audioEsercizio, parola1, parola2, parola3);
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
		this.risultatoEsercizio = risultatoEsercizio;
	}

	public EsercizioSequenzaParole(Map<String, Object> fromDatabaseMap, String fromDatabaseKey) {
		EsercizioSequenzaParole e = fromMap(fromDatabaseMap);

		this.idEsercizio = fromDatabaseKey;
		this.ricompensaCorretto = e.getRicompensaCorretto();
		this.ricompensaErrato = e.getRicompensaErrato();
		this.audioEsercizio = e.getAudioEsercizio();
		this.parola1 = e.getParola1();
		this.parola2 = e.getParola2();
		this.parola3 = e.getParola3();
		this.refIdTemplateEsercizio = e.getRefIdTemplateEsercizio();
		this.risultatoEsercizio = e.getRisultatoEsercizio();
	}

	public String getRefIdTemplateEsercizio() {
		return refIdTemplateEsercizio;
	}

	public RisultatoEsercizioSequenzaParole getRisultatoEsercizio() {
		return risultatoEsercizio;
	}

	public void setRefIdTemplateEsercizio(String refIdTemplateEsercizio) {
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
	}

	public void setRisultatoEsercizio(RisultatoEsercizioSequenzaParole risultatoEsercizio) {
		this.risultatoEsercizio = risultatoEsercizio;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = super.toMap();

		//entityMap.put(CostantiDBEsercizioSequenzaParole.ID_ESERCIZIO, this.idEsercizio);
		entityMap.put(CostantiDBEsercizioSequenzaParole.REF_ID_TEMPLATE_ESERCIZIO, this.refIdTemplateEsercizio);

		if (this.risultatoEsercizio != null) {
			entityMap.put(CostantiDBEsercizioAbstract.RISULTATO_ESERCIZIO, this.risultatoEsercizio.toMap());
		}
		return entityMap;
	}

	@Override
	public EsercizioSequenzaParole fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("EsercizioSequenzaParole.fromMap()", fromDatabaseMap.toString());
		return new EsercizioSequenzaParole(
				(String) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.ID_ESERCIZIO),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.RICOMPENSA_CORRETTO)),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.RICOMPENSA_ERRATO)),
				(String) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.AUDIO_ESERCIZIO),
				(String) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.PAROLA_1),
				(String) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.PAROLA_2),
				(String) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.PAROLA_3),
				(String) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.REF_ID_TEMPLATE_ESERCIZIO),
				(fromDatabaseMap.get(CostantiDBEsercizioAbstract.RISULTATO_ESERCIZIO)) != null ? new RisultatoEsercizioSequenzaParole((Map<String, Object>) fromDatabaseMap.get(CostantiDBEsercizioAbstract.RISULTATO_ESERCIZIO)) : null
		);
	}

	@Override
	public String toString() {
		return "EsercizioSequenzaParole{" +
				"idEsercizio='" + idEsercizio + '\'' +
				", ricompensaCorretto=" + ricompensaCorretto +
				", ricompensaErrato=" + ricompensaErrato +
				", audioEsercizio='" + audioEsercizio + '\'' +
				", parola1='" + parola1 + '\'' +
				", parola2='" + parola2 + '\'' +
				", parola3='" + parola3 + '\'' +
				", refIdTemplateEsercizio='" + refIdTemplateEsercizio + '\'' +
				", risultatoEsercizio=" + risultatoEsercizio +
				'}';
	}

}
