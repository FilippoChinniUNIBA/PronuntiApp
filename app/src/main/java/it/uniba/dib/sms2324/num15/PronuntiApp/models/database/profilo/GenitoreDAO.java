package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo;

import android.provider.ContactsContract;
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
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;

public class GenitoreDAO {
	private final FirebaseDatabase db;
	private final FirebaseAuth firebaseAuth;

	public GenitoreDAO() {
		db = FirebaseDatabase.getInstance();
		firebaseAuth = FirebaseAuth.getInstance();
	}

	public void save(Genitore obj, String idLogopedista, String idPaziente) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.PAZIENTI).child(idPaziente).child(CostantiNodiDB.GENITORE);
		String dbKey = obj.getIdProfilo();
		ref.child(dbKey).setValue(obj.toMap());
	}

	public CompletableFuture<Void> update(Genitore obj) {
		CompletableFuture<Void> future = new CompletableFuture<>();

		String idGenitore = firebaseAuth.getCurrentUser().getUid();
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI);

		ref.get().addOnCompleteListener(task -> {
			if (task.isSuccessful()) {
				DataSnapshot dataSnapshot = task.getResult();
				DatabaseReference refGenitore = null;

				for (DataSnapshot logopedistaSnapshot : dataSnapshot.getChildren()) {
					for (DataSnapshot pazienteSnapshot : logopedistaSnapshot.child(CostantiNodiDB.PAZIENTI).getChildren()) {
						DataSnapshot temp = pazienteSnapshot.child(CostantiNodiDB.GENITORE);
						if (temp.getKey().equals(idGenitore)) {
							refGenitore = temp.getRef();
							refGenitore.setValue(obj.toMap());

							Log.d("GenitoreDAO.update()", "Genitore aggiornato con successo");
							future.complete(null);
							break;
						}
					}
					if (refGenitore != null) break;
				}
			} else {
				future.completeExceptionally(task.getException());
				Log.e("GenitoreDAO.update()", "Errore nel recupero dei dati: " + task.getException());
			}
		});

		return future;
	}

	public void delete(Genitore obj, String idPaziente) {
		//solo il logopedista può cancellare un genitore
		String idLogopedista = firebaseAuth.getCurrentUser().getUid();

		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.PAZIENTI).child(idPaziente).child(CostantiNodiDB.GENITORE);
		ref.removeValue();
	}

	//Probabilmente non serve
	/*public CompletableFuture<List<Genitore>> get(String idGenitore, String field, Object value) {
		CompletableFuture<List<Genitore>> future = new CompletableFuture<>();

		DatabaseReference ref = db.getReference(CostantiNodiDB.GENITORE);
		Query query = DAO.createQuery(ref, field, value);

		query.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				List<Genitore> result = new ArrayList<>();

				for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
					Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
					Genitore genitore = new Genitore(fromDatabaseMap, snapshot.getKey());
					result.add(genitore);
				}

				Log.d("GenitoreDAO.get()", result.toString());
				future.complete(result);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				Log.e("GenitoreDAO.get()", databaseError.toString());
				future.completeExceptionally(databaseError.toException());
			}
		});

		return future;
	}*/

	public CompletableFuture<Genitore> getById(String idObj) {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.LOGOPEDISTI);
			Task<DataSnapshot> task = ref.get();

			Genitore result = null;

			while (!task.isComplete()) {}

			for (DataSnapshot logopedistaSnapshot : task.getResult().getChildren()) {
				for (DataSnapshot pazienteSnapshot : logopedistaSnapshot.child(CostantiNodiDB.PAZIENTI).getChildren()) {
					DataSnapshot genitoreSnapshot = pazienteSnapshot.child(CostantiNodiDB.GENITORE);

					if (genitoreSnapshot.getKey().equals(idObj)) {
						Map<String, Object> fromDatabaseMap = (Map<String, Object>) genitoreSnapshot.getValue();
						result = new Genitore(fromDatabaseMap, idObj);
						break;
					}
				}
				if (result != null) break;
			}

			Log.d("GenitoreDAO.getById()", (result == null) ? result.toString() : "null");
			return result;
		});
	}

	public CompletableFuture<List<Genitore>> getAllFromLogopedista(String idLogopedista) {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.PAZIENTI);
			Task<DataSnapshot> task = ref.get();

			List<Genitore> result = new ArrayList<>();

			while (!task.isComplete()) {}

			for (DataSnapshot snapshot : task.getResult().getChildren()) {
				DataSnapshot genitoreSnapshot = snapshot.child(CostantiNodiDB.GENITORE);
				Map<String, Object> fromDatabaseMap = (Map<String, Object>) genitoreSnapshot.getValue();
				Genitore genitore = new Genitore(fromDatabaseMap, snapshot.getKey());
				result.add(genitore);
			}

			Log.d("GenitoreDAO.getAll()", result.toString());
			return result;
		});
	}

	public CompletableFuture<Logopedista> getDatiLogopedistaByIdGenitore(String idGenitore) {
		CompletableFuture<Logopedista> future = new CompletableFuture<>();

		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI);

		ref.get().addOnCompleteListener(task -> {
			if (task.isSuccessful()) {
				DataSnapshot dataSnapshot = task.getResult();
				DatabaseReference refLogopedista = null;

				for (DataSnapshot logopedistaSnapshot : dataSnapshot.getChildren()) {
					for (DataSnapshot pazienteSnapshot : logopedistaSnapshot.child(CostantiNodiDB.PAZIENTI).getChildren()) {
						DataSnapshot genitoreSnapshot = pazienteSnapshot.child(CostantiNodiDB.GENITORE);

						if (genitoreSnapshot.getKey().equals(idGenitore)) {
							refLogopedista = logopedistaSnapshot.getRef();
							Map<String, Object> fromDatabaseMap = (Map<String, Object>) logopedistaSnapshot.getValue();
							Logopedista logopedista = new Logopedista(fromDatabaseMap, logopedistaSnapshot.getKey());
							logopedista.setPazienti(null);

							Log.d("GenitoreDAO.getDatiLogopedistaByIdGenitore()", logopedista.toString());
							future.complete(logopedista);
							break;
						}
					}
					if (refLogopedista != null) break;
				}
			} else {
				future.completeExceptionally(task.getException());
				Log.e("GenitoreDAO.getDatiLogopedistaByIdGenitore()", "Errore nel recupero dei dati: " + task.getException());
			}
		});

		return future;
	}

	public CompletableFuture<Paziente> getPazienteByIdGenitore(String idGenitore) {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.LOGOPEDISTI);
			Task<DataSnapshot> task = ref.get();

			Paziente result = null;

			while (!task.isComplete()) {}

			for (DataSnapshot logopedistaSnapshot : task.getResult().getChildren()) {
				for (DataSnapshot pazienteSnapshot : logopedistaSnapshot.child(CostantiNodiDB.PAZIENTI).getChildren()) {
					DataSnapshot genitoreSnapshot = pazienteSnapshot.child(CostantiNodiDB.GENITORE);

					if (genitoreSnapshot.getKey().equals(idGenitore)) {
						Map<String, Object> fromDatabaseMap = (Map<String, Object>) pazienteSnapshot.getValue();
						result = new Paziente(fromDatabaseMap, pazienteSnapshot.getKey());
						break;
					}
				}
				if (result != null) break;
			}

			Log.d("GenitoreDAO.getPazienteByIdGenitore()", (result == null) ? result.toString() : "null");
			return result;
		});
	}

}
