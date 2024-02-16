package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia;

import android.util.Log;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBLogopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTerapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;

public class Terapia implements Persistente<Terapia> {
	private String idTerapia;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private List<ScenarioGioco> scenariGioco;

	public Terapia(String idTerapia, LocalDate dataInizio, LocalDate dataFine, List<ScenarioGioco> scenariGioco) {
		this.idTerapia = idTerapia;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.scenariGioco = scenariGioco;
	}

	public Terapia(String idTerapia, LocalDate dataInizio, LocalDate dataFine) {
		this.idTerapia = idTerapia;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}

	public Terapia(LocalDate dataInizio, LocalDate dataFine, List<ScenarioGioco> scenariGioco) {
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.scenariGioco = scenariGioco;
	}

	public Terapia(LocalDate dataInizio, LocalDate dataFine) {
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}

	public Terapia(Map<String,Object> fromDatabaseMap, String fromDatabaseKey){
		Terapia t = this.fromMap(fromDatabaseMap);

		this.idTerapia = fromDatabaseKey;
		this.dataInizio = t.getDataInizio();
		this.dataFine = t.getDataFine();
		this.scenariGioco = t.getScenariGioco();
	}

	public String getIdTerapia() {
		return idTerapia;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public List<ScenarioGioco> getScenariGioco() {
		return scenariGioco;
	}

	public void setIdTerapia(String idTerapia) {
		this.idTerapia = idTerapia;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public void setScenariGioco(List<ScenarioGioco> scenariGioco) {
		this.scenariGioco = scenariGioco;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = new HashMap<>();

		//entityMap.put(CostantiDBTerapia.ID_TERAPIA, this.idTerapia);
		entityMap.put(CostantiDBTerapia.DATA_INIZIO, this.dataInizio.toString());
		entityMap.put(CostantiDBTerapia.DATA_FINE, this.dataFine.toString());

		if (this.scenariGioco != null) {
			entityMap.put(CostantiDBTerapia.LISTA_SCENARIGIOCO, this.scenariGioco.stream().map(ScenarioGioco::toMap).collect(Collectors.toList()));
		}
		return entityMap;
	}

	@Override
	public Terapia fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("Terapia.fromMap()", fromDatabaseMap.toString());
		return new Terapia(
				LocalDate.parse((String) fromDatabaseMap.get(CostantiDBTerapia.DATA_INIZIO)),
				LocalDate.parse((String) fromDatabaseMap.get(CostantiDBTerapia.DATA_FINE)),
				(fromDatabaseMap.get(CostantiDBTerapia.LISTA_SCENARIGIOCO)) != null ? ((List<Map<String, Object>>) fromDatabaseMap.get(CostantiDBTerapia.LISTA_SCENARIGIOCO)).stream().map(obj -> new ScenarioGioco(obj, null)).collect(Collectors.toList()) : null
		);
	}

	public void setScenario(int posizione, ScenarioGioco scenarioGioco){
		this.scenariGioco.set(posizione,scenarioGioco);
	}

	@Override
	public String toString() {
		return "Terapia{" +
				"idTerapia='" + idTerapia + '\'' +
				", dataInizio=" + dataInizio +
				", dataFine=" + dataFine +
				", scenariGioco=" + scenariGioco +
				'}';
	}

}
