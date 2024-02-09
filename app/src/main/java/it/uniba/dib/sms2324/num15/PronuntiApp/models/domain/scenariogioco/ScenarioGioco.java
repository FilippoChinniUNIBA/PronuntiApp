package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

import android.util.Log;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTerapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioEseguibile;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioSequenzaParole;

public class ScenarioGioco extends TemplateScenarioGioco {
	private String idScenarioGioco;
	private LocalDate dataInizio;
	private int ricompensaFinale;
	private List<EsercizioEseguibile> esercizi;
	private String refIdTemplateScenarioGioco;

	public ScenarioGioco(String idScenarioGioco, String immagineSfondo, String immagineTappa1, String immagineTappa2, String immagineTappa3, LocalDate dataInizio, int ricompensaFinale, List<EsercizioEseguibile> esercizi, String refIdTemplateScenarioGioco) {
		super(immagineSfondo, immagineTappa1, immagineTappa2, immagineTappa3);
		this.idScenarioGioco = idScenarioGioco;
		this.dataInizio = dataInizio;
		this.ricompensaFinale = ricompensaFinale;
		this.esercizi = esercizi;
		this.refIdTemplateScenarioGioco = refIdTemplateScenarioGioco;
	}

	public ScenarioGioco(String idScenarioGioco, String immagineSfondo, String immagineTappa1, String immagineTappa2, String immagineTappa3, LocalDate dataInizio, int ricompensaFinale, String refIdTemplateScenarioGioco) {
		super(immagineSfondo, immagineTappa1, immagineTappa2, immagineTappa3);
		this.idScenarioGioco = idScenarioGioco;
		this.dataInizio = dataInizio;
		this.ricompensaFinale = ricompensaFinale;
		this.refIdTemplateScenarioGioco = refIdTemplateScenarioGioco;
	}

	public ScenarioGioco(String immagineSfondo, String immagineTappa1, String immagineTappa2, String immagineTappa3, LocalDate dataInizio, int ricompensaFinale, List<EsercizioEseguibile> esercizi, String refIdTemplateScenarioGioco) {
		super(immagineSfondo, immagineTappa1, immagineTappa2, immagineTappa3);
		this.dataInizio = dataInizio;
		this.ricompensaFinale = ricompensaFinale;
		this.esercizi = esercizi;
		this.refIdTemplateScenarioGioco = refIdTemplateScenarioGioco;
	}

	public ScenarioGioco(String immagineSfondo, String immagineTappa1, String immagineTappa2, String immagineTappa3, LocalDate dataInizio, int ricompensaFinale, String refIdTemplateScenarioGioco) {
		super(immagineSfondo, immagineTappa1, immagineTappa2, immagineTappa3);
		this.dataInizio = dataInizio;
		this.ricompensaFinale = ricompensaFinale;
		this.refIdTemplateScenarioGioco = refIdTemplateScenarioGioco;
	}

	public ScenarioGioco(Map<String,Object> fromDatabaseMap, String fromDatabaseKey){
		ScenarioGioco s = this.fromMap(fromDatabaseMap);

		this.idScenarioGioco = fromDatabaseKey;
		this.immagineSfondo = s.getImmagineSfondo();
		this.immagineTappa1 = s.getImmagineTappa1();
		this.immagineTappa2 = s.getImmagineTappa2();
		this.immagineTappa3 = s.getImmagineTappa3();
		this.dataInizio = s.getDataInizio();
		this.ricompensaFinale = s.getRicompensaFinale();
		this.esercizi = s.getEsercizi();
		this.refIdTemplateScenarioGioco = s.getRefIdTemplateScenarioGioco();
	}

