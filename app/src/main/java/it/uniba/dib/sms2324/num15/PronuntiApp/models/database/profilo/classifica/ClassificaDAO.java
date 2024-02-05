package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.classifica;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.classifica.Classifica;

public class ClassificaDAO /*implements DAO<Classifica>*/ {
	private final FirebaseDatabase db;

	public ClassificaDAO() {
		db = FirebaseDatabase.getInstance();
	}


	/*@Override
	public void save(Classifica obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.CLASSIFICHE);
		String dbKey = obj.getRefIdLogopedista();
		ref.child(dbKey).setValue(obj.toMap());
	}

	@Override
	public void update(Classifica obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.CLASSIFICHE).child(obj.getRefIdLogopedista());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(Classifica obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.CLASSIFICHE).child(obj.getRefIdLogopedista());
		ref.removeValue();
	}

	@Override
	public List<Classifica> get(String field, Object value) {
		return null;
	}

	@Override
	public Classifica getById(String idObj) {
		return null;
	}

	@Override
	public List<Classifica> getAll() {
		return null;
	}*/
}
