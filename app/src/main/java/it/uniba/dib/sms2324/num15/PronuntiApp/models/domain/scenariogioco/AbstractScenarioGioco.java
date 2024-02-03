package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBAppuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public abstract class AbstractScenarioGioco implements Persistente<AbstractScenarioGioco> {
	protected File immagineSfondo;

	public AbstractScenarioGioco() {}

	public AbstractScenarioGioco(File immagineSfondo) {
		this.immagineSfondo = immagineSfondo;
	}

	public File getImmagineSfondo() {
		return immagineSfondo;
	}

	public void setImmagineSfondo(File immagineSfondo) {
		this.immagineSfondo = immagineSfondo;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = new HashMap<>();

		entityMap.put(CostantiDBTemplateScenarioGioco.IMMAGINE_SFONDO, this.immagineSfondo.getPath());
		return entityMap;
	}

}
