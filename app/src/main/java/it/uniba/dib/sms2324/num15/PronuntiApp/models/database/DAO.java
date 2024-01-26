package it.uniba.dib.sms2324.num15.PronuntiApp.models.database;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public interface DAO<T> {
	FirebaseDatabase db = FirebaseDatabase.getInstance();
	void save(T obj);
	void update(T obj);
	void delete(T obj);
	T getById(T obj);
	List<T> getAll();
}
