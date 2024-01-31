package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.risorse.errori.MessaggioErrore;

public class PazienteDAO implements DAO<Paziente> {

	private final FirebaseDatabase db;

	public PazienteDAO() {
		db = FirebaseDatabase.getInstance();
	}

	//TODO: rivedere i metodi derivati da DAO
	//TODO: rivedere i metodi derivati da DAO
	//TODO: rivedere i metodi derivati da DAO
	//TODO: rivedere i metodi derivati da DAO
	//TODO: rivedere i metodi derivati da DAO
	//TODO: rivedere i metodi derivati da DAO
	//TODO: rivedere i metodi derivati da DAO
	//TODO: rivedere i metodi derivati da DAO
	//TODO: rivedere i metodi derivati da DAO

	@Override
	public void save(Paziente obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.PAZIENTI);

		String dbKey = ref.push().getKey();

		if (dbKey != null) {
			ref.child(dbKey).setValue(obj.toMap());
		}
		else {
			Log.e("Paziente DAO", MessaggioErrore.CHIAVE_NULL_DATABASE_ERR.toString());
			throw new NullPointerException(MessaggioErrore.CHIAVE_NULL_DATABASE_ERR.toString());
		}
	}

	@Override
	public void update(Paziente obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.PAZIENTI).child(obj.getIdProfilo());
		ref.setValue(obj.toMap());
	}

	@Override
	public void delete(Paziente obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.PAZIENTI).child(obj.getIdProfilo());
		ref.removeValue();
	}

	@Override
	public List<Paziente> get(String field, Object value) {
		return null;
	}

	@Override
	public Paziente getById(String IdPaziente) {
		return null;
	}

	@Override
	public List<Paziente> getAll() {
		return null;
	}

}
