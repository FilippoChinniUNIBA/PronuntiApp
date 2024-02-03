package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

import android.util.Log;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBLogopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.Esercizio;

public class ScenarioGioco extends TemplateScenarioGioco {
	private String idScenarioGioco;
	private LocalDate dataInizio;
	private int ricompensaFinale;
	private List<Esercizio> esercizi;
	private String refIdTemplateScenarioGioco;

	public ScenarioGioco(String idScenarioGioco, File immagineSfondo, LocalDate dataInizio, int ricompensaFinale, List<Esercizio> esercizi, String refIdTemplateScenarioGioco) {
		super(immagineSfondo);
		this.idScenarioGioco = idScenarioGioco;
		this.dataInizio = dataInizio;
		this.ricompensaFinale = ricompensaFinale;
		this.esercizi = esercizi;
		this.refIdTemplateScenarioGioco = refIdTemplateScenarioGioco;
	}

	public ScenarioGioco(File immagineSfondo, LocalDate dataInizio, int ricompensaFinale, List<Esercizio> esercizi, String refIdTemplateScenarioGioco) {
		super(immagineSfondo);
		this.dataInizio = dataInizio;
		this.ricompensaFinale = ricompensaFinale;
		this.esercizi = esercizi;
		this.refIdTemplateScenarioGioco = refIdTemplateScenarioGioco;
	}

	public ScenarioGioco(File immagineSfondo, LocalDate dataInizio, int ricompensaFinale, List<Esercizio> esercizi) {
		super(immagineSfondo);
		this.dataInizio = dataInizio;
		this.ricompensaFinale = ricompensaFinale;
		this.esercizi = esercizi;
	}

	public ScenarioGioco(Map<String,Object> fromDatabaseMap, String fromDatabaseKey){
		ScenarioGioco s = this.fromMap(fromDatabaseMap);

		this.idScenarioGioco = fromDatabaseKey;
		this.immagineSfondo = s.getImmagineSfondo();
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

	public List<Esercizio> getEsercizi() {
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

	public void setEsercizi(List<Esercizio> esercizi) {
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
		entityMap.put(CostantiDBScenarioGioco.LISTA_ESERCIZI, this.esercizi.stream().map(Esercizio::getIdEsercizio).collect(Collectors.toList()));
		entityMap.put(CostantiDBScenarioGioco.ID_TEMPLATE_SCENARIOGIOCO, this.refIdTemplateScenarioGioco);
		return entityMap;
	}

	@Override
	public ScenarioGioco fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("ScenarioGioco.fromMap()", fromDatabaseMap.toString());
		return new ScenarioGioco(
				new File((String) fromDatabaseMap.get(CostantiDBScenarioGioco.IMMAGINE_SFONDO)),
				LocalDate.parse((String) fromDatabaseMap.get(CostantiDBScenarioGioco.DATA_INIZIO)),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBScenarioGioco.RICOMPENSA_FINALE)),
				//TODO: lanceranno eccezione, bisogna fare una funzione che faccia la get di ogni esercizio
				(List<Esercizio>) fromDatabaseMap.get(CostantiDBScenarioGioco.LISTA_ESERCIZI),
				(String) fromDatabaseMap.get(CostantiDBScenarioGioco.ID_TEMPLATE_SCENARIOGIOCO)
		);
	}

	@Override
	public String toString() {
		return "ScenarioGioco{" +
				"idScenarioGioco='" + idScenarioGioco + '\'' +
				", dataInizio=" + dataInizio +
				", ricompensaFinale=" + ricompensaFinale +
				", esercizi=" + esercizi +
				", refIdTemplateScenarioGioco='" + refIdTemplateScenarioGioco + '\'' +
				'}';
	}

}
