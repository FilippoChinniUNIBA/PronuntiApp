package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.esercizio.risultato;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizio;

public class RisultatoEsercizioDAO implements DAO<RisultatoEsercizio> {
	protected final FirebaseDatabase db;

	public RisultatoEsercizioDAO() {
		db = FirebaseDatabase.getInstance();
	}

	@Override
	public void save(RisultatoEsercizio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.RISULTATI_ESERCIZI);
		String dbKey = obj.getIdEsercizio();
		ref.child(dbKey).setValue(obj.toMap());
	}

	@Override
	public void update(RisultatoEsercizio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.RISULTATI_ESERCIZI).child(obj.getIdEsercizio());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(RisultatoEsercizio obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.RISULTATI_ESERCIZI).child(obj.getIdEsercizio());
		ref.removeValue();
	}

	@Override
	public List<RisultatoEsercizio> get(String field, Object value) {
		return null;
	}

	@Override
	public RisultatoEsercizio getById(String idObj) {
		return null;
	}

	@Override
	public List<RisultatoEsercizio> getAll() {
		return null;
	}

}
