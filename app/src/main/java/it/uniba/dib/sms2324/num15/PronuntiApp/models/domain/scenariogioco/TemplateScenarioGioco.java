package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class TemplateScenarioGioco extends AbstractScenarioGioco implements Persistente {
	private int idTemplateScenarioGioco;

	public TemplateScenarioGioco(File immagineSfondo, int idTemplateScenarioGioco) {
		super(immagineSfondo);
		this.idTemplateScenarioGioco = idTemplateScenarioGioco;
	}

	public TemplateScenarioGioco(File immagineSfondo) {
		super(immagineSfondo);
	}

	public int getIdTemplateScenarioGioco() {
		return idTemplateScenarioGioco;
	}

	@Override
	public Map<String, Object> toMap() {
		return null;
	}
}
