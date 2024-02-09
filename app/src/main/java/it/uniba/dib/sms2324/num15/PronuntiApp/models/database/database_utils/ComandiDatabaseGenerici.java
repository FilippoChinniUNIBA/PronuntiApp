package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.database_utils;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.TipoUtente;

public class ComandiDatabaseGenerici {
	private final FirebaseDatabase db;

	public ComandiDatabaseGenerici() {
		db = FirebaseDatabase.getInstance();
	}

	public void saveTipoUtente(String userId, TipoUtente tipoUtente) {
		DatabaseReference ref = db.getReference(CostantiNodiDB.MAPPA_TIPO_UTENTI);
		ref.child(userId).setValue(tipoUtente.toString());
	}

	public CompletableFuture<TipoUtente> getTipoUtente(String userId) {
		CompletableFuture<TipoUtente> future = new CompletableFuture<>();

		DatabaseReference ref = db.getReference(CostantiNodiDB.MAPPA_TIPO_UTENTI);
		ref.child(userId).get().addOnCompleteListener(task -> {
			if (task.isSuccessful()) {
				Log.d("DAOGenerica.getTipoUtente()", "Tipo utente: " + task.getResult().getValue().toString());
				future.complete(TipoUtente.fromString(task.getResult().getValue().toString()));
			} else {
				Log.e("DAOGenerica.getTipoUtente()", "Errore nel recupero del tipo utente: " + task.getException()); //TODO aggiungere agli errori
				future.completeExceptionally(task.getException());
			}
		});

		return future;
	}

}
