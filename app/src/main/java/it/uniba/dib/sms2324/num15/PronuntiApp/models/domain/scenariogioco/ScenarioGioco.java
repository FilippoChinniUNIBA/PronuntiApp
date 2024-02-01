package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.Esercizio;

public class ScenarioGioco extends TemplateScenarioGioco {
	private String idScenarioGioco;
	private LocalDate dataInizio;
	private int ricompensaFinale;
	private List<Esercizio> esercizi;
	private String refIdTerapia;

	public ScenarioGioco(File immagineSfondo, String idScenarioGioco, LocalDate dataInizio, int ricompensaFinale, List<Esercizio> esercizi, String refIdTerapia) {
		super(immagineSfondo);
		this.idScenarioGioco = idScenarioGioco;
		this.dataInizio = dataInizio;
		this.ricompensaFinale = ricompensaFinale;
		this.esercizi = esercizi;
		this.refIdTerapia = refIdTerapia;
	}

	public ScenarioGioco(File immagineSfondo, LocalDate dataInizio, int ricompensaFinale, List<Esercizio> esercizi) {
		super(immagineSfondo);
		this.dataInizio = dataInizio;
		this.ricompensaFinale = ricompensaFinale;
		this.esercizi = esercizi;
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

	public String getRefIdTerapia() {
		return refIdTerapia;
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

	public void setRefIdTerapia(String refIdTerapia) {
		this.refIdTerapia = refIdTerapia;
	}

	@Override
	public Map<String, Object> toMap() {
		return null;
	}

	@Override
	public ScenarioGioco fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
	}

	@Override
	public String toString() {
		return "ScenarioGioco{" +
				"idScenarioGioco='" + idScenarioGioco + '\'' +
				", dataInizio=" + dataInizio +
				", ricompensaFinale=" + ricompensaFinale +
				", esercizi=" + esercizi +
				", refIdTerapia='" + refIdTerapia + '\'' +
				'}';
	}

}
