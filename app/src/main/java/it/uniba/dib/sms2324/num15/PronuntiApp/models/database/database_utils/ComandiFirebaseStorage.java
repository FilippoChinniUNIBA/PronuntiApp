package it.uniba.dib.sms2324.num15.PronuntiApp.models.database.database_utils;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;

public class ComandiFirebaseStorage {
	public static final String TEXTURE_PERSONAGGI = "texture_personaggi";
	public static final String TEMPLATE_SCENARIGIOCO = "template_scenarigioco";
	public static final String SCENARIGIOCO = "scenarigioco";
	public static final String TEMPLATE_ESERCIZI = "template_esercizi";
	public static final String TEMPLATE_ESERCIZI_DENOMINAZIONE_IMMAGINE = TEMPLATE_ESERCIZI + "/template_esercizi_denominazione_immagine";
	public static final String TEMPLATE_ESERCIZI_SEQUENZA_PAROLE = TEMPLATE_ESERCIZI + "/template_esercizi_sequenza_parole";
	public static final String TEMPLATE_ESERCIZI_COPPIA_IMMAGINI = TEMPLATE_ESERCIZI + "/template_esercizi_coppia_immagini";
	public static final String ESERCIZI_DENOMINAZIONE_IMMAGINE = "esercizi_denominazione_immagine";
	public static final String ESERCIZI_SEQUENZA_PAROLE = "esercizi_sequenza_parole";
	public static final String ESERCIZI_COPPIA_IMMAGINI = "esercizi_coppia_immagini";
	public static final String AUDIO_REGISTRATI_DENOMINAZIONE_IMMAGINE = ESERCIZI_DENOMINAZIONE_IMMAGINE + "/audio_registrati";
	public static final String AUDIO_REGISTRATI_SEQUENZA_PAROLE = ESERCIZI_SEQUENZA_PAROLE + "/audio_registrati";
	public static final String AUDIO_REGISTRATI_COPPIA_IMMAGINI = ESERCIZI_COPPIA_IMMAGINI + "/audio_registrati";


	private final FirebaseStorage storage;

	public ComandiFirebaseStorage() {
		storage = FirebaseStorage.getInstance();
	}

	public CompletableFuture<String> uploadFileAndGetLink(Uri file, String path) {
		CompletableFuture<String> future = new CompletableFuture<>();

		StorageReference ref = storage.getReference().child(path);
		StorageReference fRef = ref.child(LocalDate.now().toString() + LocalTime.now().toString());

		fRef.putFile(file).addOnSuccessListener(taskSnapshot -> fRef.getDownloadUrl().addOnSuccessListener(uri -> {
			future.complete(uri.toString());
			Log.d("ComandiFirebaseStorage.uploadFileAndGetLink()", "File uploaded: " + uri.toString());
		}).addOnFailureListener(e -> {
			future.completeExceptionally(e);
			Log.e("ComandiFirebaseStorage.uploadFileAndGetLink()", "Errore nel getting del Download URL", e);
		})).addOnFailureListener(e -> {
			future.completeExceptionally(e);
			Log.e("ComandiFirebaseStorage.uploadFileAndGetLink()", "Errore Upload File", e);
		});

		return future;
	}

}
