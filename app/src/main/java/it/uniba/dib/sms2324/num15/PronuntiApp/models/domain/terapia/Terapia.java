package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;
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

	public Terapia(LocalDate dataInizio, LocalDate dataFine, List<ScenarioGioco> scenariGioco) {
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.scenariGioco = scenariGioco;
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
		return null;
	}

	@Override
	public Terapia fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
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
