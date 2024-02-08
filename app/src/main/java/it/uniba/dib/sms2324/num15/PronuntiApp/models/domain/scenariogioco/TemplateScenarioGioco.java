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

	public TemplateScenarioGioco(File immagineSfondo, File immagineTappa1, File immagineTappa2, File immagineTappa3, String idTemplateScenarioGioco) {
		super(immagineSfondo, immagineTappa1, immagineTappa2, immagineTappa3);
		this.idTemplateScenarioGioco = idTemplateScenarioGioco;
	}

	public TemplateScenarioGioco(File immagineSfondo, File immagineTappa1, File immagineTappa2, File immagineTappa3) {
		super(immagineSfondo, immagineTappa1, immagineTappa2, immagineTappa3);
	}

	public TemplateScenarioGioco(Map<String,Object> fromDatabaseMap, String fromDatabaseKey){
		TemplateScenarioGioco t = this.fromMap(fromDatabaseMap);

		this.idTemplateScenarioGioco = fromDatabaseKey;
		this.immagineSfondo = t.getImmagineSfondo();
		this.immagineTappa1 = t.getImmagineTappa1();
		this.immagineTappa2 = t.getImmagineTappa2();
		this.immagineTappa3 = t.getImmagineTappa3();
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
				new File((String) fromDatabaseMap.get(CostantiDBTemplateScenarioGioco.IMMAGINE_SFONDO)),
				new File((String) fromDatabaseMap.get(CostantiDBTemplateScenarioGioco.IMMAGINE_TAPPA_1)),
				new File((String) fromDatabaseMap.get(CostantiDBTemplateScenarioGioco.IMMAGINE_TAPPA_2)),
				new File((String) fromDatabaseMap.get(CostantiDBTemplateScenarioGioco.IMMAGINE_TAPPA_3))
		);
	}

	@Override
	public String toString() {
		return "TemplateScenarioGioco{" +
				"idTemplateScenarioGioco='" + idTemplateScenarioGioco + '\'' +
				", immagineSfondo=" + immagineSfondo +
				", immagineTappa1=" + immagineTappa1 +
				", immagineTappa2=" + immagineTappa2 +
				", immagineTappa3=" + immagineTappa3 +
				'}';
	}

}
