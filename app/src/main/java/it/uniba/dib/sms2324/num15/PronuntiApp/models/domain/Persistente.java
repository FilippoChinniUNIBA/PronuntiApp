package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.risorse.errori.MessaggioErrore;

public interface Persistente<T> {
	Map<String, Object> toMap();
	T fromMap(Map<String, Object> fromDatabaseMap);

}
