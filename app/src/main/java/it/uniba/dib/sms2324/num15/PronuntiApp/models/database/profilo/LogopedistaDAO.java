package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;

public class LogopedistaDAO implements DAO<Logopedista> {
	private final FirebaseDatabase db;
	private final FirebaseAuth firebaseAuth;

	public LogopedistaDAO() {
		db = FirebaseDatabase.getInstance();
		firebaseAuth = FirebaseAuth.getInstance();
	}

	@Override
	public void save(Logopedista obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI);
		//String dbKey = firebaseAuth.getCurrentUser().getUid();
		String dbKey = obj.getIdProfilo();
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
	public CompletableFuture<List<Logopedista>> get(String field, Object value) {
		CompletableFuture<List<Logopedista>> future = new CompletableFuture<>();

		DatabaseReference ref = db.getReference(CostantiNodiDB.LOGOPEDISTI);
		Query query = DAO.createQuery(ref, field, value);

		query.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				List<Logopedista> result = new ArrayList<>();

				for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
					Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
					Logopedista logopedista = new Logopedista(fromDatabaseMap, snapshot.getKey());
					result.add(logopedista);
				}

				Log.d("LogopedistaDAO.get()", result.toString());
				future.complete(result);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				Log.e("LogopedistaDAO.get()", databaseError.toString());
				future.completeExceptionally(databaseError.toException());
			}
		});

		return future;
	}

	@Override
	public CompletableFuture<Logopedista> getById(String idObj) {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idObj);
			Task<DataSnapshot> task = ref.get();

			Logopedista result = null;

			while (!task.isComplete()) {}

			DataSnapshot snapshot = task.getResult();
			Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
			result = new Logopedista(fromDatabaseMap, idObj);

			Log.d("LogopedistaDAO.getById()", result.toString());
			return result;
		});
	}

	@Override
	public CompletableFuture<List<Logopedista>> getAll() {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.LOGOPEDISTI);
			Task<DataSnapshot> task = ref.get();

			List<Logopedista> result = new ArrayList<>();

			while (!task.isComplete()) {}

			for (DataSnapshot snapshot : task.getResult().getChildren()) {
				Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
				Logopedista logopedista = new Logopedista(fromDatabaseMap, snapshot.getKey());
				result.add(logopedista);
			}

			Log.d("LogopedistaDAO.getAll()", result.toString());
			return result;
		});
	}
}
