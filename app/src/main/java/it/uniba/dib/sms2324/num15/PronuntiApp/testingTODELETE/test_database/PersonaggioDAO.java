package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE.test_database;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class PersonaggioDAO{
	private final FirebaseDatabase db;

	public PersonaggioDAO() {
		db = FirebaseDatabase.getInstance();
	}

	public void save(Personaggio obj) {
		DatabaseReference ref = this.db.getReference("TEST").child("PersonaggiTest");
		String dbKey = ref.push().getKey();
		ref.child(dbKey).setValue(obj.toMap());
	}
	/*
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
	public List<Personaggio> get(String field, Object value) {

		DatabaseReference ref = db.getReference(CostantiNodiDB.PERSONAGGI);
		Query query = DAO.createQuery(ref, field, value);

		List<Personaggio> result = new LinkedList<>();

		while (!query.get().isComplete()) {}

		for (DataSnapshot snapshot : query.get().getResult().getChildren()) {
			Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
			Personaggio personaggio = new Personaggio(fromDatabaseMap, snapshot.getKey());

			Log.d("PROVA PERSONAGGIO", personaggio.toString());
			result.add(personaggio);
		}
		Log.d("PROVA PERSONAGGIO", result.toString());




		return result;
	}

	@Override
	public Personaggio getById(String idObj) {
		return null;
	}*/
	public CompletableFuture<List<Personaggio>> getPersonaggi() {
		CompletableFuture<List<Personaggio>> future = new CompletableFuture<>();

		DatabaseReference ref = db.getReference("TEST");
		Task<DataSnapshot> data = ref.child("PersonaggiTest").get();

		data.addOnCompleteListener(task -> {
			if (task.isSuccessful()) {
				DataSnapshot dataSnapshot = task.getResult();
				List<Personaggio> result = new LinkedList<>();
				for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
					Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
					Personaggio personaggio = new Personaggio(fromDatabaseMap, snapshot.getKey());
					result.add(personaggio);
				}
				future.complete(result);
			} else {
				future.completeExceptionally(task.getException());
			}
		});
		return future;
	}
	/*
	public CompletableFuture<List<Personaggio>> getAllProva() {
		return CompletableFuture.supplyAsync(() -> {
			DatabaseReference ref = db.getReference(CostantiNodiDB.PERSONAGGI);
			Task<DataSnapshot> query = ref.get();

			Log.d("PROVA PERSONAGGIO", "PRIMA WHILE");

			while (!query.isComplete()) {}

			Log.d("PROVA PERSONAGGIO", "DOPO WHILE");

			List<Personaggio> personaggi = new ArrayList<>();

			for (DataSnapshot snapshot : query.getResult().getChildren()) {
				Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
				Personaggio personaggio = new Personaggio(fromDatabaseMap, snapshot.getKey());
				personaggi.add(personaggio);
				Log.d("PROVA PERSONAGGIO", "DENTRO FOR");
			}
			Log.d("PROVA PERSONAGGIO", "FINE FOR" + personaggi.toString());
			return personaggi;
		});
	}

	@Override
	public List<Personaggio> getAll() {
		return null;
	}
	*/
}
