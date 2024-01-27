package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

public abstract class AbstractScenarioGioco {
	private String immagineSfondo;

	public AbstractScenarioGioco(String immagineSfondo) {
		this.immagineSfondo = immagineSfondo;
	}

	public String getImmagineSfondo() {
		return immagineSfondo;
	}
}
