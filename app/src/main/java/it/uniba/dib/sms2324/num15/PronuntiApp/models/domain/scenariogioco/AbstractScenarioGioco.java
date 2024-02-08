package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBAppuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public abstract class AbstractScenarioGioco implements Persistente<AbstractScenarioGioco> {
	protected File immagineSfondo;
	protected File immagineTappa1;
	protected File immagineTappa2;
	protected File immagineTappa3;

	public AbstractScenarioGioco() {}

	public AbstractScenarioGioco(File immagineSfondo, File immagineTappa1, File immagineTappa2, File immagineTappa3) {
		this.immagineSfondo = immagineSfondo;
		this.immagineTappa1 = immagineTappa1;
		this.immagineTappa2 = immagineTappa2;
		this.immagineTappa3 = immagineTappa3;
	}

	public File getImmagineSfondo() {
		return immagineSfondo;
	}

	public File getImmagineTappa1() {
		return immagineTappa1;
	}

	public File getImmagineTappa2() {
		return immagineTappa2;
	}

	public File getImmagineTappa3() {
		return immagineTappa3;
	}

	public void setImmagineSfondo(File immagineSfondo) {
		this.immagineSfondo = immagineSfondo;
	}

	public void setImmagineTappa1(File immagineTappa1) {
		this.immagineTappa1 = immagineTappa1;
	}

	public void setImmagineTappa2(File immagineTappa2) {
		this.immagineTappa2 = immagineTappa2;
	}

	public void setImmagineTappa3(File immagineTappa3) {
		this.immagineTappa3 = immagineTappa3;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = new HashMap<>();

		entityMap.put(CostantiDBTemplateScenarioGioco.IMMAGINE_SFONDO, this.immagineSfondo.getPath());
		entityMap.put(CostantiDBTemplateScenarioGioco.IMMAGINE_TAPPA_1, this.immagineTappa1.getPath());
		entityMap.put(CostantiDBTemplateScenarioGioco.IMMAGINE_TAPPA_2, this.immagineTappa2.getPath());
		entityMap.put(CostantiDBTemplateScenarioGioco.IMMAGINE_TAPPA_3, this.immagineTappa3.getPath());
		return entityMap;
	}

}
