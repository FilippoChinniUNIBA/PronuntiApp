package it.uniba.dib.sms2324.num15.PronuntiApp.models.database;

import java.util.List;

public interface DAO<T> {
	void save(T obj);

	void update(T obj);

	void delete(T obj);

	List<T> get(String field, Object value);

	T getById(String idObj);

	List<T> getAll();

}
