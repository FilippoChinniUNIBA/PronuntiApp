package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.classifica;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBClassifica;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.classifica.Classifica;

public class ClassificaDAO {
	private final FirebaseDatabase db;

	public ClassificaDAO() {
		db = FirebaseDatabase.getInstance();
	}

	public void save(String idLogopedista, Classifica obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.CLASSIFICA_PAZIENTI);
		ref.setValue(obj.toMap());
	}

	public void update(String idLogopedista, Classifica obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.CLASSIFICA_PAZIENTI);
		ref.setValue(obj.toMap());
	}

	public void delete(String idLogopedista, Classifica obj) {
		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.CLASSIFICA_PAZIENTI);
		ref.removeValue();
	}

	public CompletableFuture<Classifica> getByIdLogopedista(String idLogopedista) {
		CompletableFuture<Classifica> future = new CompletableFuture<>();

		DatabaseReference ref = this.db.getReference(CostantiNodiDB.LOGOPEDISTI).child(idLogopedista).child(CostantiNodiDB.CLASSIFICA_PAZIENTI);
		ref.get().addOnCompleteListener(task -> {
			if (task.isSuccessful()) {
				DataSnapshot snapshot = task.getResult();
				Map<String, Object> fromDatabaseMap = (Map<String, Object>) snapshot.getValue();
				Classifica classifica = new Classifica(fromDatabaseMap, "");

				Log.d("ClassificaDAO.getByIdLogopedista()", classifica.toString());
				future.complete(classifica);
			} else {
				Log.e("ClassificaDAO.getByIdLogopedista()", task.getException().getMessage());
				future.completeExceptionally(task.getException());
			}
		});

		return future;
	}

}
