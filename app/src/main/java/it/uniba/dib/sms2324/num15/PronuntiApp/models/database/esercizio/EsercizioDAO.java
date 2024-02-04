package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.esercizio;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.Esercizio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class EsercizioDAO implements DAO<Esercizio> {
	private final FirebaseDatabase db;

	public EsercizioDAO() {
		db = FirebaseDatabase.getInstance();
	}

	@Override
	public void save(Esercizio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.ESERCIZI);
		String dbKey = ref.push().getKey();
		ref.child(dbKey).setValue(obj.toMap());
	}

	@Override
	public void update(Esercizio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.ESERCIZI).child(obj.getIdEsercizio());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(Esercizio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.ESERCIZI).child(obj.getIdEsercizio());
		ref.removeValue();
	}

	@Override
	public List<Esercizio> get(String field, Object value) {
		DatabaseReference ref = db.getReference(CostantiNodiDB.ESERCIZI);
		Query query = DAO.createQuery(ref, field, value);

		List<Esercizio> result = new LinkedList<>();

		query.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
			@Override
			public void onComplete(@NonNull Task<DataSnapshot> task) {
				if (task.isSuccessful()) {
					for (DataSnapshot snapshot : task.getResult().getChildren()) {
						Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();

						if (fromDatabaseMap.containsKey(CostantiDBEsercizioDenominazioneImmagine.IMMAGINE_ESERCIZIO)) {
							EsercizioDenominazioneImmagine esercizio = new EsercizioDenominazioneImmagine(fromDatabaseMap, snapshot.getKey());
							result.add(esercizio);
						}
						else if (fromDatabaseMap.containsKey(CostantiDBEsercizioSequenzaParole.PAROLA_1)) {
							EsercizioSequenzaParole esercizio = new EsercizioSequenzaParole(fromDatabaseMap, snapshot.getKey());
							result.add(esercizio);
						}
						else if (fromDatabaseMap.containsKey(CostantiDBEsercizioCoppiaImmagini.IMMAGINE_ESERCIZIO_CORRETTA)) {
							EsercizioCoppiaImmagini esercizio = new EsercizioCoppiaImmagini(fromDatabaseMap, snapshot.getKey());
							result.add(esercizio);
						}
					}
				}
			}
		});
		return result;
	}

	@Override
	public Esercizio getById(String idObj) {
		return null;
	}

	public CompletableFuture<Esercizio> getByIdFuture(String idObj) {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.ESERCIZI);
			Task<DataSnapshot> query = ref.child(idObj).get();

			while (!query.isComplete()) {}

			DataSnapshot snapshot = query.getResult();
			Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();

			Log.d("PROVA ESERCIZIO", fromDatabaseMap.toString());

			if (fromDatabaseMap.containsKey(CostantiDBEsercizioDenominazioneImmagine.IMMAGINE_ESERCIZIO)) {
				EsercizioDenominazioneImmagine esercizio = new EsercizioDenominazioneImmagine(fromDatabaseMap, snapshot.getKey());
				return esercizio;
			} else if (fromDatabaseMap.containsKey(CostantiDBEsercizioSequenzaParole.PAROLA_1)) {
				EsercizioSequenzaParole esercizio = new EsercizioSequenzaParole(fromDatabaseMap, snapshot.getKey());
				return esercizio;
			} else if (fromDatabaseMap.containsKey(CostantiDBEsercizioCoppiaImmagini.IMMAGINE_ESERCIZIO_CORRETTA)) {
				EsercizioCoppiaImmagini esercizio = new EsercizioCoppiaImmagini(fromDatabaseMap, snapshot.getKey());
				return esercizio;
			}
			return null;
		});
	}

	public CompletableFuture<List<Esercizio>> getListEserciziFromListId(List<String> listaId) {
		List<CompletableFuture<Esercizio>> futures = new ArrayList<>();
		for (String id : listaId) {
			futures.add(getByIdFuture(id));
		}
		return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
				.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()));
	}

	@Override
	public List<Esercizio> getAll() {
		return null;
	}
}
