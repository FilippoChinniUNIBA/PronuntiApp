package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo;

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
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBAppuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class AppuntamentoDAO implements DAO<Appuntamento> {
	private final FirebaseDatabase db;

	public AppuntamentoDAO() {
		db = FirebaseDatabase.getInstance();
	}

	@Override
	public void save(Appuntamento obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.APPUNTAMENTI);
		String dbKey = ref.push().getKey();
		ref.child(dbKey).setValue(obj.toMap());
	}

	public CompletableFuture<Appuntamento> saveWithFuture(Appuntamento obj) {
		CompletableFuture<Appuntamento> future = new CompletableFuture<>();

		DatabaseReference ref = this.db.getReference(CostantiNodiDB.APPUNTAMENTI);
		String dbKey = ref.push().getKey();
		ref.child(dbKey).setValue(obj.toMap()).addOnCompleteListener(task -> {
			obj.setIdAppuntamento(dbKey);
			future.complete(obj);
		});

		return future;
	}

	@Override
	public void update(Appuntamento obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.APPUNTAMENTI).child(obj.getIdAppuntamento());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(Appuntamento obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.APPUNTAMENTI).child(obj.getIdAppuntamento());
		ref.removeValue();
	}

	@Override
	public CompletableFuture<List<Appuntamento>> get(String field, Object value) {
		CompletableFuture<List<Appuntamento>> future = new CompletableFuture<>();

		DatabaseReference ref = db.getReference(CostantiNodiDB.APPUNTAMENTI);
		Query query = DAO.createQuery(ref, field, value);

		query.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				List<Appuntamento> result = new ArrayList<>();

				for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
					Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
					Appuntamento appuntamento = new Appuntamento(fromDatabaseMap, snapshot.getKey());
					result.add(appuntamento);
				}

				Log.d("AppuntamentoDAO.get()", result.toString());
				future.complete(result);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				Log.e("AppuntamentoDAO.get()", databaseError.toString());
				future.completeExceptionally(databaseError.toException());
			}
		});

		return future;
	}

	@Override
	public CompletableFuture<Appuntamento> getById(String idObj) {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.APPUNTAMENTI).child(idObj);
			Task<DataSnapshot> task = ref.get();

			Appuntamento result = null;

			while (!task.isComplete()) {}

			DataSnapshot snapshot = task.getResult();
			Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
			result = new Appuntamento(fromDatabaseMap, idObj);

			Log.d("AppuntamentoDAO.getById()", result.toString());
			return result;
		});
	}

	@Override
	public CompletableFuture<List<Appuntamento>> getAll() {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.APPUNTAMENTI);
			Task<DataSnapshot> task = ref.get();

			List<Appuntamento> result = new ArrayList<>();

			while (!task.isComplete()) {}

			for (DataSnapshot snapshot : task.getResult().getChildren()) {
				Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
				Appuntamento appuntamento = new Appuntamento(fromDatabaseMap, snapshot.getKey());
				result.add(appuntamento);
			}

			Log.d("AppuntamentoDAO.getAll()", result.toString());
			return result;
		});
	}

	public void updateListaAppuntamenti(List<Appuntamento> listaAppuntamenti) {
		for (Appuntamento appuntamento : listaAppuntamenti) {
			if (appuntamento.getIdAppuntamento() == null) {
				this.save(appuntamento);
			}
			else {
				this.update(appuntamento);
			}
		}
	}

	public void deleteById(String idAppuntamento) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.APPUNTAMENTI).child(idAppuntamento);
		ref.removeValue();
	}

}
