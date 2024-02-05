package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.esercizio;

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
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.Esercizio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.TemplateEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.TemplateEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.TemplateEsercizioSequenzaParole;

public class TemplateEsercizioDAO implements DAO<Esercizio> {
	private final FirebaseDatabase db;

	public TemplateEsercizioDAO() {
		db = FirebaseDatabase.getInstance();
	}

	@Override
	public void save(Esercizio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.TEMPLATE_ESERCIZI);
		String dbKey = ref.push().getKey();
		ref.child(dbKey).setValue(obj.toMap());
	}

	@Override
	public void update(Esercizio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.TEMPLATE_ESERCIZI).child(obj.getIdEsercizio());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(Esercizio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.TEMPLATE_ESERCIZI).child(obj.getIdEsercizio());
		ref.removeValue();
	}

	@Override
	public CompletableFuture<List<Esercizio>> get(String field, Object value) {
		CompletableFuture<List<Esercizio>> future = new CompletableFuture<>();

		DatabaseReference ref = db.getReference(CostantiNodiDB.TEMPLATE_ESERCIZI);
		Query query = DAO.createQuery(ref, field, value);

		query.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				List<Esercizio> result = new ArrayList<>();

				for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
					Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();

					if (fromDatabaseMap.containsKey(CostantiDBTemplateEsercizioDenominazioneImmagine.IMMAGINE_ESERCIZIO)) {
						TemplateEsercizioDenominazioneImmagine templateEsercizio = new TemplateEsercizioDenominazioneImmagine(fromDatabaseMap, snapshot.getKey());
						result.add(templateEsercizio);
					}
					else if (fromDatabaseMap.containsKey(CostantiDBTemplateEsercizioSequenzaParole.PAROLA_1)) {
						TemplateEsercizioSequenzaParole templateEsercizio = new TemplateEsercizioSequenzaParole(fromDatabaseMap, snapshot.getKey());
						result.add(templateEsercizio);
					}
					else if (fromDatabaseMap.containsKey(CostantiDBTemplateEsercizioCoppiaImmagini.IMMAGINE_ESERCIZIO_CORRETTA)) {
						TemplateEsercizioCoppiaImmagini templateEsercizio = new TemplateEsercizioCoppiaImmagini(fromDatabaseMap, snapshot.getKey());
						result.add(templateEsercizio);
					}
				}

				Log.d("TemplateEsercizioDAO.get()", result.toString());
				future.complete(result);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				Log.e("TemplateEsercizioDAO.get()", databaseError.toString());
				future.completeExceptionally(databaseError.toException());
			}
		});

		return future;
	}

	@Override
	public CompletableFuture<Esercizio> getById(String idObj) {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.TEMPLATE_ESERCIZI).child(idObj);
			Task<DataSnapshot> task = ref.get();

			Esercizio result = null;

			while (!task.isComplete()) {}

			DataSnapshot snapshot = task.getResult();
			Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();

			if (fromDatabaseMap.containsKey(CostantiDBTemplateEsercizioDenominazioneImmagine.IMMAGINE_ESERCIZIO)) {
				result = new TemplateEsercizioDenominazioneImmagine(fromDatabaseMap, snapshot.getKey());
			}
			else if (fromDatabaseMap.containsKey(CostantiDBTemplateEsercizioSequenzaParole.PAROLA_1)) {
				result = new TemplateEsercizioSequenzaParole(fromDatabaseMap, snapshot.getKey());
			}
			else if (fromDatabaseMap.containsKey(CostantiDBTemplateEsercizioCoppiaImmagini.IMMAGINE_ESERCIZIO_CORRETTA)) {
				result = new TemplateEsercizioCoppiaImmagini(fromDatabaseMap, snapshot.getKey());
			}

			Log.d("TemplateEsercizioDAO.getById()", result.toString());
			return result;
		});
	}

	@Override
	public CompletableFuture<List<Esercizio>> getAll() {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.TEMPLATE_ESERCIZI);
			Task<DataSnapshot> task = ref.get();

			List<Esercizio> result = new ArrayList<>();

			while (!task.isComplete()) {}

			for (DataSnapshot snapshot : task.getResult().getChildren()) {
				Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();

				if (fromDatabaseMap.containsKey(CostantiDBTemplateEsercizioDenominazioneImmagine.IMMAGINE_ESERCIZIO)) {
					TemplateEsercizioDenominazioneImmagine templateEsercizio = new TemplateEsercizioDenominazioneImmagine(fromDatabaseMap, snapshot.getKey());
					result.add(templateEsercizio);
				}
				else if (fromDatabaseMap.containsKey(CostantiDBTemplateEsercizioSequenzaParole.PAROLA_1)) {
					TemplateEsercizioSequenzaParole templateEsercizio = new TemplateEsercizioSequenzaParole(fromDatabaseMap, snapshot.getKey());
					result.add(templateEsercizio);
				}
				else if (fromDatabaseMap.containsKey(CostantiDBTemplateEsercizioCoppiaImmagini.IMMAGINE_ESERCIZIO_CORRETTA)) {
					TemplateEsercizioCoppiaImmagini templateEsercizio = new TemplateEsercizioCoppiaImmagini(fromDatabaseMap, snapshot.getKey());
					result.add(templateEsercizio);
				}
			}

			Log.d("TemplateEsercizioDAO.getAll()", result.toString());
			return result;
		});
	}

}
