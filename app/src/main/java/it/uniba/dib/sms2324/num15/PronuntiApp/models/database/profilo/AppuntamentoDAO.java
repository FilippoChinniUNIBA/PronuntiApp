package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;

public class AppuntamentoDAO implements DAO<Appuntamento> {
	private final FirebaseDatabase db;

	public AppuntamentoDAO() {
		db = FirebaseDatabase.getInstance();
	}

	@Override
	public void save(Appuntamento obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.APPUNTAMENTI);
		String dbKey = ref.push().getKey();
		ref.child(dbKey).setValue(obj.toMap());
	}

	@Override
	public void update(Appuntamento obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.APPUNTAMENTI).child(obj.getIdAppuntamento());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(Appuntamento obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.APPUNTAMENTI).child(obj.getIdAppuntamento());
		ref.removeValue();
	}

	@Override
	public List<Appuntamento> get(String field, Object value) {
		return null;
	}

	@Override
	public Appuntamento getById(String idObj) {
		return null;
	}

	@Override
	public List<Appuntamento> getAll() {
		return null;
	}

}
