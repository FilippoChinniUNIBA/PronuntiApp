package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.terapia;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;

public class TerapiaDAO implements DAO<Terapia> {
	private final FirebaseDatabase db;

	public TerapiaDAO() {
		db = FirebaseDatabase.getInstance();
	}

	@Override
	public void save(Terapia obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.TERAPIE);
		String dbKey = ref.push().getKey();
		ref.child(dbKey).setValue(obj.toMap());
	}

	@Override
	public void update(Terapia obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.TERAPIE).child(obj.getIdTerapia());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(Terapia obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.TERAPIE).child(obj.getIdTerapia());
		ref.removeValue();
	}

	@Override
	public List<Terapia> get(String field, Object value) {
		return null;
	}

	@Override
	public Terapia getById(String idObj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.TERAPIE);
		ref.child(idObj).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
			@Override
			public void onComplete(@NonNull Task<DataSnapshot> task) {
				if (task.isSuccessful()) {
					DataSnapshot snapshot = task.getResult();

					Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
					Terapia terapia = new Terapia(fromDatabaseMap, idObj);
				}
			}
		});
		return null;
	}

	@Override
	public List<Terapia> getAll() {
		return null;
	}

}
