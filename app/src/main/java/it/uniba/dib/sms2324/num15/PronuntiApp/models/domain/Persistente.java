package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.risorse.errori.MessaggioErrore;

public interface Persistente<T> {
	Map<String, Object> toMap();
	T fromMap(Map<String, Object> fromDatabaseMap);

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
