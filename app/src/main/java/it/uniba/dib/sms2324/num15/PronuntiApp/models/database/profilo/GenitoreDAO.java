package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;

public class GenitoreDAO implements DAO<Genitore> {
	private final FirebaseDatabase db;

	public GenitoreDAO() {
		db = FirebaseDatabase.getInstance();
	}

	@Override
	public void save(Genitore obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.GENITORI);
		String dbKey = ref.push().getKey();
		ref.child(dbKey).setValue(obj.toMap());
	}

	@Override
	public void update(Genitore obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.GENITORI).child(obj.getIdProfilo());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(Genitore obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.GENITORI).child(obj.getIdProfilo());
		ref.removeValue();
	}

	@Override
	public List<Genitore> get(String field, Object value) {
		return null;
	}

	@Override
	public Genitore getById(String idObj) {
		return null;
	}

	@Override
	public List<Genitore> getAll() {
		return null;
	}

}
