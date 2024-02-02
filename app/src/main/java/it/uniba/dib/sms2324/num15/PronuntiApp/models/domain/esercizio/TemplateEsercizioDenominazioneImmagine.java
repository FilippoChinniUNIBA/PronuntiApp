package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import android.util.Log;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class TemplateEsercizioDenominazioneImmagine extends AbstractEsercizio implements Esercizio {
	protected File immagineEsercizio;

	public TemplateEsercizioDenominazioneImmagine() {}

	public TemplateEsercizioDenominazioneImmagine(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio) {
		super(idEsercizio, ricompensaCorretto, ricompensaErrato);
		this.immagineEsercizio = immagineEsercizio;
	}

	public TemplateEsercizioDenominazioneImmagine(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio) {
		super(ricompensaCorretto, ricompensaErrato);
		this.immagineEsercizio = immagineEsercizio;
	}

	public TemplateEsercizioDenominazioneImmagine(Map<String, Object> fromDatabaseMap) {
        TemplateEsercizioDenominazioneImmagine t = this.fromMap(fromDatabaseMap);

        this.idEsercizio = t.getIdEsercizio();
        this.ricompensaCorretto = t.getRicompensaCorretto();
        this.ricompensaErrato = t.getRicompensaErrato();
        this.immagineEsercizio = t.getImmagineEsercizio();
	}

	public File getImmagineEsercizio() {
		return immagineEsercizio;
	}

	public void setImmagineEsercizio(File immagineEsercizio) {
		this.immagineEsercizio = immagineEsercizio;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = super.toMap();

		//entityMap.put(CostantiDBTemplateEsercizioDenominazioneImmagine.ID_TEMPLATE_ESERCIZIO, this.idEsercizio);
		entityMap.put(CostantiDBTemplateEsercizioDenominazioneImmagine.IMMAGINE_ESERCIZIO, this.immagineEsercizio.getPath());
		return entityMap;
	}

	@Override
	public TemplateEsercizioDenominazioneImmagine fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("TemplateEsercizioDenominazioneImmagine.fromMap()", fromDatabaseMap.toString());
		return new TemplateEsercizioDenominazioneImmagine(
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBTemplateEsercizioDenominazioneImmagine.RICOMPENSA_CORRETTO)),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBTemplateEsercizioDenominazioneImmagine.RICOMPENSA_ERRATO)),
				new File((String) fromDatabaseMap.get(CostantiDBTemplateEsercizioDenominazioneImmagine.IMMAGINE_ESERCIZIO))
		);
	}

	@Override
	public String toString() {
		return "TemplateEsercizioDenominazioneImmagine{" +
				"idTemplateEsercizio='" + idEsercizio + '\'' +
				", ricompensaCorretto=" + ricompensaCorretto +
				", ricompensaErrato=" + ricompensaErrato +
				", immagineEsercizio=" + immagineEsercizio +
				'}';
	}

}
