package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.database_utils;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;

public class ComandiFirebaseStorage {
	private final FirebaseStorage storage;

	public ComandiFirebaseStorage() {
		storage = FirebaseStorage.getInstance();
	}

	private CompletableFuture<String> uploadFileAndGetLink(Uri file, String path) {
		CompletableFuture<String> future = new CompletableFuture<>();

		StorageReference ref = storage.getReference().child(path);
		StorageReference fRef = ref.child(LocalDate.now().toString() + LocalTime.now().toString());

		fRef.putFile(file).addOnSuccessListener(taskSnapshot -> fRef.getDownloadUrl().addOnSuccessListener(uri -> {
			future.complete(uri.toString());
			Log.d("DAOStorage.uploadFileAndGetLink()", "File uploaded: " + uri.toString());
		}).addOnFailureListener(e -> {
			future.completeExceptionally(e);
			Log.e("DAOStorage.uploadFileAndGetLink()", "Errore nel getting del Download URL", e);
		})).addOnFailureListener(e -> {
			future.completeExceptionally(e);
			Log.e("DAOStorage.uploadFileAndGetLink()", "Errore Upload File", e);
		});

		return future;
	}

}
