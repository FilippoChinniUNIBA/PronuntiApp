package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import android.util.Log;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class EsercizioSequenzaParole extends TemplateEsercizioSequenzaParole implements EsercizioEseguibile {
	private String refIdTemplateEsercizio;

	public EsercizioSequenzaParole(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, String parola1, String parola2, String parola3, String refIdTemplateEsercizio) {
		super(idEsercizio, ricompensaCorretto, ricompensaErrato, audioEsercizio, parola1, parola2, parola3);
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
	}

	public EsercizioSequenzaParole(int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, String parola1, String parola2, String parola3, String refIdTemplateEsercizio) {
		super(ricompensaCorretto, ricompensaErrato, audioEsercizio, parola1, parola2, parola3);
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
	}

	public EsercizioSequenzaParole(int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, String parola1, String parola2, String parola3) {
		super(ricompensaCorretto, ricompensaErrato, audioEsercizio, parola1, parola2, parola3);
	}

	public EsercizioSequenzaParole(Map<String,Object> fromDatabaseMap) {
		EsercizioSequenzaParole e = fromMap(fromDatabaseMap);

		this.idEsercizio = e.getIdEsercizio();
		this.ricompensaCorretto = e.getRicompensaCorretto();
		this.ricompensaErrato = e.getRicompensaErrato();
		this.audioEsercizio = e.getAudioEsercizio();
		this.parola1 = e.getParola1();
		this.parola2 = e.getParola2();
		this.parola3 = e.getParola3();
		this.refIdTemplateEsercizio = e.getRefIdTemplateEsercizio();
	}

	public String getRefIdTemplateEsercizio() {
		return refIdTemplateEsercizio;
	}

	public void setRefIdTemplateEsercizio(String refIdTemplateEsercizio) {
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
	}

	@Override
	public Map<String,Object> toMap() {
		Map<String,Object> entityMap = super.toMap();

		//entityMap.put(CostantiDBEsercizioSequenzaParole.ID_ESERCIZIO, this.idEsercizio);
		entityMap.put(CostantiDBEsercizioSequenzaParole.ID_TEMPLATE_ESERCIZIO, this.refIdTemplateEsercizio);
		return entityMap;
	}

	@Override
	public EsercizioSequenzaParole fromMap(Map<String,Object> fromDatabaseMap) {
		Log.d("EsercizioSequenzaParole.fromMap()", fromDatabaseMap.toString());
		return new EsercizioSequenzaParole(
				(String) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.ID_ESERCIZIO),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.RICOMPENSA_CORRETTO)),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.RICOMPENSA_ERRATO)),
				new File((String) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.AUDIO_ESERCIZIO)),
				(String) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.PAROLA_1),
				(String) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.PAROLA_2),
				(String) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.PAROLA_3),
				(String) fromDatabaseMap.get(CostantiDBEsercizioSequenzaParole.ID_TEMPLATE_ESERCIZIO)
		);
	}

	@Override
	public String toString() {
		return "EsercizioSequenzaParole{" +
				"idEsercizio='" + idEsercizio + '\'' +
				", ricompensaCorretto=" + ricompensaCorretto +
				", ricompensaErrato=" + ricompensaErrato +
				", audioEsercizio=" + audioEsercizio +
				", parola1='" + parola1 + '\'' +
				", parola2='" + parola2 + '\'' +
				", parola3='" + parola3 + '\'' +
				", refIdTemplateEsercizio='" + refIdTemplateEsercizio + '\'' +
				'}';
	}

}
