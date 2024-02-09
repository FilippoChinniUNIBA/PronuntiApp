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
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;

public class PazienteDAO {

	private final FirebaseDatabase db;
	private final FirebaseAuth firebaseAuth;

	public PazienteDAO() {
		db = FirebaseDatabase.getInstance();
		firebaseAuth = FirebaseAuth.getInstance();
	}

	public void save(Paziente obj, String idLogopedista) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.PAZIENTI);
		String dbKey = obj.getIdProfilo();
		ref.child(dbKey).setValue(obj.toMap());
	}

	public CompletableFuture<Void> update(Paziente obj) {
		CompletableFuture<Void> future = new CompletableFuture<>();

		String idPaziente = obj.getIdProfilo();
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI);

		ref.get().addOnCompleteListener(task -> {
			if (task.isSuccessful()) {
				DataSnapshot dataSnapshot = task.getResult();
				DatabaseReference refPaziente = null;

				for (DataSnapshot logopedistaSnapshot : dataSnapshot.getChildren()) {
					for (DataSnapshot pazienteSnapshot : logopedistaSnapshot.child(CostantiNodiDB.PAZIENTI).getChildren()) {
						if (pazienteSnapshot.getKey().equals(idPaziente)) {
							refPaziente = pazienteSnapshot.getRef();
							refPaziente.setValue(obj.toMap());

							Log.d("PazienteDAO.update()", "Paziente aggiornato con successo");
							future.complete(null);
							break;
						}
					}
					if (refPaziente != null) break;
				}
			} else {
				future.completeExceptionally(task.getException());
				Log.e("PazienteDAO.update()", "Errore nel recupero dei dati: " + task.getException());
			}
		});

		return future;
	}

	public void delete(Paziente obj) {
		//solo il logopedista può cancellare un paziente
		String idLogopedista = firebaseAuth.getCurrentUser().getUid();

		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.PAZIENTI).child(obj.getIdProfilo());
		ref.removeValue();
	}

	//Probabilmente non serve
	/*public CompletableFuture<List<Paziente>> get(String idPaziente, String field, Object value) {
		CompletableFuture<List<Paziente>> future = new CompletableFuture<>();

		DatabaseReference ref = db.getReference(CostantiNodiDB.PAZIENTI);
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
	}*/

	public CompletableFuture<Paziente> getById(String idObj) {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.LOGOPEDISTI);
			Task<DataSnapshot> task = ref.get();

			Paziente result = null;

			while (!task.isComplete()) {}

			for (DataSnapshot logopedistaSnapshot : task.getResult().getChildren()) {
				for (DataSnapshot pazienteSnapshot : logopedistaSnapshot.child(CostantiNodiDB.PAZIENTI).getChildren()) {
					if (pazienteSnapshot.getKey().equals(idObj)) {
						Map<String, Object> fromDatabaseMap = (Map<String, Object>) pazienteSnapshot.getValue();
						result = new Paziente(fromDatabaseMap, idObj);
						break;
					}
				}
				if (result != null) break;
			}

			Log.d("PazienteDAO.getById()", (result == null) ? result.toString() : "null");
			return result;
		});
	}

	public CompletableFuture<List<Paziente>> getAllFromLogopedista(String idLogopedista) {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.PAZIENTI);
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

	public CompletableFuture<Logopedista> getDatiLogopedistaByIdPaziente(String idPaziente) {
		CompletableFuture<Logopedista> future = new CompletableFuture<>();

		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI);

		ref.get().addOnCompleteListener(task -> {
			if (task.isSuccessful()) {
				DataSnapshot dataSnapshot = task.getResult();
				DatabaseReference refLogopedista = null;

				for (DataSnapshot logopedistaSnapshot : dataSnapshot.getChildren()) {
					for (DataSnapshot pazienteSnapshot : logopedistaSnapshot.child(CostantiNodiDB.PAZIENTI).getChildren()) {
						if (pazienteSnapshot.getKey().equals(idPaziente)) {
							refLogopedista = logopedistaSnapshot.getRef();
							Map<String, Object> fromDatabaseMap = (Map<String, Object>) logopedistaSnapshot.getValue();
							Logopedista logopedista = new Logopedista(fromDatabaseMap, logopedistaSnapshot.getKey());
							logopedista.setPazienti(null);

							Log.d("PazienteDAO.getDatiLogopedistaByIdPaziente()", logopedista.toString());
							future.complete(logopedista);
							break;
						}
					}
					if (refLogopedista != null) break;
				}
			} else {
				future.completeExceptionally(task.getException());
				Log.e("PazienteDAO.getDatiLogopedistaByIdPaziente()", "Errore nel recupero dei dati: " + task.getException());
			}
		});

		return future;
	}

}
