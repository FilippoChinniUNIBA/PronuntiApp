package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain;

import java.util.Map;

public interface Persistente<T> {
	Map<String, Object> toMap();
	T fromMap(Map<String, Object> fromDatabaseMap);

}
