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
	private String redIdPaziente;

	public Terapia(String idTerapia, LocalDate dataInizio, LocalDate dataFine, List<ScenarioGioco> scenariGioco, String redIdPaziente) {
		this.idTerapia = idTerapia;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.scenariGioco = scenariGioco;
		this.redIdPaziente = redIdPaziente;
	}

	public Terapia(LocalDate dataInizio, LocalDate dataFine, List<ScenarioGioco> scenariGioco) {
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.scenariGioco = scenariGioco;
	}

	public Terapia(Map<String,Object> fromDatabaseMap){
		Terapia T = fromMap(fromDatabaseMap);
		this.idTerapia = T.idTerapia;
		this.dataInizio = T.dataInizio;
		this.dataFine = T.dataFine;
		this.scenariGioco = T.scenariGioco;
		this.redIdPaziente = T.redIdPaziente;
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

	public String getRedIdPaziente() {
		return redIdPaziente;
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

	public void setRedIdPaziente(String redIdPaziente) {
		this.redIdPaziente = redIdPaziente;
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
				", redIdPaziente='" + redIdPaziente + '\'' +
				'}';
	}

}
