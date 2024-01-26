package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo;

import com.google.firebase.Firebase;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;

public class PazienteDAO implements DAO<Paziente> {

/*	private FirebaseDatabase db;

	public PazienteDAO() {
		db = FirebaseDatabase.getInstance();
	}*/

	@Override
	public void save(Paziente paziente) {
		db.getReference("pazienti").child(paziente.getUsername()).setValue(paziente);
	}

	@Override
	public void update(Paziente paziente) {

	}

	@Override
	public void delete(Paziente paziente) {

	}

	@Override
	public Paziente getById(Paziente paziente) {
		return null;
	}

	@Override
	public List<Paziente> getAll() {
		return null;
	}
}
