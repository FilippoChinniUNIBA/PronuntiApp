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

	public ScenarioGioco(File immagineSfondo, String idScenarioGioco, LocalDate dataInizio, int ricompensaFinale, List<Esercizio> esercizi) {
		super(immagineSfondo);
		this.idScenarioGioco = idScenarioGioco;
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


	@Override
	public Map<String, Object> toMap() {
		return null;
	}

	@Override
	public ScenarioGioco fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
	}
}
