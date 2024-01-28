package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

import java.io.File;

public class TemplateScenarioGioco extends AbstractScenarioGioco {
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
}
