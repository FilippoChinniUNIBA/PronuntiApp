package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;

public class LogopedistaDAO implements DAO<Logopedista> {
	private final FirebaseDatabase db;

	public LogopedistaDAO() {
		db = FirebaseDatabase.getInstance();
	}

	@Override
	public void save(Logopedista obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI);
		String dbKey = ref.push().getKey();
		ref.child(dbKey).setValue(obj.toMap());
	}

	@Override
	public void update(Logopedista obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(obj.getIdProfilo());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(Logopedista obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(obj.getIdProfilo());
		ref.removeValue();
	}

	@Override
	public List<Logopedista> get(String field, Object value) {
		return null;
	}

	@Override
	public Logopedista getById(String idObj) {
		return null;
	}

	@Override
	public List<Logopedista> getAll() {
		return null;
	}

}
