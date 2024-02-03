package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import android.util.Log;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBPersonaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class EsercizioDenominazioneImmagine extends TemplateEsercizioDenominazioneImmagine implements EsercizioEseguibile {
	private int countAiuti;
	private String refIdTemplateEsercizio;
	private RisultatoEsercizioDenominazioneImmagine risultatoEsercizio;

	public EsercizioDenominazioneImmagine(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio, int countAiuti, String refIdTemplateEsercizio, RisultatoEsercizioDenominazioneImmagine risultatoEsercizio) {
		super(idEsercizio, ricompensaCorretto, ricompensaErrato, immagineEsercizio);
		this.countAiuti = countAiuti;
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
		this.risultatoEsercizio = risultatoEsercizio;
	}

	public EsercizioDenominazioneImmagine(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio, int countAiuti, String refIdTemplateEsercizio) {
		super(idEsercizio, ricompensaCorretto, ricompensaErrato, immagineEsercizio);
		this.countAiuti = countAiuti;
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
	}

	public EsercizioDenominazioneImmagine(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio, int countAiuti, String refIdTemplateEsercizio) {
		super(ricompensaCorretto, ricompensaErrato, immagineEsercizio);
		this.countAiuti = countAiuti;
		this.refIdTemplateEsercizio = refIdTemplateEsercizio;
	}

	public EsercizioDenominazioneImmagine(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio, int countAiuti) {
		super(ricompensaCorretto, ricompensaErrato, immagineEsercizio);
		this.countAiuti = countAiuti;
	}

	public EsercizioDenominazioneImmagine(Map<String, Object> fromDatabaseMap, String fromDatabaseKey) {
		EsercizioDenominazioneImmagine e = fromMap(fromDatabaseMap);

		this.idEsercizio = fromDatabaseKey;
		this.ricompensaCorretto = e.getRicompensaCorretto();
		this.ricompensaErrato = e.getRicompensaErrato();
		this.immagineEsercizio = e.getImmagineEsercizio();
		this.countAiuti = e.getCountAiuti();
		this.refIdTemplateEsercizio = e.getRefIdTemplateEsercizio();
		//this.risultatoEsercizio = e.getRisultatoEsercizio();
	}

	public int getCountAiuti() {
		return countAiuti;
	}

	public String getRefIdTemplateEsercizio() {
		return refIdTemplateEsercizio;
	}

	public RisultatoEsercizioDenominazioneImmagine getRisultatoEsercizio() {
		return risultatoEsercizio;
	}

	public void setCountAiuti(int countAiuti) {
		this.countAiuti = countAiuti;
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
		entityMap.put(CostantiDBEsercizioDenominazioneImmagine.COUNT_AIUTI, this.countAiuti);
		entityMap.put(CostantiDBEsercizioDenominazioneImmagine.ID_TEMPLATE_ESERCIZIO, this.refIdTemplateEsercizio);
		return entityMap;
	}

	@Override
	public EsercizioDenominazioneImmagine fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("EsercizioDenominazioneImmagine.fromMap()", fromDatabaseMap.toString());
		return new EsercizioDenominazioneImmagine(
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBEsercizioDenominazioneImmagine.RICOMPENSA_CORRETTO)),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBEsercizioDenominazioneImmagine.RICOMPENSA_ERRATO)),
				new File((String) fromDatabaseMap.get(CostantiDBEsercizioDenominazioneImmagine.IMMAGINE_ESERCIZIO)),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBEsercizioDenominazioneImmagine.COUNT_AIUTI)),
				(String) fromDatabaseMap.get(CostantiDBEsercizioDenominazioneImmagine.ID_TEMPLATE_ESERCIZIO)
		);
	}

	@Override
	public String toString() {
		return "EsercizioDenominazioneImmagine{" +
				"idEsercizio='" + idEsercizio + '\'' +
				", ricompensaCorretto=" + ricompensaCorretto +
				", ricompensaErrato=" + ricompensaErrato +
				", immagineEsercizio=" + immagineEsercizio +
				", countAiuti=" + countAiuti +
				", refIdTemplateEsercizio='" + refIdTemplateEsercizio + '\'' +
				", risultatoEsercizio=" + risultatoEsercizio +
				'}';
	}

}
