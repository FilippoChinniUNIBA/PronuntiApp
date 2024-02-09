package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBAppuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public abstract class AbstractScenarioGioco implements Persistente<AbstractScenarioGioco> {
	protected String immagineSfondo;
	protected String immagineTappa1;
	protected String immagineTappa2;
	protected String immagineTappa3;

	public AbstractScenarioGioco() {}

	public AbstractScenarioGioco(String immagineSfondo, String immagineTappa1, String immagineTappa2, String immagineTappa3) {
		this.immagineSfondo = immagineSfondo;
		this.immagineTappa1 = immagineTappa1;
		this.immagineTappa2 = immagineTappa2;
		this.immagineTappa3 = immagineTappa3;
	}

	public String getImmagineSfondo() {
		return immagineSfondo;
	}

	public String getImmagineTappa1() {
		return immagineTappa1;
	}

	public String getImmagineTappa2() {
		return immagineTappa2;
	}

	public String getImmagineTappa3() {
		return immagineTappa3;
	}

	public void setImmagineSfondo(String immagineSfondo) {
		this.immagineSfondo = immagineSfondo;
	}

	public void setImmagineTappa1(String immagineTappa1) {
		this.immagineTappa1 = immagineTappa1;
	}

	public void setImmagineTappa2(String immagineTappa2) {
		this.immagineTappa2 = immagineTappa2;
	}

	public void setImmagineTappa3(String immagineTappa3) {
		this.immagineTappa3 = immagineTappa3;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = new HashMap<>();

		entityMap.put(CostantiDBTemplateScenarioGioco.IMMAGINE_SFONDO, this.immagineSfondo);
		entityMap.put(CostantiDBTemplateScenarioGioco.IMMAGINE_TAPPA_1, this.immagineTappa1);
		entityMap.put(CostantiDBTemplateScenarioGioco.IMMAGINE_TAPPA_2, this.immagineTappa2);
		entityMap.put(CostantiDBTemplateScenarioGioco.IMMAGINE_TAPPA_3, this.immagineTappa3);
		return entityMap;
	}

}
