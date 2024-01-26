package it.uniba.dib.sms2324.num15.PronuntiApp.models.database;

import java.util.List;

public interface DAO<T> {
	void save(T obj);
	void update(T obj);
	void delete(T obj);
	T getById(T obj);
	List<T> getAll();
}
