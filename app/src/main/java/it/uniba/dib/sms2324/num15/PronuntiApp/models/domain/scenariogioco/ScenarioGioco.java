package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

import java.time.LocalDate;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.Esercizio;

public class ScenarioGioco extends TemplateScenarioGioco {
	private int idScenarioGioco;
	private LocalDate dataInizio;
	private int ricompensaFinale;
	private List<Esercizio> esercizi;

	public ScenarioGioco(String immagineSfondo, int idScenarioGioco, LocalDate dataInizio, int ricompensaFinale, List<Esercizio> esercizi) {
		super(immagineSfondo);
		this.idScenarioGioco = idScenarioGioco;
		this.dataInizio = dataInizio;
		this.ricompensaFinale = ricompensaFinale;
		this.esercizi = esercizi;
	}
}
