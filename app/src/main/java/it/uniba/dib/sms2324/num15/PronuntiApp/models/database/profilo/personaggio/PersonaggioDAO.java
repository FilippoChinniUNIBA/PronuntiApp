package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.personaggio;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.risorse.errori.MessaggioErrore;

public class PersonaggioDAO implements DAO<Personaggio> {
	private final FirebaseDatabase db;

	public PersonaggioDAO() {
		db = FirebaseDatabase.getInstance();
	}

	@Override
	public void save(Personaggio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.PERSONAGGI);

		String dbKey = ref.push().getKey();

		if (dbKey != null) {
			ref.child(dbKey).setValue(obj.toMap());
		}
		else {
			//MessaggioErrore.CHIAVE_NULL_DATABASE.stampaErrore("Personaggio DAO");
			Log.e("Personaggio DAO", MessaggioErrore.CHIAVE_NULL_DATABASE_ERR.toString());
			throw new NullPointerException(MessaggioErrore.CHIAVE_NULL_DATABASE_ERR.toString());
		}
	}

	@Override
	public void update(Personaggio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.PERSONAGGI).child(obj.getIdPersonaggio());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(Personaggio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.PERSONAGGI).child(obj.getIdPersonaggio());
		ref.removeValue();
	}

	@Override
	public List<Personaggio> get(String field, Object value) {
		DatabaseReference ref = db.getReference("personaggi");
		Query query = Persistente.createQuery(ref, field, value);

		List<Personaggio> result = new LinkedList<>();
		query.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

					Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
					Personaggio personaggio = new Personaggio(fromDatabaseMap);
					personaggio.setIdPersonaggio(snapshot.getKey());

					Log.d("PROVA PERSONAGGIO", personaggio.toString());
					result.add(personaggio);
				}
				Log.d("PROVA PERSONAGGIO", result.toString());
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});

		return result;
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
