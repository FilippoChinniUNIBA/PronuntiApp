package it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.CompletableFuture;

public class Autenticazione {
	private static FirebaseAuth mAuth;

	public Autenticazione() {
		mAuth = FirebaseAuth.getInstance();
	}

	public CompletableFuture<String> registrazione(String email, String password) {
		CompletableFuture<String> future = new CompletableFuture<>();

		mAuth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener(task -> {
					if (task.isSuccessful()) {
						Log.d("Autenticazione.registrazione()", "Registrazione avvenuta con successo: " + mAuth.getCurrentUser().getUid());
						future.complete(mAuth.getCurrentUser().getUid());
					} else {
						Log.e("Autenticazione.registrazione()", "Registrazione fallita: " + task.getException());
						future.completeExceptionally(task.getException());
					}
				});

		return future;
	}

	public CompletableFuture<String> login(String email, String password) {
		CompletableFuture<String> future = new CompletableFuture<>();

		mAuth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener(task -> {
					if (task.isSuccessful()) {
						Log.d("Autenticazione.login()", "Login avvenuto con successo: " + mAuth.getCurrentUser().getUid());
						future.complete(mAuth.getCurrentUser().getUid());
					} else {
						Log.e("Autenticazione.login()", "Login fallito: " + task.getException());
						future.completeExceptionally(task.getException());
					}
				});

		return future;
	}

	public String getCurrentUserId() {
		return mAuth.getCurrentUser().getUid();
	}

	public void logout() {
		mAuth.signOut();
	}

}
