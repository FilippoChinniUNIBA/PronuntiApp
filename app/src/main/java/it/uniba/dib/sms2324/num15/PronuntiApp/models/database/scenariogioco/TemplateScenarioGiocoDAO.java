package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.scenariogioco;

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
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.TemplateScenarioGioco;

public class TemplateScenarioGiocoDAO implements DAO<TemplateScenarioGioco> {
	private final FirebaseDatabase db;

	public TemplateScenarioGiocoDAO() {
		db = FirebaseDatabase.getInstance();
	}

	@Override
	public void save(TemplateScenarioGioco obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.TEMPLATE_SCENARIGIOCO);
		String dbKey = ref.push().getKey();
		ref.child(dbKey).setValue(obj.toMap());
	}

	@Override
	public void update(TemplateScenarioGioco obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.TEMPLATE_SCENARIGIOCO).child(obj.getIdTemplateScenarioGioco());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(TemplateScenarioGioco obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.TEMPLATE_SCENARIGIOCO).child(obj.getIdTemplateScenarioGioco());
		ref.removeValue();
	}

	@Override
	public CompletableFuture<List<TemplateScenarioGioco>> get(String field, Object value) {
		CompletableFuture<List<TemplateScenarioGioco>> future = new CompletableFuture<>();

		DatabaseReference ref = db.getReference(CostantiNodiDB.TEMPLATE_SCENARIGIOCO);
		Query query = DAO.createQuery(ref, field, value);

		query.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				List<TemplateScenarioGioco> result = new ArrayList<>();

				for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
					Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
					TemplateScenarioGioco templateScenarioGioco = new TemplateScenarioGioco(fromDatabaseMap, snapshot.getKey());
					result.add(templateScenarioGioco);
				}

				Log.d("TemplateScenarioGiocoDAO.get()", result.toString());
				future.complete(result);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				Log.e("TemplateScenarioGiocoDAO.get()", databaseError.toString());
				future.completeExceptionally(databaseError.toException());
			}
		});

		return future;
	}

	@Override
	public CompletableFuture<TemplateScenarioGioco> getById(String idObj) {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.TEMPLATE_SCENARIGIOCO).child(idObj);
			Task<DataSnapshot> task = ref.get();

			TemplateScenarioGioco result = null;

			while (!task.isComplete()) {}

			DataSnapshot snapshot = task.getResult();
			Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
			result = new TemplateScenarioGioco(fromDatabaseMap, idObj);

			Log.d("TemplateScenarioGiocoDAO.getById()", result.toString());
			return result;
		});
	}

	@Override
	public CompletableFuture<List<TemplateScenarioGioco>> getAll() {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.TEMPLATE_SCENARIGIOCO);
			Task<DataSnapshot> task = ref.get();

			List<TemplateScenarioGioco> result = new ArrayList<>();

			while (!task.isComplete()) {}

			for (DataSnapshot snapshot : task.getResult().getChildren()) {
				Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
				TemplateScenarioGioco templateScenarioGioco = new TemplateScenarioGioco(fromDatabaseMap, snapshot.getKey());
				result.add(templateScenarioGioco);
			}

			Log.d("TemplateScenarioGiocoDAO.getAll()", result.toString());
			return result;
		});
	}

}
