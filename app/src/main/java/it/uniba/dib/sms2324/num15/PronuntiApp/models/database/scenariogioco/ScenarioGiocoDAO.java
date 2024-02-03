package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.scenariogioco;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;

public class ScenarioGiocoDAO implements DAO<ScenarioGioco> {
	private final FirebaseDatabase db;

	public ScenarioGiocoDAO() {
		db = FirebaseDatabase.getInstance();
	}

	@Override
	public void save(ScenarioGioco obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.SCENARIGIOCO);
		String dbKey = ref.push().getKey();
		ref.child(dbKey).setValue(obj.toMap());
	}

	@Override
	public void update(ScenarioGioco obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.SCENARIGIOCO).child(obj.getIdScenarioGioco());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(ScenarioGioco obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.SCENARIGIOCO).child(obj.getIdScenarioGioco());
		ref.removeValue();
	}

	@Override
	public List<ScenarioGioco> get(String field, Object value) {
		return null;
	}

	@Override
	public ScenarioGioco getById(String idObj) {
		return null;
	}

	@Override
	public List<ScenarioGioco> getAll() {
		return null;
	}

}
