package it.uniba.dib.sms2324.num15.PronuntiApp.models.database;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.risorse.errori.MessaggioErrore;

public interface DAO<T> {
	void save(T obj);

	void update(T obj);

	void delete(T obj);

	List<T> get(String field, Object value);

	T getById(String idObj);

	List<T> getAll();


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
			Log.e("Persistente.createQuery()", MessaggioErrore.TIPO_DATO_SCONOSCIUTO_DATABASE_ERR.toString());
			return null;
		}
	}

}
