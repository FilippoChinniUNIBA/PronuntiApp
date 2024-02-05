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
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;

public class GenitoreDAO {
	private final FirebaseDatabase db;
	private final FirebaseAuth firebaseAuth;

	public GenitoreDAO() {
		db = FirebaseDatabase.getInstance();
		firebaseAuth = FirebaseAuth.getInstance();
	}

	public void save(Genitore obj, String idPaziente) {
		String idLogopedista = firebaseAuth.getCurrentUser().getUid();

		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.PAZIENTI).child(idPaziente).child(CostantiNodiDB.GENITORE);
		String dbKey = obj.getIdProfilo();
		ref.child(dbKey).setValue(obj.toMap());
	}

	public void update(Genitore obj, String idPaziente) {
		String idLogopedista = firebaseAuth.getCurrentUser().getUid(); //TODO probabilmente da cambiare, anche il Paziente può fare update su se stesso

		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.PAZIENTI).child(idPaziente).child(CostantiNodiDB.GENITORE);
		ref.setValue(obj.toMap());
	}

	public void delete(Genitore obj, String idPaziente) {
		String idLogopedista = firebaseAuth.getCurrentUser().getUid();

		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.PAZIENTI).child(idPaziente).child(CostantiNodiDB.GENITORE);
		ref.removeValue();
	}

	public CompletableFuture<List<Genitore>> get(String field, Object value) {
		CompletableFuture<List<Genitore>> future = new CompletableFuture<>();

		DatabaseReference ref = db.getReference(CostantiNodiDB.GENITORE); //TODO funzione che capisce il tipo di utente
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
	}

	public CompletableFuture<Genitore> getById(String idObj) {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.GENITORE).child(idObj); //TODO funzione che capisce il tipo di utente
			Task<DataSnapshot> task = ref.get();

			Genitore result = null;

			while (!task.isComplete()) {}

			DataSnapshot snapshot = task.getResult();
			Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
			result = new Genitore(fromDatabaseMap, idObj);

			Log.d("GenitoreDAO.getById()", result.toString());
			return result;
		});
	}

	public CompletableFuture<List<Genitore>> getAll() {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.GENITORE); //TODO funzione che capisce il tipo di utente
			Task<DataSnapshot> task = ref.get();

			List<Genitore> result = new ArrayList<>();

			while (!task.isComplete()) {}

			for (DataSnapshot snapshot : task.getResult().getChildren()) {
				Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
				Genitore genitore = new Genitore(fromDatabaseMap, snapshot.getKey());
				result.add(genitore);
			}

			Log.d("GenitoreDAO.getAll()", result.toString());
			return result;
		});
	}

}
