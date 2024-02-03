package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.esercizio;

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
	public List<Esercizio> get(String field, Object value) {
		DatabaseReference ref = db.getReference(CostantiNodiDB.TEMPLATE_ESERCIZI);
		Query query = DAO.createQuery(ref, field, value);

		List<Esercizio> result = new LinkedList<>();

		query.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
			@Override
			public void onComplete(@NonNull Task<DataSnapshot> task) {
				if (task.isSuccessful()) {
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
				}
			}
		});
		return result;
	}

	@Override
	public Esercizio getById(String idObj) {
		return null;
	}

	@Override
	public List<Esercizio> getAll() {
		return null;
	}

}
