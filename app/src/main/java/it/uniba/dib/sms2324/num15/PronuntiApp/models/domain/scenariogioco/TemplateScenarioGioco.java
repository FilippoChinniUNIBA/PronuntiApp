package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

public class TemplateScenarioGioco extends AbstractScenarioGioco {
	private int idTemplateScenarioGioco;

	public TemplateScenarioGioco(String immagineSfondo, int idTemplateScenarioGioco) {
		super(immagineSfondo);
		this.idTemplateScenarioGioco = idTemplateScenarioGioco;
	}

	public TemplateScenarioGioco(String immagineSfondo) {
		super(immagineSfondo);
	}

	public int getIdTemplateScenarioGioco() {
		return idTemplateScenarioGioco;
	}
}
