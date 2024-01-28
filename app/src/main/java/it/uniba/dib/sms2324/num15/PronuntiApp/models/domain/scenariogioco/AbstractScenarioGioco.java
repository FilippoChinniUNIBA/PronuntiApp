package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

import java.io.File;

public abstract class AbstractScenarioGioco {
	private File immagineSfondo;

	public AbstractScenarioGioco(File immagineSfondo) {
		this.immagineSfondo = immagineSfondo;
	}

	public File getImmagineSfondo() {
		return immagineSfondo;
	}
}
