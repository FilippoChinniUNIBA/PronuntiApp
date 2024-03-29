package it.uniba.dib.sms2324.num15.PronuntiApp.models.database;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.risorse.MessaggioLogErrore;

public interface DAO<T> {
	void save(T obj);

	void update(T obj);

	void delete(T obj);

	CompletableFuture<List<T>> get(String field, Object value);

	CompletableFuture<T> getById(String idObj);

	CompletableFuture<List<T>> getAll();


	/**
	 * Metodo per creare una query per il database
	 * @param ref Riferimento al database
	 * @param field Campo su cui fare la query
	 * @param value Valore da cercare
	 * @return Query
	 */
	static Query createQuery(DatabaseReference ref, String field, Object value) {
		if (value instanceof String) {
			return ref.orderByChild(field).equalTo(value.toString());
		} else if (value instanceof Long || value instanceof Integer) {
			return ref.orderByChild(field).equalTo((int) value);
		} else if (value instanceof Boolean) {
			return ref.orderByChild(field).equalTo((boolean) value);
		} else if (value instanceof Double) {
			return ref.orderByChild(field).equalTo((double) value);
		} else {
			Log.e("Persistente.createQuery()", "Tipo del parametro value non supportato dal db: " + value.getClass().getName());
			return null;
		}
	}

}
