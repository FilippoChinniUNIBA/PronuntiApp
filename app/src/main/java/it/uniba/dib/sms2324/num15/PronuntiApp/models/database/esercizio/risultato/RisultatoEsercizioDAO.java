package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.esercizio.risultato;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBRisultato;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioDenominazioneImmagine;

public class RisultatoEsercizioDAO /*implements DAO<RisultatoEsercizio>*/ {
	protected final FirebaseDatabase db;

	public RisultatoEsercizioDAO() {
		db = FirebaseDatabase.getInstance();
	}

	/*@Override
	public void save(RisultatoEsercizio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.RISULTATO_ESERCIZIO);
		String dbKey = obj.getIdEsercizio();
		ref.child(dbKey).setValue(obj.toMap());
	}

	@Override
	public void update(RisultatoEsercizio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.RISULTATO_ESERCIZIO).child(obj.getIdEsercizio());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(RisultatoEsercizio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.RISULTATO_ESERCIZIO).child(obj.getIdEsercizio());
		ref.removeValue();
	}

	@Override
	public List<RisultatoEsercizio> get(String field, Object value) {
		DatabaseReference ref = db.getReference(CostantiNodiDB.ESERCIZI);
		Query query = DAO.createQuery(ref, field, value);

		List<RisultatoEsercizio> result = new LinkedList<>();

		query.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
			@Override
			public void onComplete(@NonNull Task<DataSnapshot> task) {
				if (task.isSuccessful()) {
					for (DataSnapshot snapshot : task.getResult().getChildren()) {
						Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();

						if (fromDatabaseMap.containsKey(CostantiDBRisultato.AUDIO_REGISTRATO)) {
							RisultatoEsercizioDenominazioneImmagine risultatoEsercizio = new RisultatoEsercizioDenominazioneImmagine(fromDatabaseMap, snapshot.getKey());
							result.add(risultatoEsercizio);
						}
						else {
							RisultatoEsercizioCoppiaImmagini risultatoEsercizio = new RisultatoEsercizioCoppiaImmagini(fromDatabaseMap, snapshot.getKey());
							result.add(risultatoEsercizio);
						}
					}
				}
			}
		});
		return result;
	}

	@Override
	public RisultatoEsercizio getById(String idObj) {
		return null;
	}

	@Override
	public List<RisultatoEsercizio> getAll() {
		return null;
	}*/

}
