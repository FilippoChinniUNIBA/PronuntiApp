package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.scenariogioco;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.TemplateScenarioGioco;

public class TemplateScenarioGiocoDAO implements DAO<TemplateScenarioGioco> {
	private final FirebaseDatabase db;

	public TemplateScenarioGiocoDAO() {
		db = FirebaseDatabase.getInstance();
	}

	@Override
	public void save(TemplateScenarioGioco obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.TEMPLATE_SCENARIGIOCO);
		String dbKey = ref.push().getKey();
		ref.child(dbKey).setValue(obj.toMap());
	}

	@Override
	public void update(TemplateScenarioGioco obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.TEMPLATE_SCENARIGIOCO).child(obj.getIdTemplateScenarioGioco());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(TemplateScenarioGioco obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.TEMPLATE_SCENARIGIOCO).child(obj.getIdTemplateScenarioGioco());
		ref.removeValue();
	}

	@Override
	public List<TemplateScenarioGioco> get(String field, Object value) {
		return null;
	}

	@Override
	public TemplateScenarioGioco getById(String idObj) {
		return null;
	}

	@Override
	public List<TemplateScenarioGioco> getAll() {
		return null;
	}

}
