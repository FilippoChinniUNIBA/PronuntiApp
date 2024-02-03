package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco;

import android.util.Log;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBAppuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;

public class TemplateScenarioGioco extends AbstractScenarioGioco {
	private String idTemplateScenarioGioco;

	public TemplateScenarioGioco() {}

	public TemplateScenarioGioco(String idTemplateScenarioGioco, File immagineSfondo) {
		super(immagineSfondo);
		this.idTemplateScenarioGioco = idTemplateScenarioGioco;
	}

	public TemplateScenarioGioco(File immagineSfondo) {
		super(immagineSfondo);
	}

	public TemplateScenarioGioco(Map<String,Object> fromDatabaseMap, String fromDatabaseKey){
		TemplateScenarioGioco t = this.fromMap(fromDatabaseMap);

		this.idTemplateScenarioGioco = fromDatabaseKey;
		this.immagineSfondo = t.getImmagineSfondo();
	}

	public final String getIdTemplateScenarioGioco() {
		return idTemplateScenarioGioco;
	}

	public void setIdTemplateScenarioGioco(String idTemplateScenarioGioco) {
		this.idTemplateScenarioGioco = idTemplateScenarioGioco;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = super.toMap();

		//entityMap.put(CostantiDBTemplateScenarioGioco.ID_TEMPLATE_SCENARIOGIOCO, this.idTemplateScenarioGioco);
		return entityMap;
	}

	@Override
	public TemplateScenarioGioco fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("TemplateScenarioGioco.fromMap()", fromDatabaseMap.toString());
		return new TemplateScenarioGioco(
				new File((String) fromDatabaseMap.get(CostantiDBTemplateScenarioGioco.IMMAGINE_SFONDO))
		);
	}

	@Override
	public String toString() {
		return "TemplateScenarioGioco{" +
				"idTemplateScenarioGioco='" + idTemplateScenarioGioco + '\'' +
				", immagineSfondo=" + immagineSfondo +
				'}';
	}

}
