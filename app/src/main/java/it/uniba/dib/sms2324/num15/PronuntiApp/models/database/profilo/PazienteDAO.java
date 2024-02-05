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
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;

public class PazienteDAO implements DAO<Paziente> {

	private final FirebaseDatabase db;
	private final FirebaseAuth firebaseAuth;

	public PazienteDAO() {
		db = FirebaseDatabase.getInstance();
		firebaseAuth = FirebaseAuth.getInstance();
	}

	@Override
	public void save(Paziente obj) {
		String idLogopedista = firebaseAuth.getCurrentUser().getUid();

		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.PAZIENTI);
		String dbKey = obj.getIdProfilo();
		ref.child(dbKey).setValue(obj.toMap());
	}

	@Override
	public void update(Paziente obj) {
		String idLogopedista = firebaseAuth.getCurrentUser().getUid(); //TODO: probabilmente da cambiare, anche il Paziente può fare update su se stesso

		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.PAZIENTI);
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(Paziente obj) {
		String idLogopedista = firebaseAuth.getCurrentUser().getUid();

		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.PAZIENTI);
		ref.removeValue();
	}

	@Override
	public CompletableFuture<List<Paziente>> get(String field, Object value) {
		CompletableFuture<List<Paziente>> future = new CompletableFuture<>();

		DatabaseReference ref = db.getReference(CostantiNodiDB.PAZIENTI); //TODO funzione che capisce il tipo di utente
		Query query = DAO.createQuery(ref, field, value);

		query.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				List<Paziente> result = new ArrayList<>();

				for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
					Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
					Paziente paziente = new Paziente(fromDatabaseMap, snapshot.getKey());
					result.add(paziente);
				}

				Log.d("PazienteDAO.get()", result.toString());
				future.complete(result);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				Log.e("PazienteDAO.get()", databaseError.toString());
				future.completeExceptionally(databaseError.toException());
			}
		});

		return future;
	}

	@Override
	public CompletableFuture<Paziente> getById(String idObj) {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.PAZIENTI).child(idObj); //TODO funzione che capisce il tipo di utente
			Task<DataSnapshot> task = ref.get();

			Paziente result = null;

			while (!task.isComplete()) {}

			DataSnapshot snapshot = task.getResult();
			Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
			result = new Paziente(fromDatabaseMap, idObj);

			Log.d("PazienteDAO.getById()", result.toString());
			return result;
		});
	}

	@Override
	public CompletableFuture<List<Paziente>> getAll() {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.PAZIENTI); //TODO funzione che capisce il tipo di utente
			Task<DataSnapshot> task = ref.get();

			List<Paziente> result = new ArrayList<>();

			while (!task.isComplete()) {}

			for (DataSnapshot snapshot : task.getResult().getChildren()) {
				Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
				Paziente paziente = new Paziente(fromDatabaseMap, snapshot.getKey());
				result.add(paziente);
			}

			Log.d("PazienteDAO.getAll()", result.toString());
			return result;
		});
	}

}
