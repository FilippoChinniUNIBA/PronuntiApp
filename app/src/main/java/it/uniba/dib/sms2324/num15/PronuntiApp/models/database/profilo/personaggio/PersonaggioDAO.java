package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.personaggio;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class PersonaggioDAO implements DAO<Personaggio> {
	private final FirebaseDatabase db;

	public PersonaggioDAO() {
		db = FirebaseDatabase.getInstance();
	}

	@Override
	public void save(Personaggio obj) {
		DatabaseReference ref = db.getReference("personaggi");

		ref.push().getKey();

		ref.child(obj.getIdPersonaggio()).setValue(obj);

		Task<Void> t = ref.setValue(obj);

	}

	@Override
	public void update(Personaggio obj) {

	}

	@Override
	public void delete(Personaggio obj) {

	}

	@Override
	public List<Personaggio> get(String field, Object value) {
		return null;
	}

	@Override
	public Personaggio getById(String idObj) {
		return null;
	}

	@Override
	public List<Personaggio> getAll() {
		return null;
	}

}
