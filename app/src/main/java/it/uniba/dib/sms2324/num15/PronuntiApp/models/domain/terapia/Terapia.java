package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;

public class Terapia implements Persistente {
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


	@Override
	public Map<String, Object> toMap() {
		return null;
	}
}
