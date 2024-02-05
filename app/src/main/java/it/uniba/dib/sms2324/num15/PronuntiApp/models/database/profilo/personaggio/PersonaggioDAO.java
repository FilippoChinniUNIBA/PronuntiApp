package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.personaggio;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class PersonaggioDAO implements DAO<Personaggio> {
	private final FirebaseDatabase db;

	public PersonaggioDAO() {
		db = FirebaseDatabase.getInstance();
	}

	@Override
	public void save(Personaggio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.PERSONAGGI);
		String dbKey = ref.push().getKey();
		ref.child(dbKey).setValue(obj.toMap());
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
	public CompletableFuture<List<Personaggio>> get(String field, Object value) {
		CompletableFuture<List<Personaggio>> future = new CompletableFuture<>();

		DatabaseReference ref = db.getReference(CostantiNodiDB.PERSONAGGI);
		Query query = DAO.createQuery(ref, field, value);

		query.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				List<Personaggio> result = new ArrayList<>();

				for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
					Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
					Personaggio personaggio = new Personaggio(fromDatabaseMap, snapshot.getKey());
					result.add(personaggio);
				}

				Log.d("PersonaggioDAO.get()", result.toString());
				future.complete(result);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				Log.e("PersonaggioDAO.get()", databaseError.toString());
				future.completeExceptionally(databaseError.toException());
			}
		});

		return future;
	}

	@Override
	public CompletableFuture<Personaggio> getById(String idObj) {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.PERSONAGGI).child(idObj);
			Task<DataSnapshot> task = ref.get();

			Personaggio result = null;

			while (!task.isComplete()) {}

			DataSnapshot snapshot = task.getResult();
			Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
			result = new Personaggio(fromDatabaseMap, idObj);

			Log.d("PersonaggioDAO.getById()", result.toString());
			return result;
		});
	}

	@Override
	public CompletableFuture<List<Personaggio>> getAll() {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.PERSONAGGI);
			Task<DataSnapshot> task = ref.get();

			List<Personaggio> result = new ArrayList<>();

			while (!task.isComplete()) {}

			for (DataSnapshot snapshot : task.getResult().getChildren()) {
				Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
				Personaggio personaggio = new Personaggio(fromDatabaseMap, snapshot.getKey());
				result.add(personaggio);
			}

			Log.d("PersonaggioDAO.getAll()", result.toString());
			return result;
		});
	}

}
