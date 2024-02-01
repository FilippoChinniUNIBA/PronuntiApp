package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class TemplateScenarioGioco extends AbstractScenarioGioco implements Persistente<TemplateScenarioGioco> {
	private String idTemplateScenarioGioco;

	public TemplateScenarioGioco(File immagineSfondo, String idTemplateScenarioGioco) {
		super(immagineSfondo);
		this.idTemplateScenarioGioco = idTemplateScenarioGioco;
	}

	public TemplateScenarioGioco(File immagineSfondo) {
		super(immagineSfondo);
	}

	public String getIdTemplateScenarioGioco() {
		return idTemplateScenarioGioco;
	}


	@Override
	public Map<String, Object> toMap() {
		return null;
	}

	@Override
	public TemplateScenarioGioco fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
	}

	public void setIdTemplateScenarioGioco(String idTemplateScenarioGioco) {
		this.idTemplateScenarioGioco = idTemplateScenarioGioco;
	}

	@Override
	public String toString() {
		return "TemplateScenarioGioco{" +
				"idTemplateScenarioGioco='" + idTemplateScenarioGioco + '\'' +
				'}';
	}
}