	public String getIdScenarioGioco() {
		return idScenarioGioco;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public int getRicompensaFinale() {
		return ricompensaFinale;
	}

	public List<EsercizioEseguibile> getEsercizi() {
		return esercizi;
	}

	public String getRefIdTemplateScenarioGioco() {
		return refIdTemplateScenarioGioco;
	}

	public void setIdScenarioGioco(String idScenarioGioco) {
		this.idScenarioGioco = idScenarioGioco;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public void setRicompensaFinale(int ricompensaFinale) {
		this.ricompensaFinale = ricompensaFinale;
	}

	public void setEsercizi(List<EsercizioEseguibile> esercizi) {
		this.esercizi = esercizi;
	}

	public void setRefIdTemplateScenarioGioco(String refIdTemplateScenarioGioco) {
		this.refIdTemplateScenarioGioco = refIdTemplateScenarioGioco;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = super.toMap();

		//entityMap.put(CostantiDBScenarioGioco.ID_SCENARIOGIOCO, this.idScenarioGioco);
		entityMap.put(CostantiDBScenarioGioco.DATA_INIZIO, this.dataInizio.toString());
		entityMap.put(CostantiDBScenarioGioco.RICOMPENSA_FINALE, this.ricompensaFinale);

		if (this.esercizi != null) {
			entityMap.put(CostantiDBScenarioGioco.LISTA_ESERCIZI, this.esercizi.stream().map(EsercizioEseguibile::toMap).collect(Collectors.toList()));
		}

		entityMap.put(CostantiDBScenarioGioco.REF_ID_TEMPLATE_SCENARIOGIOCO, this.refIdTemplateScenarioGioco);
		return entityMap;
	}

	@Override
	public ScenarioGioco fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("ScenarioGioco.fromMap()", fromDatabaseMap.toString());

		List<EsercizioEseguibile> esercizi = (fromDatabaseMap.get(CostantiDBScenarioGioco.LISTA_ESERCIZI)) != null ?
				((List<Map<String, Object>>) fromDatabaseMap.get(CostantiDBScenarioGioco.LISTA_ESERCIZI)).stream().map(obj -> {
					if (obj.containsKey(CostantiDBEsercizioDenominazioneImmagine.IMMAGINE_ESERCIZIO)) {
						return new EsercizioDenominazioneImmagine((Map<String, Object>) obj, null);
					}
					else if (obj.containsKey(CostantiDBEsercizioSequenzaParole.PAROLA_3)) {
						return new EsercizioSequenzaParole((Map<String, Object>) obj, null);
					}
					else if (obj.containsKey(CostantiDBTemplateEsercizioCoppiaImmagini.IMMAGINE_ESERCIZIO_CORRETTA)) {
						return new EsercizioCoppiaImmagini((Map<String, Object>) obj, null);
					}
					return null;
				}).collect(Collectors.toList()) : null;

		return new ScenarioGioco(
				(String) fromDatabaseMap.get(CostantiDBScenarioGioco.IMMAGINE_SFONDO),
				(String) fromDatabaseMap.get(CostantiDBScenarioGioco.IMMAGINE_TAPPA_1),
				(String) fromDatabaseMap.get(CostantiDBScenarioGioco.IMMAGINE_TAPPA_2),
				(String) fromDatabaseMap.get(CostantiDBScenarioGioco.IMMAGINE_TAPPA_3),
				LocalDate.parse((String) fromDatabaseMap.get(CostantiDBScenarioGioco.DATA_INIZIO)),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBScenarioGioco.RICOMPENSA_FINALE)),
				esercizi,
				(String) fromDatabaseMap.get(CostantiDBScenarioGioco.REF_ID_TEMPLATE_SCENARIOGIOCO)
		);
	}

	@Override
	public String toString() {
		return "ScenarioGioco{" +
				"idScenarioGioco='" + idScenarioGioco + '\'' +
				", immagineSfondo=" + immagineSfondo +
				", immagineTappa1=" + immagineTappa1 +
				", immagineTappa2=" + immagineTappa2 +
				", immagineTappa3=" + immagineTappa3 +
				", dataInizio=" + dataInizio +
				", ricompensaFinale=" + ricompensaFinale +
				", esercizi=" + esercizi +
				", refIdTemplateScenarioGioco='" + refIdTemplateScenarioGioco + '\'' +
				'}';
	}

}
