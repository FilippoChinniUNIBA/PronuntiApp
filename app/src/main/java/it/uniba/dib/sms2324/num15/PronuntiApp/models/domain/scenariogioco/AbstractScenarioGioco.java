package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

import java.io.File;
import java.util.Map;

public abstract class AbstractScenarioGioco {
	protected File immagineSfondo;

	public AbstractScenarioGioco(File immagineSfondo) {
		this.immagineSfondo = immagineSfondo;
	}

	public AbstractScenarioGioco(Map<String,Object> fromDatabaseMap){}

	public File getImmagineSfondo() {
		return immagineSfondo;
	}

	public void setImmagineSfondo(File immagineSfondo) {
		this.immagineSfondo = immagineSfondo;
	}

}
